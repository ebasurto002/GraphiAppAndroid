package eus.ehu.tta.graphiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PostLevel1Activity extends coreActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_level1);
    }

    public void addL1Exercise(View view){

        final String correcta = ((EditText)findViewById(R.id.cWordL1)).getText().toString();
        final String incorrecta = ((EditText)findViewById(R.id.iWordL1)).getText().toString();
        final String login = TeacherData.getInstance().getLogin();

        final Button btn = findViewById(R.id.l1AddBtn);
        final EditText pCorrecta = findViewById(R.id.cWordL1);
        final EditText pIncorrecta = findViewById(R.id.iWordL1);
        btn.setEnabled(false);

        new ProgressTask<String>(this){

            @Override
            protected String work() {
                try{
                    return business.postLevel1(correcta,incorrecta,login);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onFinish(String result) {
                btn.setEnabled(true);
                if(result.contains("Error")){
                    Toast.makeText(PostLevel1Activity.this, R.string.onPostLevel1Fail,Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(PostLevel1Activity.this, R.string.onPostLevel1Success,Toast.LENGTH_SHORT).show();
                    pCorrecta.setText("");
                    pIncorrecta.setText("");
                }
            }
        }.execute();

    }

    public void level2(View view){
        Intent intent = new Intent(this, PostLevel2Activity.class);
        startActivity(intent);
    }
}

package eus.ehu.tta.graphiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PostLevel4Activity extends coreActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_level4);
    }

    public void level5(View view){
        Intent intent = new Intent(this, PostLevel5Activity.class);
        startActivity(intent);
    }

    public void level3(View view){
        Intent intent = new Intent(this, PostLevel3Activity.class);
        startActivity(intent);
    }
    public void addL4Exercise(View view){
        String headline = ((EditText)findViewById(R.id.headLine4)).getText().toString();
        int iWordPosition = Integer.parseInt(((EditText)findViewById(R.id.iWordL4)).getText().toString());
        boolean result = business.postLevel4(this,headline,iWordPosition);

        if(result){
            Toast.makeText(this, R.string.onPostLevel1Success, Toast.LENGTH_SHORT).show();
            ((EditText)findViewById(R.id.headLine4)).setText("");
            ((EditText)findViewById(R.id.iWordL4)).setText("");
        }
        else{
            Toast.makeText(this, R.string.onPostLevel1Fail, Toast.LENGTH_SHORT).show();
        }
    }
}

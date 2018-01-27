package eus.ehu.tta.graphiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PostLevel5Activity extends coreActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_level5);
    }

    public void level8(View view){
        Intent intent = new Intent(this, PostLevel8Activity.class);
        startActivity(intent);
    }

    public void level4(View view){
        Intent intent = new Intent(this, PostLevel4Activity.class);
        startActivity(intent);
    }

    public void addL5Exercise(View view){
        String palabra1 = ((EditText)findViewById(R.id.wWording1)).getText().toString();
        String palabra2 = ((EditText)findViewById(R.id.wWording2)).getText().toString();
        String frase1 = ((EditText)findViewById(R.id.sWording1)).getText().toString();
        String frase2 = ((EditText)findViewById(R.id.sWording2)).getText().toString();

        boolean result = business.postLevel5(this,palabra1,palabra2,frase1,frase2);

        if(result){
            Toast.makeText(this, R.string.onPostLevel1Success, Toast.LENGTH_SHORT).show();
            ((EditText)findViewById(R.id.wWording1)).setText("");
            ((EditText)findViewById(R.id.wWording2)).setText("");
            ((EditText)findViewById(R.id.sWording1)).setText("");
            ((EditText)findViewById(R.id.sWording2)).setText("");
        }
        else{
            Toast.makeText(this, R.string.onPostLevel1Fail, Toast.LENGTH_SHORT).show();
        }
    }
}

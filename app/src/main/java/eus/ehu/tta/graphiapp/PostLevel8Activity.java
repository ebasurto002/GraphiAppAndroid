package eus.ehu.tta.graphiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class PostLevel8Activity extends coreActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_level8);
    }


    public void level5(View view){
        Intent intent = new Intent(this, PostLevel5Activity.class);
        startActivity(intent);
    }
    public void endPosting(View view){
        Intent intent = new Intent(this,TeacherHomeActivity.class);
        startActivity(intent);
    }
    public void addL8Exercise(View view){
        String word = ((EditText)findViewById(R.id.wordL8)).getText().toString();
        RadioGroup rg = (RadioGroup)findViewById(R.id.kindaStressRGroup);
        int stressType = 0;
        int checkedID = rg.getCheckedRadioButtonId();

        switch(checkedID){
            case R.id.idAguda:
                stressType=1;
                break;
            case R.id.idLlana:
                stressType=2;
                break;
            case R.id.idEsdrujula:
                stressType=3;
                break;
        }

        boolean result = business.postLevel8(this,word,stressType);
        if(result){
            Toast.makeText(this, R.string.onPostLevel1Success, Toast.LENGTH_SHORT).show();
            ((EditText)findViewById(R.id.wordL8)).setText("");
            rg.clearCheck();
        }
        else{
            Toast.makeText(this, R.string.onPostLevel1Fail, Toast.LENGTH_SHORT).show();
        }
    }
}

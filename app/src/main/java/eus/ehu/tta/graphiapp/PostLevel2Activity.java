package eus.ehu.tta.graphiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PostLevel2Activity extends coreActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_level2);
    }

    public void level3(View view){
        Intent intent = new Intent(this, PostLevel3Activity.class);
        startActivity(intent);
    }

    public void level1(View view){
        Intent intent = new Intent(this, PostLevel1Activity.class);
        startActivity(intent);
    }
}

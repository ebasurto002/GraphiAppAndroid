package eus.ehu.tta.graphiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PostLevel3Activity extends coreActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_level3);
    }

    public void level4(View view){
        Intent intent  = new Intent(this, PostLevel4Activity.class);
        startActivity(intent);
    }

    public void level2(View view){
        Intent intent = new Intent(this, PostLevel2Activity.class);
        startActivity(intent);
    }
}

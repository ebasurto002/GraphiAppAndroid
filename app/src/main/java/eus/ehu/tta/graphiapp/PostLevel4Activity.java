package eus.ehu.tta.graphiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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
}

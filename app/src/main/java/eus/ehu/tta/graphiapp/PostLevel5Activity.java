package eus.ehu.tta.graphiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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
}

package eus.ehu.tta.graphiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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
}

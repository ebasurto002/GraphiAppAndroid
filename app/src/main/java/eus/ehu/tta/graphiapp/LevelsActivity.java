package eus.ehu.tta.graphiapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

public class LevelsActivity extends drawerStudentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout frameLayout = findViewById(R.id.content_frame);
        LayoutInflater inflater = LayoutInflater.from(this);
        inflater.inflate(R.layout.activity_levels,frameLayout,true);
    }

    public void goBack(View view) {
        Intent intent = new Intent(this,IndividualModeActivity.class);
        startActivity(intent);
    }

    public void goToLevel1(View view) {
        Intent intent = new Intent(this,Level1Activity.class);
        startActivity(intent);
    }
    public void goToLevel2(View view) {
        Intent intent = new Intent(this,Level2Activity.class);
        startActivity(intent);
    }

    public void goToLevel3(View view) {
        Intent intent = new Intent(this,Level3Activity.class);
        startActivity(intent);
    }

    public void goToLevel4(View view) {
        Intent intent = new Intent(this,Level4Activity.class);
        startActivity(intent);
    }
}

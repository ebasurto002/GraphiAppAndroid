package eus.ehu.tta.graphiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

public class GameModesActivity extends drawerStudentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout frameLayout = findViewById(R.id.content_frame);
        LayoutInflater inflater = LayoutInflater.from(GameModesActivity.this);
        inflater.inflate(R.layout.activity_game_modes,frameLayout,true);
    }

    public void goBack(View view) {
        Intent intent = new Intent(this,userHomeActivity.class);
        startActivity(intent);
    }
}

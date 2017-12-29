package eus.ehu.tta.graphiapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

public class userHomeActivity extends drawerStudentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout frameLayout = findViewById(R.id.content_frame);
        LayoutInflater inflater = LayoutInflater.from(userHomeActivity.this);
        inflater.inflate(R.layout.activity_user_home,frameLayout,true);
    }

    public void goToHelp(View view) {
        Intent intent = new Intent(this,applicationHelpActivity.class);
        startActivity(intent);
    }
}

package eus.ehu.tta.graphiapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StudentData studentData = new StudentData(this);

        if (studentData.getNickname() != null)
        {
            Intent intent = new Intent(this, userHomeActivity.class);
            startActivity(intent);
        }

        else
        {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}

package eus.ehu.tta.graphiapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences defaultSP = getSharedPreferences("eus.ehu.tta.graphiapp.default", Context.MODE_PRIVATE);

        if(defaultSP.getBoolean("isTeacher",false)){
            TeacherData.getInstance().setLogin(defaultSP.getString("currentNickname",null));
            Intent intent = new Intent(this, TeacherHomeActivity.class);
            startActivity(intent);
        }
        else{
            StudentData studentData = new StudentData(this);

            if (studentData.getNickname() != null)
            {
                Intent intent = new Intent(this, userHomeActivity.class);
                startActivity(intent);
            }

            else
            {
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
            }
        }
    }
}

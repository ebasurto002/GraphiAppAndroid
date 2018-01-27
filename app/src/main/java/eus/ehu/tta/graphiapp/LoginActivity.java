package eus.ehu.tta.graphiapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class LoginActivity extends coreActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view){

        final String login = ((EditText)findViewById(R.id.loginName)).getText().toString();
        final String passwd = ((EditText)findViewById(R.id.psswd)).getText().toString();
        final int uType;
        final RadioGroup rGroup = (RadioGroup)findViewById(R.id.loginInitRadioGroup);
        int idChecked = rGroup.getCheckedRadioButtonId();

        if(idChecked == R.id.alumnRadioButton2){
            uType = Business.TIPO_ALUMNO;
        }
        else if(idChecked == R.id.teacherRadioButton2){
            uType = Business.TIPO_PROFESOR;
        }
        else {
            uType = -1;
        }
        new ProgressTask<Boolean>(this){
            @Override
            protected Boolean work(){
                return business.login(login,passwd,uType);
            }
            @Override
            protected void onFinish(Boolean result){
                if(result.booleanValue() == true){
                    SharedPreferences defaultSP = getSharedPreferences("eus.ehu.tta.graphiapp.default", Context.MODE_PRIVATE);
                    if(uType==Business.TIPO_ALUMNO){
                        Intent intent = new Intent(LoginActivity.this,userHomeActivity.class);
                        StudentData studentData = new StudentData(LoginActivity.this);
                        studentData.setNickname(login);
                        defaultSP.edit().putBoolean("isTeacher",false).apply();
                        startActivity(intent);
                    }
                    else{
                        Intent intent = new Intent(LoginActivity.this,TeacherHomeActivity.class);
                        TeacherData td = TeacherData.getInstance();
                        td.setLogin(login);
                        defaultSP.edit().putString("currentNickname",login).apply();
                        defaultSP.edit().putBoolean("isTeacher",true).apply();
                        intent.putExtra(TeacherHomeActivity.EXTRA_LOGIN, login);
                        startActivity(intent);
                    }
                }
                else{
                    Toast.makeText(LoginActivity.this, R.string.loginErrorToast,Toast.LENGTH_SHORT).show();
                    EditText pswd = (EditText)findViewById(R.id.psswd);
                    pswd.setText("");
                }
            }
        }.execute();


    }
}

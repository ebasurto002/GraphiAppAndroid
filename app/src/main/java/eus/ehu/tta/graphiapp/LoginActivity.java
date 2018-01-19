package eus.ehu.tta.graphiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private Business business;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.business = new RealBusiness();
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
                    if(uType==Business.TIPO_ALUMNO){
                        Intent intent = new Intent(LoginActivity.this,TeacherHomeActivity.class);
                        intent.putExtra(TeacherHomeActivity.EXTRA_LOGIN,login);
                    }
                    else{
                        Intent intent = new Intent(LoginActivity.this,TeacherHomeActivity.class);
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

package eus.ehu.tta.graphiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

public class LoginActivity extends coreActivity {

    private Business business;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.business = new BusinessMock();
    }

    public void login(View view){

        String login = ((EditText)findViewById(R.id.loginName)).getText().toString();
        String passwd = ((EditText)findViewById(R.id.psswd)).getText().toString();
        int uType;
        RadioGroup rGroup = (RadioGroup)findViewById(R.id.loginInitRadioGroup);
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

        if(business.login(login,passwd,uType)){
            Intent intent = new Intent(this, TeacherHomeActivity.class);
            intent.putExtra(TeacherHomeActivity.EXTRA_LOGIN, login);
            startActivity(intent);
        }
    }
}

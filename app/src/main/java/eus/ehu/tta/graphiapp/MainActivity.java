package eus.ehu.tta.graphiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Business negocio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        negocio = new RealBusiness();
    }

    public void goToLogin(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void register(View view)
    {
        final int tipoUsuario = checkRadioButtons();
        if (tipoUsuario == -1)
        {
            Toast.makeText(this,R.string.errorLoginRadioButtons, Toast.LENGTH_SHORT).show();
        }

        else
        {
            final EditText nombreEditText = findViewById(R.id.nameEditText);
            final String nombre = nombreEditText.getText().toString();

            final EditText apellidoEditText = findViewById(R.id.surnameEditText);
            final String apellidos = apellidoEditText.getText().toString();

            final EditText passwordEditText = findViewById(R.id.passwordEditText);
            final String password = passwordEditText.getText().toString();

            new ProgressTask<String>(this){
                @Override
                protected String work(){
                    String nickname = negocio.register(nombre, apellidos, password, tipoUsuario);
                    return nickname;
                }

                protected void onFinish(String result){
                    if (!result.equals(""))
                    {
                        String showNickname = getResources().getString(R.string.registerCompleted);
                        showNickname = showNickname.concat(" " + result);

                        Toast.makeText(MainActivity.this,showNickname, Toast.LENGTH_LONG).show();
                        nombreEditText.setText("");
                        apellidoEditText.setText("");
                        passwordEditText.setText("");
                        RadioGroup rg = (RadioGroup)findViewById(R.id.loginRadioGroup);
                        rg.clearCheck();
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }

                    else
                    {
                        Toast.makeText(MainActivity.this,R.string.registerError, Toast.LENGTH_LONG).show();
                    }
                }
            }.execute();
        }
    }

    private int checkRadioButtons() {
        int tipoUsuario;
        RadioGroup radioGroup = findViewById(R.id.loginRadioGroup);
        int idRadioButtonChecked = radioGroup.getCheckedRadioButtonId();

        if (idRadioButtonChecked == R.id.alumnRadioButton)
        {
            tipoUsuario = Business.TIPO_ALUMNO;
        }
        else if (idRadioButtonChecked == R.id.teacherRadioButton)
        {
            tipoUsuario = Business.TIPO_PROFESOR;
        }
        else
        {
            tipoUsuario = -1;
        }

        return tipoUsuario;
    }
}

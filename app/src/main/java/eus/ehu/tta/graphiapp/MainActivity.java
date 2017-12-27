package eus.ehu.tta.graphiapp;

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
        negocio = new BusinessMock();
    }

    public void register(View view)
    {
        int tipoUsuario = checkRadioButtons();
        if (tipoUsuario == -1)
        {
            Toast.makeText(this,R.string.errorLoginRadioButtons, Toast.LENGTH_SHORT).show();
        }

        else
        {
            EditText nombreEditText = findViewById(R.id.nameEditText);
            String nombre = nombreEditText.getText().toString();

            EditText apellidoEditText = findViewById(R.id.surnameEditText);
            String apellidos = apellidoEditText.getText().toString();

            EditText passwordEditText = findViewById(R.id.passwordEditText);
            String password = passwordEditText.getText().toString();

            String nickname = negocio.register(nombre, apellidos, password, tipoUsuario);

            if (!nickname.equals(""))
            {
                String showNickname = getResources().getString(R.string.registerCompleted);
                showNickname = showNickname.concat(" " + nickname);

                Toast.makeText(this,showNickname, Toast.LENGTH_SHORT).show();
            }

            else
            {
                Toast.makeText(this,R.string.registerError, Toast.LENGTH_SHORT).show();
            }
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
        if (idRadioButtonChecked == R.id.alumnRadioButton)
        {
            tipoUsuario = Business.TIPO_ALUMNO;
        }
        else
        {
            tipoUsuario = -1;
        }

        return tipoUsuario;
    }
}

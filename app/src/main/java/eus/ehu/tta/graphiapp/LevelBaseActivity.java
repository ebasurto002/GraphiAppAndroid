package eus.ehu.tta.graphiapp;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LevelBaseActivity <T> extends drawerStudentActivity {

    private static final String INDEX = "index";
    private static final String CORRECTAS = "correctas";
    private static final String LEVELARRAY = "levelArray";
    protected int index;
    protected int correctas;
    protected T[] levelArray;
    protected String nickname;
    protected Integer pin;
    protected StudentData studentData;
    protected double [] puntuacionesArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        studentData = new StudentData(this);
        nickname = studentData.getNickname();
        Intent intent = getIntent();
        int pinExtra = intent.getIntExtra("pin",-1);
        if (pinExtra == -1) {
            pin = null;
        }
        else
        {
            pin = pinExtra;
        }

        if (savedInstanceState == null) {
            index = 0;
            correctas = 0;
        }

        else
        {
            index = savedInstanceState.getInt(INDEX);
            correctas = savedInstanceState.getInt(CORRECTAS);;
            levelArray = (T []) savedInstanceState.getParcelableArray(LEVELARRAY);
        }
    }

    public void onSaveInstanceState (Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(INDEX,index);
        savedInstanceState.putInt(CORRECTAS,correctas);
        savedInstanceState.putParcelableArray(LEVELARRAY, (Parcelable[]) levelArray);
    }

    protected void endLevel(int numNivel) {
        float puntuacion = (float)(correctas*10)/(float)levelArray.length;
        Toast.makeText(this,"Tu puntuacion es " + puntuacion, Toast.LENGTH_SHORT).show();

        float puntuacionGuardada = studentData.getResultado(numNivel);
        if (pin == null) {
            if (puntuacionGuardada < puntuacion)
            {
                studentData.setResultado(puntuacion, numNivel);
            }
            goBack(null);
        }
    }

    public void goBack(View view) {
        Intent intent = new Intent(this,LevelsActivity.class);
        startActivity(intent);
    }
}

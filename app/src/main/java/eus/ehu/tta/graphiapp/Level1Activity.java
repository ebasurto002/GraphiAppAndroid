package eus.ehu.tta.graphiapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import eus.ehu.tta.graphiapp.Levels.Nivel1;

public class Level1Activity extends drawerStudentActivity {

    private int index;
    private int correctas;
    private Nivel1[] levelArray;
    private String nickname;
    private Integer pin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout frameLayout = findViewById(R.id.content_frame);
        LayoutInflater inflater = LayoutInflater.from(this);
        inflater.inflate(R.layout.activity_level1,frameLayout,true);

        StudentData studentData = StudentData.getInstance();
        nickname = studentData.getNickname();
        pin = null;

        index=0;
        correctas=0;

        Button button1 = findViewById(R.id.level1word1);
        Button button2 = findViewById(R.id.level1word2);
        button1.setOnClickListener(new buttonOnClickListener(1));
        button2.setOnClickListener(new buttonOnClickListener(2));

        new getLevelTask(this).execute();
    }

    private void setTextButtons() {
        Button button1 = findViewById(R.id.level1word1);
        Button button2 = findViewById(R.id.level1word2);
        button1.setText(levelArray[index].getPalabra1());
        button2.setText(levelArray[index].getPalabra2());
    }

    public void goBack(View view) {
        Intent intent = new Intent(this,LevelsActivity.class);
        startActivity(intent);
    }

    private class buttonOnClickListener implements View.OnClickListener
    {
        int posicion;
        public buttonOnClickListener(int posicion)
        {
            this.posicion=posicion;
        }

        @Override
        public void onClick(View view) {
            if (posicion == levelArray[index].getCorrecta())
            {
                correctas++;
            }
            index++;
            if (index >= levelArray.length)
            {
                endLevel(1);
            }

            else
            {
                setTextButtons();
            }
        }
    }

    private void endLevel(int numNivel) {
        float puntuacion = (float)(correctas*10)/(float)levelArray.length;
        Toast.makeText(this,"Tu puntuacion es " + puntuacion, Toast.LENGTH_SHORT).show();

        SharedPreferences sharedPrefs = getSharedPreferences("eus.ehu.tta.graphiapp." + nickname, Context.MODE_PRIVATE);
        float puntuacionGuardada = sharedPrefs.getFloat("puntuacionNivel" + String.valueOf(numNivel),-1);
        if (puntuacionGuardada < puntuacion) //TODO: Considerar desbloqueo de los niveles
        {
            sharedPrefs.edit().putFloat("puntuacionNivel" + String.valueOf(numNivel),puntuacion).apply();
        }
        goBack(null);
    }

    private class getLevelTask extends ProgressTask<Nivel1[]>
    {

        public getLevelTask(Context context) {
            super(context);
        }

        @Override
        protected Nivel1[] work() throws Exception {
            return business.getNivel1(nickname,pin);
        }

        @Override
        protected void onFinish(Nivel1[] result) {
            levelArray = result;
            if (levelArray != null) {
                setTextButtons();
                Button button1 = findViewById(R.id.level1word1);
                Button button2 = findViewById(R.id.level1word2);
                button1.setEnabled(true);
                button2.setEnabled(true);
            }
            else
            {
                Toast.makeText(Level1Activity.this, "No se ha podido obtener los ejercicios del servidor",Toast.LENGTH_LONG).show();
            }
        }
    }
}

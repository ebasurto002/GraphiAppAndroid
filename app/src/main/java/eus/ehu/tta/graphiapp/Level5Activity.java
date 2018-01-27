package eus.ehu.tta.graphiapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import eus.ehu.tta.graphiapp.Levels.Nivel5;

public class Level5Activity extends drawerStudentActivity {

    private int index;
    private int correctas;
    private Nivel5[] levelArray;
    private String nickname;
    private Integer pin;
    StudentData studentData;
    double [] puntuacionesArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout frameLayout = findViewById(R.id.content_frame);
        LayoutInflater inflater = LayoutInflater.from(this);
        inflater.inflate(R.layout.activity_level5,frameLayout,true);

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
            Button button = findViewById(R.id.level5BackButton);
            button.setEnabled(false);
            puntuacionesArray = intent.getDoubleArrayExtra("puntuacionesArray");
        }

        index=0;
        correctas=0;

        new getLevelTask(this).execute();
    }

    private void setViews() {
        TextView word1View = findViewById(R.id.level5Word1);
        TextView word2View = findViewById(R.id.level5Word2);
        if (Math.random()<0.5) {
            word1View.setText(levelArray[index].getPalabra1());
            word2View.setText(levelArray[index].getPalabra2());
        }

        else
        {
            word1View.setText(levelArray[index].getPalabra2());
            word2View.setText(levelArray[index].getPalabra1());
        }

        String[] sentence1split = splitSentence(levelArray[index].getFrase1());
        TextView textViewSentence1Part1 = findViewById(R.id.level5Sentence1Part1);
        textViewSentence1Part1.setText(sentence1split[0]);
        TextView textViewSentence1Part2 = findViewById(R.id.level5Sentence1Part2);
        textViewSentence1Part2.setText(sentence1split[1]);

        String[] sentence2split = splitSentence(levelArray[index].getFrase2());
        TextView textViewSentence2Part1 = findViewById(R.id.level5Sentence2Part1);
        textViewSentence2Part1.setText(sentence2split[0]);
        TextView textViewSentence2Part2 = findViewById(R.id.level5Sentence2Part2);
        textViewSentence2Part2.setText(sentence2split[1]);

        ((EditText) findViewById(R.id.level5EditText1)).setText("");
        ((EditText) findViewById(R.id.level5EditText2)).setText("");
    }

    private String[] splitSentence(String sentence) {
        String [] split = new String [2];
        if (sentence.startsWith("***"))
        {
            split[0]="";
            split[1]=sentence.replace("***","");
        }
        else if(sentence.endsWith("***"))
        {
            split[0]=sentence.replace("***","");;
            split[1]="";
        }
        else
        {
            split = sentence.split("\\*\\*\\*");
        }

        return split;
    }

    public void goBack(View view) {
        Intent intent = new Intent(this,LevelsActivity.class);
        startActivity(intent);
    }

    public void checkWords(View view) {
        EditText editText1 = findViewById(R.id.level5EditText1);
        EditText editText2 = findViewById(R.id.level5EditText2);

        if (editText1.getText().toString().equals(levelArray[index].getPalabra1())&& editText2.getText().toString().equals(levelArray[index].getPalabra2()))
        {
            correctas++;
        }
        index++;
        if (index >= levelArray.length)
        {
            endLevel(5);
        }

        else
        {
            setViews();
        }
    }

    private void endLevel(int numNivel) {
        float puntuacion = (float)(correctas*10)/(float)levelArray.length;
        Toast.makeText(this,"Tu puntuacion es " + puntuacion, Toast.LENGTH_SHORT).show();

        float puntuacionGuardada = studentData.getResultado(numNivel);
        if (pin == null) {
            if (puntuacionGuardada < puntuacion) //TODO: Considerar desbloqueo de los niveles
            {
                studentData.setResultado(puntuacion, numNivel);
            }
            goBack(null);
        }
        else
        {
            puntuacionesArray[4] = puntuacion;
            Intent intent = new Intent(this,Level8Activity.class);
            intent.putExtra("pin",pin);
            intent.putExtra("puntuacionesArray",puntuacionesArray);
            startActivity(intent);
        }
    }

    private class getLevelTask extends ProgressTask<Nivel5[]>
    {

        public getLevelTask(Context context) {
            super(context);
        }

        @Override
        protected Nivel5[] work() throws Exception {
            return business.getNivel5(nickname,pin);
        }

        @Override
        protected void onFinish(Nivel5[] result) {
            levelArray = result;
            if (levelArray != null) {
                setViews();
                Button confirmButton = findViewById(R.id.level5ConfirmButton);
                confirmButton.setEnabled(true);
            }
            else
            {
                Toast.makeText(Level5Activity.this, "No se ha podido obtener los ejercicios del servidor",Toast.LENGTH_LONG).show();
            }
        }
    }
}
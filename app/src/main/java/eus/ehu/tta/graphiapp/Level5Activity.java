package eus.ehu.tta.graphiapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout frameLayout = findViewById(R.id.content_frame);
        LayoutInflater inflater = LayoutInflater.from(this);
        inflater.inflate(R.layout.activity_level5,frameLayout,true);

        levelArray = business.getNivel5(null);
        index=0;
        correctas=0;

        setViews();
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
            endLevel();
        }

        else
        {
            setViews();
        }
    }

    private void endLevel() {
        float puntuacion = (float)(correctas*10)/(float)levelArray.length;
        Toast.makeText(this,"Tu puntuacion es " + puntuacion, Toast.LENGTH_SHORT).show();
        goBack(null);
    }
}

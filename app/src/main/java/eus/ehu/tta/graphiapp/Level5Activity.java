package eus.ehu.tta.graphiapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import eus.ehu.tta.graphiapp.Levels.Nivel5;

public class Level5Activity extends LevelBaseActivity <Nivel5> {

    private static final String ORDER = "order";
    private int order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout frameLayout = findViewById(R.id.content_frame);
        LayoutInflater inflater = LayoutInflater.from(this);
        inflater.inflate(R.layout.activity_level5,frameLayout,true);

        if (pin != null)
        {
            Button button = findViewById(R.id.levelBackButton);
            button.setEnabled(false);
            puntuacionesArray = getIntent().getDoubleArrayExtra("puntuacionesArray");
        }

        if (savedInstanceState == null)
        {
            new getLevelTask(this).execute();
        }

        else
        {
            order = savedInstanceState.getInt(ORDER);
            setViews();
            Button confirmButton = findViewById(R.id.level5ConfirmButton);
            confirmButton.setEnabled(true);
        }
    }

    public void onSaveInstanceState (Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(ORDER,order);
    }

    private void setViews() {
        TextView word1View = findViewById(R.id.level5Word1);
        TextView word2View = findViewById(R.id.level5Word2);
        if (order<0.5) {
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
    }

    private void changeViews ()
    {
        order = (int) Math.round(Math.random());
        setViews();
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
            changeViews();
        }
    }

    protected void endLevel(int numNivel) {
        super.endLevel(numNivel);
        if (pin != null)
        {
            puntuacionesArray[4] = (float)(correctas*10)/(float)levelArray.length;
            goToNextLevel();
        }
    }

    private void goToNextLevel() {
        Intent intent = new Intent(this,Level8Activity.class);
        intent.putExtra("pin",pin);
        intent.putExtra("puntuacionesArray",puntuacionesArray);
        startActivity(intent);
        this.finish();
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
                if (levelArray.length != 0) {
                    order = (int) Math.round(Math.random());
                    setViews();
                    Button confirmButton = findViewById(R.id.level5ConfirmButton);
                    confirmButton.setEnabled(true);
                }
                else
                {
                    puntuacionesArray[4] = -1;
                    goToNextLevel();
                }
            }
            else
            {
                Toast.makeText(Level5Activity.this, "No se ha podido obtener los ejercicios del servidor",Toast.LENGTH_LONG).show();
            }
        }
    }
}
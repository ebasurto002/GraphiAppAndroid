package eus.ehu.tta.graphiapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import eus.ehu.tta.graphiapp.Levels.Nivel1;

public class Level1Activity extends LevelBaseActivity <Nivel1> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout frameLayout = findViewById(R.id.content_frame);
        LayoutInflater inflater = LayoutInflater.from(this);
        inflater.inflate(R.layout.activity_level1,frameLayout,true);

        if (pin != null)
        {
            Button button = findViewById(R.id.levelBackButton);
            button.setEnabled(false);
            puntuacionesArray = new double[6];
        }

        Button button1 = findViewById(R.id.level1word1);
        Button button2 = findViewById(R.id.level1word2);

        if (savedInstanceState == null) {
            new getLevelTask(this).execute();
        }

        else
        {
            button1.setEnabled(true);
            button2.setEnabled(true);
            setTextButtons();
        }

        button1.setOnClickListener(new buttonOnClickListener(1));
        button2.setOnClickListener(new buttonOnClickListener(2));
    }

    private void setTextButtons() {
        Button button1 = findViewById(R.id.level1word1);
        Button button2 = findViewById(R.id.level1word2);
        button1.setText(levelArray[index].getPalabra1());
        button2.setText(levelArray[index].getPalabra2());
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

    protected void endLevel(int numNivel) {
        super.endLevel(numNivel);
        if (pin != null)
        {
            puntuacionesArray[0] = (float)(correctas*10)/(float)levelArray.length;
            Intent intent = new Intent(this,Level2Activity.class);
            intent.putExtra("pin",pin);
            intent.putExtra("puntuacionesArray",puntuacionesArray);
            startActivity(intent);
        }
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

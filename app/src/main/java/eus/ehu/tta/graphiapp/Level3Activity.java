package eus.ehu.tta.graphiapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import eus.ehu.tta.graphiapp.Levels.Nivel3;

public class Level3Activity extends LevelBaseActivity<Nivel3> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout frameLayout = findViewById(R.id.content_frame);
        LayoutInflater inflater = LayoutInflater.from(this);
        inflater.inflate(R.layout.activity_level3,frameLayout,true);

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
            setViews();
            setButtons();
        }
    }

    private void setButtons() {
        Button button1 = findViewById(R.id.level3Word1);
        button1.setEnabled(true);
        button1.setOnClickListener(new ButtonOnClickListener(1));
        Button button2 = findViewById(R.id.level3Word2);
        button2.setOnClickListener(new ButtonOnClickListener(2));
        button2.setEnabled(true);
    }

    private void setViews() {
        ImageView imageView = findViewById(R.id.level3Image);
        Glide.with(this).load(levelArray[index].getUrlImagen()).into(imageView);
        Button button1 = findViewById(R.id.level3Word1);
        button1.setText(levelArray[index].getPalabra1());
        Button button2 = findViewById(R.id.level3Word2);
        button2.setText(levelArray[index].getPalabra2());
    }

    private class ButtonOnClickListener implements View.OnClickListener {
        int position;
        public ButtonOnClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View view) {
            if (position == levelArray[index].getCorrecta())
            {
                correctas++;
            }
            index++;
            if (index >= levelArray.length)
            {
                endLevel(3);
            }
            else
            {
                setViews();
            }
        }
    }

    protected void endLevel(int numNivel) {
        super.endLevel(numNivel);
        if (pin != null)
        {
            puntuacionesArray[2] = (float)(correctas*10)/(float)levelArray.length;
            goToNextLevel();
        }
    }

    private void goToNextLevel() {
        Intent intent = new Intent(this,Level4Activity.class);
        intent.putExtra("pin",pin);
        intent.putExtra("puntuacionesArray",puntuacionesArray);
        startActivity(intent);
        this.finish();
    }


    private class getLevelTask extends ProgressTask<Nivel3[]>
    {

        public getLevelTask(Context context) {
            super(context);
        }

        @Override
        protected Nivel3[] work() throws Exception {
            return business.getNivel3(nickname,pin);
        }

        @Override
        protected void onFinish(Nivel3[] result) {
            levelArray = result;
            if (levelArray != null) {
                if (levelArray.length != 0) {
                    setViews();
                    setButtons();
                }
                else
                {
                    puntuacionesArray[2] = -1;
                    goToNextLevel();
                }
            }
            else
            {
                Toast.makeText(Level3Activity.this, "No se ha podido obtener los ejercicios del servidor",Toast.LENGTH_LONG).show();
            }
        }
    }
}

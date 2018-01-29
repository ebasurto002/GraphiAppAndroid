package eus.ehu.tta.graphiapp;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;
import eus.ehu.tta.graphiapp.Levels.Nivel2;

public class Level2Activity extends LevelBaseActivity <Nivel2>  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout frameLayout = findViewById(R.id.content_frame);
        LayoutInflater inflater = LayoutInflater.from(this);
        inflater.inflate(R.layout.activity_level2,frameLayout,true);

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
            setButtons();
            Button button = findViewById(R.id.level2HearAudio);
            button.setEnabled(true);
        }
    }


    private void setButtons() {
        LinearLayout linearLayout = findViewById(R.id.layoutLevel2Word);
        linearLayout.removeAllViews();
        int position = 0;
        for(char character : levelArray[index].getPalabra().toCharArray())
        {
            position++;
            Button button = new Button(this);
            button.setText(String.valueOf(character));
            button.setOnClickListener(new buttonOnClickListener(position));
            button.setLayoutParams(new LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,1
            ));
            linearLayout.addView(button);
        }

    }

    public void playAudio(View view) {
        MediaPlayer audioPlayer = MediaPlayer.create(this, Uri.parse(levelArray[index].getAudio()));
        audioPlayer.start();
        audioPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.release();
            }
        });
    }

    private class buttonOnClickListener implements View.OnClickListener {
        int position;
        public buttonOnClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View view) {
            if (position == levelArray[index].getTildada())
            {
                correctas++;
            }
            index++;
            if (index >= levelArray.length)
            {
                endLevel(2);
            }

            else
            {
                setButtons();
            }
        }
    }

    protected void endLevel(int numNivel) {
        super.endLevel(numNivel);
        if (pin != null)
        {
            puntuacionesArray[1] = (float)(correctas*10)/(float)levelArray.length;
            goToNextLevel();
        }
    }

    private class getLevelTask extends ProgressTask<Nivel2[]>
    {

        public getLevelTask(Context context) {
            super(context);
        }

        @Override
        protected Nivel2[] work() throws Exception {
            return business.getNivel2(nickname,pin);
        }

        @Override
        protected void onFinish(Nivel2[] result) {
            levelArray = result;
            if (levelArray != null) {
                if (levelArray.length != 0) {
                    setButtons();
                    Button button = findViewById(R.id.level2HearAudio);
                    button.setEnabled(true);
                }
                else
                {
                    puntuacionesArray[1] = -1;
                    goToNextLevel();
                }
            }
            else
            {
                Toast.makeText(Level2Activity.this, "No se ha podido obtener los ejercicios del servidor",Toast.LENGTH_LONG).show();
            }
        }
    }

    private void goToNextLevel()
    {
        Intent intent = new Intent(this,Level3Activity.class);
        intent.putExtra("pin",pin);
        intent.putExtra("puntuacionesArray",puntuacionesArray);
        startActivity(intent);
    }
}

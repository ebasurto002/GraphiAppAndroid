package eus.ehu.tta.graphiapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import eus.ehu.tta.graphiapp.Levels.Nivel2;

public class Level2Activity extends drawerStudentActivity {

    private int index;
    private int correctas;
    private Nivel2[] levelArray;
    private String nickname;
    private Integer pin;
    StudentData studentData;
    double [] puntuacionesArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout frameLayout = findViewById(R.id.content_frame);
        LayoutInflater inflater = LayoutInflater.from(this);
        inflater.inflate(R.layout.activity_level2,frameLayout,true);

        studentData = new StudentData(this);
        nickname = studentData.getNickname();
        Intent intent = getIntent();
        int pinExtra = intent.getIntExtra("pin",-1);
        if (pinExtra == -1) {
            pin = null;;
        }
        else
        {
            pin = pinExtra;
            Button button = findViewById(R.id.level2BackButton);
            button.setEnabled(false);
            puntuacionesArray = intent.getDoubleArrayExtra("puntuacionesArray");
        }
        index=0;
        correctas=0;

        new getLevelTask(this).execute();
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

    public void goBack(View view) {
        Intent intent = new Intent(this,LevelsActivity.class);
        startActivity(intent);
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
            puntuacionesArray[1] = puntuacion;
            Intent intent = new Intent(this,Level3Activity.class);
            intent.putExtra("pin",pin);
            intent.putExtra("puntuacionesArray",puntuacionesArray);
            startActivity(intent);
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
                setButtons();
                Button button = findViewById(R.id.level2HearAudio);
                button.setEnabled(true);
            }
            else
            {
                Toast.makeText(Level2Activity.this, "No se ha podido obtener los ejercicios del servidor",Toast.LENGTH_LONG).show();
            }
        }
    }
}

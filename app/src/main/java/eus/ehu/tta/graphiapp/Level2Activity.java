package eus.ehu.tta.graphiapp;

import android.content.Intent;
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
    private Business business;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout frameLayout = findViewById(R.id.content_frame);
        LayoutInflater inflater = LayoutInflater.from(this);
        inflater.inflate(R.layout.activity_level2,frameLayout,true);

        levelArray = business.getNivel2(null);
        index=0;
        correctas=0;

        setButtons();
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
                endLevel();
            }

            else
            {
                setButtons();
            }
        }
    }

    private void endLevel() {
        float puntuacion = (float)(correctas*10)/(float)levelArray.length;
        Toast.makeText(this,"Tu puntuacion es " + puntuacion, Toast.LENGTH_SHORT).show();
        goBack(null);
    }
}

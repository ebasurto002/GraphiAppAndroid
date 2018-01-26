package eus.ehu.tta.graphiapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import eus.ehu.tta.graphiapp.Levels.Nivel4;

public class Level4Activity extends drawerStudentActivity {

    private static final String TAG = "Level4Activity";
    private int index;
    private int correctas;
    private Nivel4[] levelArray;
    private String nickname;
    private Integer pin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout frameLayout = findViewById(R.id.content_frame);
        LayoutInflater inflater = LayoutInflater.from(this);
        inflater.inflate(R.layout.activity_level4, frameLayout, true);

        StudentData studentData = StudentData.getInstance();
        nickname = studentData.getNickname();
        pin = null;

        index = 0;
        correctas = 0;

        new getLevelTask(this).execute();
    }

    private void setNewspaperHeader() {
        int position = 0;
        int start = 0;
        TextView headerTextView = findViewById(R.id.newspaperHeaderTextView);
        SpannableString ss = new SpannableString(levelArray[index].getTitular());
        for (String palabra : levelArray[index].getTitular().split(" ")) {
            position++;
            ClickableSpan clickableSpan = new wordClickableSpan(position);
            int end = start + palabra.length();
            ss.setSpan(clickableSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            start = end + 1; //+1 por espacio en blanco
        }
        headerTextView.setText(ss);
        headerTextView.setTextSize(20);
        headerTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private class wordClickableSpan extends ClickableSpan {
        private int position;
        private wordClickableSpan(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View textView) {
            Log.d(TAG,"Click: " + position);
            if (position == levelArray[index].getIncorrecta())
            {
                correctas++;
            }
            index++;
            if (index >= levelArray.length)
            {
                endLevel(4);
            }
            else
            {
                setNewspaperHeader();
            }
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setUnderlineText(false);
            ds.setColor(Color.BLACK);
        }
    }

    public void goBack(View view) {
        Intent intent = new Intent(this,LevelsActivity.class);
        startActivity(intent);
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

    private class getLevelTask extends ProgressTask<Nivel4[]>
    {

        public getLevelTask(Context context) {
            super(context);
        }

        @Override
        protected Nivel4[] work() throws Exception {
            return business.getNivel4(nickname,pin);
        }

        @Override
        protected void onFinish(Nivel4[] result) {
            levelArray = result;
            if (levelArray != null) {
                setNewspaperHeader();
            }
            else
            {
                Toast.makeText(Level4Activity.this, "No se ha podido obtener los ejercicios del servidor",Toast.LENGTH_LONG).show();
            }
        }
    }
}

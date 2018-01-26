package eus.ehu.tta.graphiapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import eus.ehu.tta.graphiapp.Levels.Nivel3;

public class Level3Activity extends drawerStudentActivity {

    private int index;
    private int correctas;
    private Nivel3[] levelArray;
    private String nickname;
    private Integer pin;
    StudentData studentData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout frameLayout = findViewById(R.id.content_frame);
        LayoutInflater inflater = LayoutInflater.from(this);
        inflater.inflate(R.layout.activity_level3,frameLayout,true);

        studentData = new StudentData(this);
        nickname = studentData.getNickname();
        pin = null;

        index=0;
        correctas=0;

        new getLevelTask(this).execute();
    }

    private void setViews() {
        ImageView imageView = findViewById(R.id.level3Image);
        Glide.with(this).load(levelArray[index].getUrlImagen()).into(imageView);
        Button button1 = findViewById(R.id.level3Word1);
        button1.setText(levelArray[index].getPalabra1());
        Button button2 = findViewById(R.id.level3Word2);
        button2.setText(levelArray[index].getPalabra2());
    }

    public void goBack(View view) {
        Intent intent = new Intent(this,LevelsActivity.class);
        startActivity(intent);
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

    private void endLevel(int numNivel) {
        float puntuacion = (float)(correctas*10)/(float)levelArray.length;
        Toast.makeText(this,"Tu puntuacion es " + puntuacion, Toast.LENGTH_SHORT).show();

        float puntuacionGuardada = studentData.getResultado(numNivel);
        if (puntuacionGuardada < puntuacion) //TODO: Considerar desbloqueo de los niveles
        {
            studentData.setResultado(puntuacion,numNivel);
        }
        goBack(null);
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
                setViews();
                Button button1 = findViewById(R.id.level3Word1);
                button1.setEnabled(true);
                button1.setOnClickListener(new ButtonOnClickListener(1));
                Button button2 = findViewById(R.id.level3Word2);
                button2.setOnClickListener(new ButtonOnClickListener(2));
                button2.setEnabled(true);
            }
            else
            {
                Toast.makeText(Level3Activity.this, "No se ha podido obtener los ejercicios del servidor",Toast.LENGTH_LONG).show();
            }
        }
    }
}

package eus.ehu.tta.graphiapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ProfileActivity extends drawerStudentActivity {

    private String nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        StudentData studentData = new StudentData(this);
        nickname = studentData.getNickname();
        setPuntuaciones();
        //StudentData.getInstance();
        //Glide.with(this).load()
    }

    private void setPuntuaciones() {
        int [] numeros = {1,2,3,4,5,8};
        SharedPreferences sharedPrefs = getSharedPreferences("eus.ehu.tta.graphiapp." + nickname, Context.MODE_PRIVATE);
        GridLayout gridLayout = findViewById(R.id.puntuacionesGrid);
        gridLayout.setColumnCount(4);
        int rowNum = 0;
        for (int numero : numeros)
        {
            TextView textView = new TextView(this);
            textView.setText(String.format("Nivel %d",numero));
            gridLayout.addView(textView,getLayoutParameters(rowNum,0));

            float puntuacion = sharedPrefs.getFloat("puntuacionNivel" + numero,-1);

            if (puntuacion != -1)
            {
                TextView puntuacionText = new TextView(this);
                puntuacionText.setText(String.valueOf(puntuacion));
                gridLayout.addView(puntuacionText,getLayoutParameters(rowNum,1));
                ProgressBar progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
                progressBar.setProgress((int)(puntuacion*10));
                gridLayout.addView(progressBar,getLayoutParameters(rowNum,2));
            }

            else
            {
                TextView notCompletedText = new TextView(this);
                notCompletedText.setText(getResources().getString(R.string.exerciseNotCompletedYet));
                notCompletedText.setTextColor(Color.RED);
                gridLayout.addView(notCompletedText,getLayoutParameters(rowNum,1));
            }

            rowNum++;
        }
    }

    public GridLayout.LayoutParams getLayoutParameters(int rowNum, int columnNum)
    {
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.height = GridLayout.LayoutParams.WRAP_CONTENT;
        params.width = GridLayout.LayoutParams.WRAP_CONTENT;
        params.rightMargin = 5;
        params.topMargin = 5;
        params.setGravity(Gravity.CENTER);
        params.columnSpec = GridLayout.spec(columnNum);
        params.rowSpec = GridLayout.spec(rowNum);
        return params;
    }

    public void goBack(View view) {
        finish();
    }
}

package eus.ehu.tta.graphiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import eus.ehu.tta.graphiapp.Levels.Nivel4;

public class Level4Activity extends drawerStudentActivity {

    private int index;
    private int correctas;
    private Nivel4[] levelArray;
    private Business business;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout frameLayout = findViewById(R.id.content_frame);
        LayoutInflater inflater = LayoutInflater.from(this);
        inflater.inflate(R.layout.activity_level4,frameLayout,true);

        business = new BusinessMock();
        levelArray = business.getNivel4(null);
        index=0;
        correctas=0;

        setNewspaperHeader();
    }

    private void setNewspaperHeader() {
        int position=0;
        LinearLayout newspaperHeaderLayout = findViewById(R.id.newspaperHeader);
        newspaperHeaderLayout.removeAllViews();
        for (String palabra : levelArray[index].getTitular().split(" "))
        {
            position++;
            Button button = new Button(this);
            button.setText(palabra);
            button.setOnClickListener(new ButtonOnClickListener(position));
            button.setLayoutParams(new LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,1
            ));
            newspaperHeaderLayout.addView(button);
        }
    }

    public void goBack(View view) {
        Intent intent = new Intent(this,LevelsActivity.class);
        startActivity(intent);
    }

    private class ButtonOnClickListener implements View.OnClickListener {
        private int position;
        public ButtonOnClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View view) {
            if (position == levelArray[index].getIncorrecta())
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
                setNewspaperHeader();
            }
        }
    }

    private void endLevel() {
        float puntuacion = (float)(correctas*10)/(float)levelArray.length;
        Toast.makeText(this,"Tu puntuacion es " + puntuacion, Toast.LENGTH_SHORT).show();
        goBack(null);
    }
}

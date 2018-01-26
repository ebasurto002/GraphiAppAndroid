package eus.ehu.tta.graphiapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.IOException;

public class ProfileActivity extends drawerStudentActivity {

    private static final int PICTURE_REQUEST_CODE = 0;
    private String nickname;
    StudentData studentData;

    Uri pictureUri;
    File file;
    private String photoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        studentData = new StudentData(this);
        nickname = studentData.getNickname();
        setPuntuaciones();
        studentData = new StudentData(this);
        ImageView imageView = findViewById(R.id.profilePhoto);
        Uri uriFoto = studentData.getUrlFoto();
        if (uriFoto != null)
        {
            Glide.with(this).load(String.valueOf(uriFoto)).into(imageView);
        }
        else
        {
            imageView.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.addphoto));
        }
        TextView nameText = findViewById(R.id.userNameText);
        nameText.setText(nickname);
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

    public void changeProfilePhoto(View view) {
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            Toast.makeText(this, R.string.noCamera, Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (intent.resolveActivity(getPackageManager()) != null) {
                File dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                File file = null;
                try {
                    file = File.createTempFile("profilePhoto_" + nickname,".jpg",dir);
                    pictureUri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".eus.ehu.tta.ttaejemplo.provider", file);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, pictureUri);
                    startActivityForResult(intent, PICTURE_REQUEST_CODE);
                    photoPath = file.getAbsolutePath();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(this, R.string.no_app, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        Uri uri;
        String filename;

        if (resultCode != Activity.RESULT_OK)
            return;
        switch (requestCode)
        {
            case PICTURE_REQUEST_CODE:
                studentData.setUrlFoto(Uri.parse(photoPath));
                ImageView imageView = findViewById(R.id.profilePhoto);
                Glide.with(this).load(photoPath).into(imageView);
                imageView.setImageURI(Uri.parse(photoPath));
                break;
        }
    }
}

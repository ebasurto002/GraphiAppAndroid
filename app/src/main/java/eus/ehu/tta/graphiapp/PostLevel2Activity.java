package eus.ehu.tta.graphiapp;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PostLevel2Activity extends coreActivity {

    private static final int EXTERNAL_STORAGE_FOR_AUDIO = 0;
    protected String filename;
    protected static final int AUDIO_REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_level2);
    }



    public void audioRec(){
        filename = ((EditText)findViewById(R.id.unstressedWord)).getText().toString() + ".amr";
        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_MICROPHONE)){
            Toast.makeText(this,R.string.no_mic,Toast.LENGTH_SHORT);
        }
        else{
            Intent intent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
            if(intent.resolveActivity(getPackageManager()) != null){
                startActivityForResult(intent, AUDIO_REQUEST_CODE);
            }
            else{
                Toast.makeText(this, R.string.no_app, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void addL2Exercise(View view){
        final String noStressedWord = ((EditText)findViewById(R.id.unstressedWord)).getText().toString();
        final int stressPos = Integer.parseInt(((EditText)findViewById(R.id.stressPos)).getText().toString());
        final String login = TeacherData.getInstance().getLogin();
        final Button btn = findViewById(R.id.l2AddBtn);
        btn.setEnabled(false);

        new ProgressTask<String>(this){

            @Override
            protected String work() {

                try{
                    return business.postLevel2(noStressedWord,stressPos,filename,login);
                }
                catch(Exception e){

                }
                return null;
            }

            @Override
            protected void onFinish(String result) {
                if(result.contains("Error")){
                    Toast.makeText(PostLevel2Activity.this,R.string.onPostLevel1Fail,Toast.LENGTH_SHORT).show();
                    btn.setEnabled(true);
                }
                else{
                    btn.setEnabled(true);
                    Toast.makeText(PostLevel2Activity.this,R.string.onPostLevel1Success,Toast.LENGTH_SHORT).show();
                    ((EditText) findViewById(R.id.stressPos)).setText("");
                    ((EditText) findViewById(R.id.unstressedWord)).setText("");
                }
            }
        }.execute();
    }

    public void level3(View view){
        Intent intent = new Intent(this, PostLevel3Activity.class);
        startActivity(intent);
    }

    public void level1(View view){
        Intent intent = new Intent(this, PostLevel1Activity.class);
        startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode != Activity.RESULT_OK){
            return;
        }
        final Uri uri = data.getData();

        new ProgressTask<Boolean>(this){

            @Override
            protected Boolean work() {
                try{
                    return business.uploadFile(uri,PostLevel2Activity.this,filename);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onFinish(Boolean result) {
                if(result.booleanValue() == true){
                    Toast.makeText(PostLevel2Activity.this, R.string.onFileUploadSuccess,Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(PostLevel2Activity.this, R.string.onFileUploadFail, Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();

    }

    private boolean checkAncRequestPerms(Context context, Activity activity, String permission, int requestCode){
        if(ContextCompat.checkSelfPermission(context,permission) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(activity, new String[]{permission},requestCode);
            return false;
        }
        return true;
    }

    public void onRecAudioClick(View view){
        if(checkAncRequestPerms(this,this,Manifest.permission.READ_EXTERNAL_STORAGE,EXTERNAL_STORAGE_FOR_AUDIO)){
            audioRec();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults){
        if(requestCode == EXTERNAL_STORAGE_FOR_AUDIO){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                audioRec();
            }
        }
    }

}

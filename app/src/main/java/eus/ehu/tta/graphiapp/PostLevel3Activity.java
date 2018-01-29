package eus.ehu.tta.graphiapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PostLevel3Activity extends coreActivity {

    private static final int READ_REQUEST_CODE = 2;
    protected String filename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_level3);
    }

    public void addL3Exercise(View view){

        String correcta = ((EditText)findViewById(R.id.cWordL3)).getText().toString();
        String incorrecta = ((EditText)findViewById(R.id.iWordL3)).getText().toString();
        filename = correcta + ".jpg";
        String remoteURL = "http://u017633.ehu.eus:28080/static/GraphiAppServer/img/"+filename;

        boolean result = business.postLevel3(this,correcta,incorrecta,remoteURL);
        if(result == true){
            Toast.makeText(this, R.string.onPostLevel1Success, Toast.LENGTH_SHORT).show();
            ((EditText)findViewById(R.id.cWordL3)).setText("");
            ((EditText)findViewById(R.id.iWordL3)).setText("");
        }
        else{
            Toast.makeText(this, R.string.onPostLevel1Fail, Toast.LENGTH_SHORT).show();
        }

    }

    public void level4(View view){
        Intent intent  = new Intent(this, PostLevel4Activity.class);
        startActivity(intent);
    }

    public void level2(View view){
        Intent intent = new Intent(this, PostLevel2Activity.class);
        startActivity(intent);
    }

    public void picPicker(View view){
        filename = ((EditText)findViewById(R.id.cWordL3)).getText().toString() + ".jpg";
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/jpeg");
        startActivityForResult(intent,READ_REQUEST_CODE);

    }

    @Override
    public void onActivityResult(int requestCode,int resultCode, Intent data){
        final Uri uri = data.getData();

        new ProgressTask<Boolean>(this){

            @Override
            protected Boolean work(){
                try {
                    return business.uploadFile(uri, PostLevel3Activity.this, filename);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                return false;
            }

            @Override
            protected void onFinish(Boolean result) {
                if(result.booleanValue() == true){
                    Toast.makeText(PostLevel3Activity.this, R.string.onFileUploadSuccess,Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(PostLevel3Activity.this, R.string.onFileUploadFail, Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();

    }
}

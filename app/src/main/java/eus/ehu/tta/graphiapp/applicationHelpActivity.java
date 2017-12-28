package eus.ehu.tta.graphiapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

public class applicationHelpActivity extends drawerBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup viewGroup = findViewById(R.id.content_frame);
        LayoutInflater inflater = LayoutInflater.from(applicationHelpActivity.this);
        View inflatedView = inflater.inflate(R.layout.activity_application_help,viewGroup,false);
        viewGroup.addView(inflatedView);

        VideoView videoView = (VideoView)findViewById(R.id.helpVideoView);
        videoView.setVideoURI(Uri.parse("https://dl.dropboxusercontent.com/s/e94hhon3f6dr6tb/Vídeo%20de%20ayuda%20de%20GraphiApp.mp4?dl=0"));
        videoView.start();
    }

    public void goBack(View view) {
        Intent intent = new Intent(this,userHomeActivity.class);
        startActivity(intent);
    }
}

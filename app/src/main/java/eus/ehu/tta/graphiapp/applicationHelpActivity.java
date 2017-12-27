package eus.ehu.tta.graphiapp;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.VideoView;

public class applicationHelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_help);

        VideoView videoView = (VideoView)findViewById(R.id.helpVideoView);
        videoView.setVideoURI(Uri.parse("https://dl.dropboxusercontent.com/s/e94hhon3f6dr6tb/Vídeo%20de%20ayuda%20de%20GraphiApp.mp4?dl=0"));
        videoView.start();
    }
}

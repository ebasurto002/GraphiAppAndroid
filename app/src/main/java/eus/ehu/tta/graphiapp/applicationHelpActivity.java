package eus.ehu.tta.graphiapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.VideoView;

public class applicationHelpActivity extends drawerStudentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout frameLayout = findViewById(R.id.content_frame);
        LayoutInflater inflater = LayoutInflater.from(applicationHelpActivity.this);
        inflater.inflate(R.layout.activity_application_help,frameLayout,true);

        VideoView videoView = findViewById(R.id.helpVideoView);
        videoView.setVideoURI(Uri.parse("https://dl.dropboxusercontent.com/s/e94hhon3f6dr6tb/Vídeo%20de%20ayuda%20de%20GraphiApp.mp4?dl=0"));
        MediaController controller = new MediaController(this)
        {
            @Override
            public void hide() {
            }

            @Override
            public boolean dispatchKeyEvent(KeyEvent event)
            {
                if (event.getKeyCode() == KeyEvent.KEYCODE_BACK)
                {
                    finish();
                }
                return super.dispatchKeyEvent(event);
            }
        };
        controller.setAnchorView(videoView);
        videoView.setMediaController(controller);
    }

    public void goBack(View view) {
        Intent intent = new Intent(this,userHomeActivity.class);
        startActivity(intent);
    }
}

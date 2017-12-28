package eus.ehu.tta.graphiapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class userHomeActivity extends drawerBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup viewGroup = findViewById(R.id.content_frame);
        LayoutInflater inflater = LayoutInflater.from(userHomeActivity.this);
        View inflatedView = inflater.inflate(R.layout.activity_user_home,viewGroup,false);
        viewGroup.addView(inflatedView);
    }
}

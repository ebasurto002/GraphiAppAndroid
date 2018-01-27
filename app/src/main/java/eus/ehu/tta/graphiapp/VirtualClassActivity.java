package eus.ehu.tta.graphiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

public class VirtualClassActivity extends drawerStudentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout frameLayout = findViewById(R.id.content_frame);
        LayoutInflater inflater = LayoutInflater.from(this);
        inflater.inflate(R.layout.activity_virtual_class,frameLayout,true);
    }

    public void goBack(View view) {
        Intent intent = new Intent(this,GameModesActivity.class);
        startActivity(intent);
    }

    public void startVirtualClass(View view) {
        EditText editText = findViewById(R.id.claseVirtualPinEditText);
        int pin = Integer.valueOf(editText.getText().toString());
        Intent intent = new Intent(this,Level1Activity.class);
        intent.putExtra("pin",pin);
        startActivity(intent);
    }
}

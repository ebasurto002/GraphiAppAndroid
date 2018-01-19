package eus.ehu.tta.graphiapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class coreActivity extends AppCompatActivity {

    private Business business;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        business = new RealBusiness();
    }
}

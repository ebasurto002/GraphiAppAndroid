package eus.ehu.tta.graphiapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class drawerTeacherActivity extends drawerBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
    }
    @Override
    protected String[] getItems() {
        return new String[] {getResources().getString(R.string.closeSession)};
    }
    private class DrawerItemClickListener implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            SharedPreferences defaultSP = getSharedPreferences("eus.ehu.tta.graphiapp.default", Context.MODE_PRIVATE);
            defaultSP.edit().putString("currentNickname",null).apply();
            defaultSP.edit().putBoolean("isTeacher", false).apply();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
            mDrawerLayout.closeDrawer(Gravity.LEFT);
        }
    }
}

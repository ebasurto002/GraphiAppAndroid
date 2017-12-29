package eus.ehu.tta.graphiapp;

import android.os.Bundle;
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
            Toast.makeText(drawerTeacherActivity.this,String.valueOf(i), Toast.LENGTH_SHORT).show();
        }
    }
}

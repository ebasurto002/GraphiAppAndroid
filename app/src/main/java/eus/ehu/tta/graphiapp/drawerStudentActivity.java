package eus.ehu.tta.graphiapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class drawerStudentActivity extends drawerBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
    }

    @Override
    protected String[] getItems() {
        return getResources().getStringArray(R.array.drawer_items);
    }

    private class DrawerItemClickListener implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Toast.makeText(drawerStudentActivity.this,String.valueOf(i), Toast.LENGTH_SHORT).show();
        }
    }
}

package eus.ehu.tta.graphiapp;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public abstract class drawerBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_base);

        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ListView mDrawerList = (ListView) findViewById(R.id.left_drawer);

        String [] stringArray = getItems();
        mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, stringArray));

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        myToolbar.setNavigationIcon(R.drawable.menu);
        myToolbar.setNavigationOnClickListener(new navigationOnClickListener());
    }

    protected abstract String[] getItems();

    private class navigationOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            DrawerLayout mDrawerLayout = findViewById(R.id.drawer_layout);
            if (mDrawerLayout.isDrawerOpen(Gravity.LEFT))
            {
                mDrawerLayout.closeDrawer(Gravity.LEFT);
            }
            else
            {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        }
    }
}

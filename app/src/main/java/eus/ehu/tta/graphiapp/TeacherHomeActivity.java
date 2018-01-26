package eus.ehu.tta.graphiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TeacherHomeActivity extends drawerTeacherActivity {

    public static final String EXTRA_LOGIN = "login";
    public static int CLASS_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout frameLayout = findViewById(R.id.content_frame);
        LayoutInflater inflater = LayoutInflater.from(this);
        inflater.inflate(R.layout.activity_teacher_home, frameLayout, true);
        TeacherData td = TeacherData.getInstance();
        final String login = td.getLogin();
        new ProgressTask<JSONObject>(this) {

            @Override
            protected JSONObject work() {
                try {
                    return business.getClasses(login);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onFinish(JSONObject result) {
                if (result == null) {
                    Toast.makeText(TeacherHomeActivity.this, R.string.onClassRetrieveFail, Toast.LENGTH_SHORT);
                } else {
                    try {
                        if (!result.isNull("listaClases")) {
                            JSONArray jsArray = result.getJSONArray("listaClases");
                            for (int i = 0; i < jsArray.length(); i++) {
                                Button btn = new Button(TeacherHomeActivity.this);
                                btn.setText(jsArray.getJSONObject(i).getString("tematica"));
                                btn.setId(i);
                                btn.setOnClickListener(new OnClickClassBtn(i));
                                LinearLayout ll = findViewById(R.id.tHLayout);
                                ll.addView(btn,i+1);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.execute();
    }

    public void startPosting(View view) {
        EditText className = (EditText) findViewById(R.id.className);
        final String name = className.getText().toString();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("ddMMyyyy");
        String dateString = df.format(calendar.getTime());
        Toast.makeText(this, dateString, Toast.LENGTH_LONG); //De momento, para pruebas del formato de fecha
        final int date = Integer.parseInt(dateString);
        final String login = TeacherData.getInstance().getLogin();
        new ProgressTask<String>(this) {
            @Override
            protected String work() {
                return business.registerClass(name, date, login);
            }

            @Override
            protected void onFinish(String result) {
                if (result.contains("Error")) {
                    Toast.makeText(TeacherHomeActivity.this, R.string.registerClassError, Toast.LENGTH_SHORT).show();
                } else {
                    CLASS_ID = Integer.parseInt(result);
                    TeacherData td = TeacherData.getInstance();
                    td.setIdClase(CLASS_ID);
                    Intent intent = new Intent(TeacherHomeActivity.this, PostLevel1Activity.class);
                    startActivity(intent);
                }
            }
        }.execute();

    }
    protected class OnClickClassBtn implements View.OnClickListener {

        int id;

        public OnClickClassBtn(int i) {
            id = i;
        }

        @Override
        public void onClick(View view) {
            Button btn = findViewById(id);
            String tematica = btn.getText().toString();
            goToResults(tematica);

        }
    }
    public void goToResults(final String className){
        Intent intent = new Intent(this,TrackingActivity.class);
        intent.putExtra(TrackingActivity.CLASS_NAME,className);
        startActivity(intent);
    }
}

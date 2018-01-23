package eus.ehu.tta.graphiapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class TrackingActivity extends coreActivity {

    public static final String CLASS_NAME = "tematica";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);

        new ProgressTask<JSONObject>(this){

            @Override
            protected JSONObject work() {
                try{
                    TeacherData td = TeacherData.getInstance();
                    String login = td.getLogin();
                    String tematica = getIntent().getStringExtra(CLASS_NAME);
                    return business.getResults(login, tematica);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onFinish(JSONObject result) {
                if(result == null){
                    Toast.makeText(TrackingActivity.this,R.string.onResultRetrieveFail,Toast.LENGTH_SHORT).show();
                }
                else{
                    try{
                        if(!result.isNull("listaResultadoJSON")){
                            JSONArray jsArray = result.getJSONArray("listaResultadoJSON");
                            for(int i=0; i<jsArray.length();i++){
                                GridLayout gl = findViewById(R.id.trackingLayout);
                                TextView name = new TextView(TrackingActivity.this);
                                TextView l1Res = new TextView(TrackingActivity.this);
                                TextView l2Res = new TextView(TrackingActivity.this);
                                TextView l3Res = new TextView(TrackingActivity.this);
                                TextView l4Res = new TextView(TrackingActivity.this);
                                TextView l5Res = new TextView(TrackingActivity.this);
                                TextView l8Res = new TextView(TrackingActivity.this);
                                name.setText(jsArray.getJSONObject(i).getString("alumno"));
                                l1Res.setText(jsArray.getJSONObject(i).getString("puntosNivel1"));
                                l2Res.setText(jsArray.getJSONObject(i).getString("puntosNivel2"));
                                l3Res.setText(jsArray.getJSONObject(i).getString("puntosNivel3"));
                                l4Res.setText(jsArray.getJSONObject(i).getString("puntosNivel4"));
                                l5Res.setText(jsArray.getJSONObject(i).getString("puntosNivel5"));
                                l8Res.setText(jsArray.getJSONObject(i).getString("puntosNivel8"));
                                gl.addView(name, 7*(i+1)+0);
                                gl.addView(l1Res, 7*(i+1)+1);
                                gl.addView(l2Res, 7*(i+1)+2);
                                gl.addView(l3Res, 7*(i+1)+3);
                                gl.addView(l4Res, 7*(i+1)+4);
                                gl.addView(l5Res, 7*(i+1)+5);
                                gl.addView(l8Res, 7*(i+1)+6);
                            }
                        }
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }.execute();
    }
}

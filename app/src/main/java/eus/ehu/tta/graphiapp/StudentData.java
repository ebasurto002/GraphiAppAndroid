package eus.ehu.tta.graphiapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

public class StudentData {
    private Context context;
    private SharedPreferences prefs;
    private SharedPreferences defaultSharedPreferences;

    public StudentData(Context context) {
        this.context = context;
        defaultSharedPreferences = context.getSharedPreferences("eus.ehu.tta.graphiapp.default",Context.MODE_PRIVATE);
        String nickname = defaultSharedPreferences.getString("currentNickname",null);
        if (nickname != null) {
            prefs = context.getSharedPreferences("eus.ehu.tta.graphiapp." + nickname, Context.MODE_PRIVATE);
        }
    }

    public String getNickname() {
        return defaultSharedPreferences.getString("currentNickname",null);
    }

    public void setNickname(String nickname) {
        defaultSharedPreferences.edit().putString("currentNickname",nickname).apply();
        prefs = context.getSharedPreferences("eus.ehu.tta.graphiapp." + nickname, Context.MODE_PRIVATE);
    }

    public Uri getUrlFoto() {
        String url =  prefs.getString("urlFoto", null);
        Uri uri = null;
        if (url != null)
        {
            uri = Uri.parse(url);
        }
        return uri;
    }

    public void setUrlFoto(Uri urlFoto) {
        String url = String.valueOf(urlFoto);
        prefs.edit().putString("urlFoto",url).apply();
    }

    public float getResultado(int numero) {
        return prefs.getFloat("puntuacionNivel" + numero,-1);
    }

    public void setResultado(float resultado,int numero) {
        prefs.edit().putFloat("puntuacionNivel" + numero,resultado).apply();
    }
}

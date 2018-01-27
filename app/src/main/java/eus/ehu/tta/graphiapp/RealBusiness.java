package eus.ehu.tta.graphiapp;

import android.content.Context;
import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import eus.ehu.tta.graphiapp.Levels.Nivel1;
import eus.ehu.tta.graphiapp.Levels.Nivel2;
import eus.ehu.tta.graphiapp.Levels.Nivel3;
import eus.ehu.tta.graphiapp.Levels.Nivel4;
import eus.ehu.tta.graphiapp.Levels.Nivel5;
import eus.ehu.tta.graphiapp.Levels.Nivel8;

/**
 * Created by estre on 19/01/2018.
 */

public class RealBusiness implements Business {
    protected RestClient restClient = new RestClient("http://u017633.ehu.eus:28080/GraphiAppServer/rest/GraphiApp");
    private Context context;

    public RealBusiness(Context context)
    {
        this.context = context;
    }

    @Override
    public String register(String name, String surname, String password, int userType) {
        try {
            JSONObject json = new JSONObject();
            json.put("nombre", name);
            json.put("apellidos", surname);
            json.put("password", password);
            String login = null;
            if(userType == Business.TIPO_ALUMNO){
                login  = restClient.postJsonwithString(json, "registerUser");
            }
            else {
                login = restClient.postJsonwithString(json,"registerTeacher");
            }
            return login;

        }
        catch(JSONException je){
            je.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public boolean login(String userName, String password, int userType) {
        try {
            String response = restClient.getString("loginUser?nickname="+userName+"&password="+password);
            if(response.contains("Error")){
                return false;
            }
            else if(response.contains("Alumno")&&userType== TIPO_ALUMNO){
                return true;
            }
            else if(response.contains("Docente")&&userType==TIPO_PROFESOR){
                return true;
            }
            else{
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public String registerClass(String tematica, int fecha, String login){

        try{
            JSONObject json = new JSONObject();
            json.put("tematica",tematica);
            json.put("fecha",fecha);
            json.put("loginDocente",login);
            String response = restClient.postJsonwithString(json, "registerClass");

            return response;
        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch(JSONException je){
            je.printStackTrace();
        }
        return "";
    }

    @Override
    public JSONObject getClasses(String nickname) {
        try{
            JSONObject json = restClient.getJson("getClass?nickname="+nickname);
            return json;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONObject getResults(String nickname, String tematica) {
        try{
            JSONObject json = restClient.getJson("getResults?nickname="+nickname+"&tematica="+tematica);
            return json;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String postLevel1(String correcta, String incorrecta, String nickname) {
        try{
            JSONObject level1json = new JSONObject();
            Random rdn = new Random();
            int order = rdn.nextInt(2) + 1;

            if(order == 1){
                level1json.put("correcta", order);
                level1json.put("palabra1",correcta);
                level1json.put("palabra2",incorrecta);
                level1json.put("clase", TeacherData.getInstance().getIdClase());
            }
            else{
                level1json.put("correcta", order);
                level1json.put("palabra1",incorrecta);
                level1json.put("palabra2",correcta);
                level1json.put("clase", TeacherData.getInstance().getIdClase());
            }

            JSONObject json = new JSONObject();
            json.put("nivel1JSON",level1json);
            json.put("login",nickname);
            String response = restClient.postJsonwithString(json, "postNivel1");

            return response;

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public boolean uploadFile(Uri uri, Context c, String filename) {
        try{
            InputStream is = c.getContentResolver().openInputStream(uri);
            int i = restClient.postFile("uploadFile",is,filename);
            if(i == HttpURLConnection.HTTP_OK){
                return true;
            }
            else{
                return false;
            }
        }
        catch(Exception e){
            e.printStackTrace();

        }
        return false;
    }

    @Override
    public boolean postLevel3(Context context, String correcta, String incorrecta, String remoteURL) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHH");
        String dateString = df.format(calendar.getTime());
        int fecha = Integer.parseInt(dateString);
        Random rdn = new Random();
        int order = rdn.nextInt(2) + 1;

        try{
            JSONObject json = new JSONObject();
            if(order == 1){
                json.put("palabra1",correcta);
                json.put("palabra2",incorrecta);
                json.put("correcta",order);
                json.put("urlImagen",remoteURL);
                json.put("pin",fecha);
            }
            else{
                json.put("palabra1",incorrecta);
                json.put("palabra2",correcta);
                json.put("correcta",order);
                json.put("urlImagen",remoteURL);
                json.put("pin",fecha);
            }

            String jsonArrayString = context.getSharedPreferences("eus.ehu.tta.graphiapp.default",Context.MODE_PRIVATE).getString("nivel3",null);

            if(jsonArrayString != null){
                JSONArray jsonArray = new JSONArray(jsonArrayString);
                jsonArray.put(json);
                context.getSharedPreferences("eus.ehu.tta.graphiapp.default",Context.MODE_PRIVATE).edit().putString("nivel3",jsonArray.toString()).apply();
            }
            else{
                JSONArray jsonArray = new JSONArray();
                jsonArray.put(json);
                context.getSharedPreferences("eus.ehu.tta.graphiapp.default",Context.MODE_PRIVATE).edit().putString("nivel3",jsonArray.toString()).apply();
            }
            return true;

        }
         catch(Exception e){
            e.printStackTrace();
         }

         return false;

    }

    @Override
    public boolean postLevel4(Context context, String headline, int iWordPos) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHH");
        String dateString = df.format(calendar.getTime());
        int fecha = Integer.parseInt(dateString);

        try{
            JSONObject json = new JSONObject();
            json.put("titular",headline);
            json.put("incorrecta",iWordPos);
            json.put("pin",fecha);

            String jsonArrayString = context.getSharedPreferences("eus.ehu.tta.graphiapp.default",Context.MODE_PRIVATE).getString("nivel4",null);

            if(jsonArrayString != null){
                JSONArray jsonArray = new JSONArray(jsonArrayString);
                jsonArray.put(json);
                context.getSharedPreferences("eus.ehu.tta.graphiapp.default",Context.MODE_PRIVATE).edit().putString("nivel4",jsonArray.toString()).apply();
            }
            else{
                JSONArray jsonArray = new JSONArray();
                jsonArray.put(json);
                context.getSharedPreferences("eus.ehu.tta.graphiapp.default",Context.MODE_PRIVATE).edit().putString("nivel4",jsonArray.toString()).apply();
            }
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean postLevel5(Context context, String palabra1, String palabra2, String frase1, String frase2) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHH");
        String dateString = df.format(calendar.getTime());
        int fecha = Integer.parseInt(dateString);
        try{
            JSONObject json = new JSONObject();
            json.put("palabra1",palabra1);
            json.put("palabra2",palabra2);
            json.put("frase1",frase1);
            json.put("frase2",frase2);
            json.put("pin",fecha);

            String jsonArrayString = context.getSharedPreferences("eus.ehu.tta.graphiapp.default",Context.MODE_PRIVATE).getString("nivel5",null);

            if(jsonArrayString != null){
                JSONArray jsonArray = new JSONArray(jsonArrayString);
                jsonArray.put(json);
                context.getSharedPreferences("eus.ehu.tta.graphiapp.default",Context.MODE_PRIVATE).edit().putString("nivel5",jsonArray.toString()).apply();
            }
            else{
                JSONArray jsonArray = new JSONArray();
                jsonArray.put(json);
                context.getSharedPreferences("eus.ehu.tta.graphiapp.default",Context.MODE_PRIVATE).edit().putString("nivel5",jsonArray.toString()).apply();
            }
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean postLevel8(Context context, String palabra, int stressType) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHH");
        String dateString = df.format(calendar.getTime());
        int fecha = Integer.parseInt(dateString);
        try{
            JSONObject json =  new JSONObject();
            json.put("palabra",palabra);
            json.put("acento",stressType);
            json.put("pin",fecha);

            String jsonArrayString  = context.getSharedPreferences("eus.ehu.tta.graphiapp.default",Context.MODE_PRIVATE).getString("nivel8",null);

            if(jsonArrayString != null){
                JSONArray jsonArray = new JSONArray(jsonArrayString);
                jsonArray.put(json);
                context.getSharedPreferences("eus.ehu.tta.graphiapp.default",Context.MODE_PRIVATE).edit().putString("nivel8",jsonArray.toString()).apply();
            }
            else{
                JSONArray jsonArray = new JSONArray();
                jsonArray.put(json);
                context.getSharedPreferences("eus.ehu.tta.graphiapp.default",Context.MODE_PRIVATE).edit().putString("nivel8",jsonArray.toString()).apply();
            }
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String postLevel2(String unstressedWord, int stressPos, String filename, String nickname) {
         try{
             JSONObject level2 = new JSONObject();
             String remoteURL = "http://u017633.ehu.eus:28080/GraphiAppServer/" + "audio/"+filename;
             level2.put("audio",remoteURL);
             level2.put("palabra",unstressedWord);
             level2.put("tildada",stressPos);
             level2.put("clase",TeacherData.getInstance().getIdClase());

             JSONObject json = new JSONObject();
             json.put("nivel2JSON",level2);
             json.put("login",nickname);
             json.put("url",remoteURL);

             String response = restClient.postJsonwithString(json,"postNivel2");
             return response;
         }
         catch(Exception e){
             e.printStackTrace();
         }

        return null;
    }

    @Override
    public Nivel1[] getNivel1(String nickname, Integer pin) {
        Nivel1[] nivel1Array = null;
        try {
            JSONObject jsonObject = getNivelJSON("getNivel1",nickname,pin);
            JSONArray jsonArray = jsonObject.getJSONArray("nivel1");
            nivel1Array = new Nivel1[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject nivel1JSON = jsonArray.getJSONObject(i);
                Nivel1 nivel1 = new Nivel1();
                nivel1.setCorrecta(nivel1JSON.getInt("correcta"));
                nivel1.setPalabra1(nivel1JSON.getString("palabra1"));
                nivel1.setPalabra2(nivel1JSON.getString("palabra2"));
                nivel1Array[i] = nivel1;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return nivel1Array;
    }

    public Nivel2[] getNivel2(String nickname, Integer pin) {
        Nivel2[] nivel2Array = null;
        try {
            JSONObject jsonObject = getNivelJSON("getNivel2",nickname,pin);
            JSONArray jsonArray = jsonObject.getJSONArray("nivel2");
            nivel2Array = new Nivel2[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject nivel2JSON = jsonArray.getJSONObject(i);
                Nivel2 nivel2 = new Nivel2();
                nivel2.setAudio(nivel2JSON.getString("audio"));
                nivel2.setPalabra(nivel2JSON.getString("palabra"));
                nivel2.setTildada(nivel2JSON.getInt("tildada"));
                nivel2Array[i] = nivel2;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return nivel2Array;
    }

    @Override
    public Nivel3[] getNivel3(String nickname, Integer pin) {
        Nivel3[] nivel3Array = null;
        try {
            JSONObject jsonObject = getNivelJSON("nivel3.json",nickname,pin);
            JSONArray jsonArray = jsonObject.getJSONArray("nivel3");
            nivel3Array = new Nivel3[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject nivel3JSON = jsonArray.getJSONObject(i);
                Nivel3 nivel3 = new Nivel3();
                nivel3.setPalabra1(nivel3JSON.getString("palabra1"));
                nivel3.setPalabra2(nivel3JSON.getString("palabra2"));
                nivel3.setCorrecta(nivel3JSON.getInt("correcta"));
                nivel3.setUrlImagen(nivel3JSON.getString("urlImagen"));
                nivel3Array[i] = nivel3;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return nivel3Array;
    }

    @Override
    public Nivel4[] getNivel4(String nickname, Integer pin) {
        Nivel4[] nivel4Array = null;
        try {
            JSONObject jsonObject = getNivelJSON("nivel4.json",nickname,pin);
            JSONArray jsonArray = jsonObject.getJSONArray("nivel4");
            nivel4Array = new Nivel4[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject nivel4JSON = jsonArray.getJSONObject(i);
                Nivel4 nivel4 = new Nivel4();
                nivel4.setTitular(nivel4JSON.getString("titular"));
                nivel4.setIncorrecta(nivel4JSON.getInt("incorrecta"));
                nivel4Array[i] = nivel4;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return nivel4Array;
    }

    @Override
    public Nivel5[] getNivel5(String nickname, Integer pin) {
        Nivel5[] nivel5Array = null;
        try {
            JSONObject jsonObject = getNivelJSON("nivel5.json",nickname,pin);
            JSONArray jsonArray = jsonObject.getJSONArray("nivel5");
            nivel5Array = new Nivel5[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject nivel5JSON = jsonArray.getJSONObject(i);
                Nivel5 nivel5 = new Nivel5();
                nivel5.setFrase1(nivel5JSON.getString("frase1"));
                nivel5.setFrase2(nivel5JSON.getString("frase2"));
                nivel5.setPalabra1(nivel5JSON.getString("palabra1"));
                nivel5.setPalabra2(nivel5JSON.getString("palabra2"));
                nivel5Array[i] = nivel5;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return nivel5Array;
    }

    @Override
    public Nivel8[] getNivel8(String nickname, Integer pin) {
        Nivel8[] nivel8Array = null;
        try {
            JSONObject jsonObject = getNivelJSON("nivel8.json",nickname,pin);
            JSONArray jsonArray = jsonObject.getJSONArray("nivel8");
            nivel8Array = new Nivel8[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject nivel8JSON = jsonArray.getJSONObject(i);
                Nivel8 nivel8 = new Nivel8();
                nivel8.setPalabra(nivel8JSON.getString("palabra"));
                nivel8.setAcento(nivel8JSON.getInt("acento"));
                nivel8Array[i] = nivel8;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return nivel8Array;
    }

    private String getPath(String path, String nickname, Integer pin) {
        String completePath = String.format(path+"?nickname=%s",nickname);
        if (pin!=null)
        {
            completePath = completePath.concat(String.format("&&pin=%d",pin.intValue()));
        }
        return completePath;
    }

    public JSONObject getNivelJSON(String path, String nickname, Integer pin) throws IOException, JSONException {
        if (!path.endsWith(".json")) {
            String completePath = getPath(path, nickname, pin);
            return restClient.getJson(completePath);
        }

        else
        {
            return readJSONFromStorage(path,pin);
        }
    }

    private JSONObject readJSONFromStorage(String path, Integer pin) throws IOException, JSONException {
        InputStream is = context.getAssets().open("json/"+path);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        byte [] buffer = new byte [1024];
        StringBuffer json = new StringBuffer();
        String line;
        while ((line=br.readLine())!=null)
        {
            json.append(line);
        }
        JSONObject jsonObject = new JSONObject(json.toString());
        String[] fileNameSplit = path.split("\\.");
        String levelName = fileNameSplit[0];
        JSONArray array = jsonObject.getJSONArray(levelName);
        JSONArray newArray = new JSONArray();
        if (pin==null) {
            if (array.length()<=10)
            {
                newArray = array;
            }
            else
            {
                for (int i = 0; i < 10; i++) {
                    int random = (int) (Math.random() * array.length());
                    newArray.put(array.get(random));
                    array.remove(random);
                }
            }
        }
        else
        {
            for (int i = 0; i < array.length(); i++) {
                if (array.getJSONObject(i).getInt("pin") == pin.intValue())
                {
                    newArray.put(array.getJSONObject(i));
                }
            }
        }
        JSONObject newJSONObject = new JSONObject();
        newJSONObject.put(levelName,newArray);
        return newJSONObject;
    }

    @Override
    public Boolean postResults(double[] puntuaciones, int pin, String nickname) {
        boolean result = false;
        JSONObject json = new JSONObject();
        try {
            json.put("puntosNivel1", puntuaciones[0]);
            json.put("puntosNivel2", puntuaciones[1]);
            json.put("puntosNivel3", puntuaciones[2]);
            json.put("puntosNivel4", puntuaciones[3]);
            json.put("puntosNivel5", puntuaciones[4]);
            json.put("puntosNivel8", puntuaciones[5]);
            json.put("fecha",pin);
            json.put("alumno",nickname);
            int code = restClient.postJson(json, "postResult");
            if (code == HttpURLConnection.HTTP_OK) {
                result = true;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
}

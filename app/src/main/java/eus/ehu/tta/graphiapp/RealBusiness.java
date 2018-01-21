package eus.ehu.tta.graphiapp;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


import eus.ehu.tta.graphiapp.Levels.Nivel1;
import eus.ehu.tta.graphiapp.Levels.Nivel2;
import eus.ehu.tta.graphiapp.Levels.Nivel3;
import eus.ehu.tta.graphiapp.Levels.Nivel4;
import eus.ehu.tta.graphiapp.Levels.Nivel5;

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
        return new Nivel4[] {
                new Nivel4("Descubre que es canival tras morderse la lengua y querer repetir", 4),
                new Nivel4("Denuncian situaziones laborables que perjudican a adolescentes", 2),
                new Nivel4("La ambruna crece en todo el mundo", 2),
                new Nivel4("La venta de las bibiendas ha aumentado un 50%", 5),
                new Nivel4("El Rei hace público un sueldo de 292.000€ brutos al año", 2),
                new Nivel4("Un abión español se estrella en Turquia por tercera vez lo que va en de año", 2),
                new Nivel4("Condenan a ocho de los tres akusados por secuestrar a un menor", 7),
                new Nivel4("Rova un coche para aparcarlo mejor", 1),
                new Nivel4("Los presos de las cárceles españolas critican la hentrada en masa de “gente normal”", 9),
                new Nivel4("Dios admite en el Sielo al primer pecador", 5)
        };
    }

    @Override
    public Nivel5[] getNivel5(String nickname, Integer pin) {
        return new Nivel5[] {
                new Nivel5("Ellos van de paseo en su *** nuevo", "Se espera que el grupo *** hoy a su delegado", "bote", "vote"),
                new Nivel5("Roberto *** y aceptó el consejo", "El niño se *** y esta llorando", "calló", "cayó"),
                new Nivel5("La bandera ondea en su ***", "carmen lleva a María *** el colegio", "asta", "hasta"),
                new Nivel5("En la pared colocaron un *** nuevo", "José *** que perdonar a su hermano", "tubo", "tuvo"),
                new Nivel5("Una *** gigante se acercó a la playa", "EL niño saludo con un ``***´´ a su amiga.", "ola", "hola"),
                new Nivel5("En las comidas el niño *** agua", "el *** llora cada vez que quiere comer", "bebe", "bebé"),
                new Nivel5("La partida de cartas se gana con el *** de oros", "*** de terminar los deberes para ir al parque", "as", "Has"),
                new Nivel5("mi hermana heredó todos los *** de mi tio", "¿Mañana *** a la comida familiar?", "Bienes", "Vienes"),
                new Nivel5("Estoy *** con mi mejor amiga", "Yo *** la plastilina antes de realizar figuras", "hablando", "ablando"),
                new Nivel5("Este curriculum no es *** para este puesto de trabajo", "Se ha escuchado un *** en la granja", "valido", "balido")
        };
    }

    private String getPath(String path, String nickname, Integer pin) {
        String completePath = String.format(path+"?nickname=%s",nickname);
        if (pin!=null)
        {
            completePath.concat(String.format("&&pin=%d",pin.intValue()));
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
            for (int i = 0; i < 10; i++) {
                int random = (int) (Math.random() * array.length());
                newArray.put(array.get(random));
                array.remove(random);
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
}

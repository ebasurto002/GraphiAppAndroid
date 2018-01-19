package eus.ehu.tta.graphiapp;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


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
                login = restClient.postJsonwithString(json,"regiterTeacher");
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
        if (userName.equals("eb00") && password.equals("1234") && userType == TIPO_ALUMNO)
        {
            return true;
        }
        else if (userName.equals("je00") && password.equals("1234") && userType == TIPO_PROFESOR)
        {
            return true;
        }

        else
        {
            return false;
        }
    }

    @Override
    public Nivel1[] getNivel1(Integer pin) {
        return new Nivel1 [] {
                new Nivel1("correcta","incorrecta",1),
                new Nivel1("incorrecta","correcta",2),
                new Nivel1("correcta","incorrecta",1),
                new Nivel1("incorrecta","correcta",2),
                new Nivel1("correcta","incorrecta",1),
                new Nivel1("incorrecta","correcta",2),
                new Nivel1("correcta","incorrecta",1),
                new Nivel1("incorrecta","correcta",2),
                new Nivel1("correcta","incorrecta",1),
                new Nivel1("incorrecta","correcta",2)
        };
    }

    public Nivel2[] getNivel2(Integer pin) {
        return new Nivel2[] {
                new Nivel2("Telefono",4,"http://u017633.ehu.eus:28080/GraphiAppServer/audio/telefono.mp3"),
                new Nivel2("camaleon",7,"http://u017633.ehu.eus:28080/GraphiAppServer/audio/camaleon.mp3"),
                new Nivel2("cancer",2,"http://u017633.ehu.eus:28080/GraphiAppServer/audio/cancer.mp3"),
                new Nivel2("pais",3,"http://u017633.ehu.eus:28080/GraphiAppServer/audio/pais.mp3"),
                new Nivel2("matematicas",6,"http://u017633.ehu.eus:28080/GraphiAppServer/audio/matematicas.mp3"),
                new Nivel2("adios",4,"http://u017633.ehu.eus:28080/GraphiAppServer/audio/adios.mp3"),
                new Nivel2("trebol",3,"http://u017633.ehu.eus:28080/GraphiAppServer/audio/trebol.mp3"),
                new Nivel2("bisturi",7,"http://u017633.ehu.eus:28080/GraphiAppServer/audio/bisturi.mp3"),
                new Nivel2("nitido",2,"http://u017633.ehu.eus:28080/GraphiAppServer/audio/nitido.mp3"),
                new Nivel2("capitan",6,"http://u017633.ehu.eus:28080/GraphiAppServer/audio/capitan.mp3")
        };
    }

    @Override
    public Nivel3[] getNivel3(Integer pin) {
        return new Nivel3[] {
                new Nivel3( "Abrasar", "Abrazar", 2,"http://u017633.ehu.eus:28080/GraphiAppServer/img/nivel3/Abrazar.jpg"),
                new Nivel3( "Hasta", "Asta", 2, "http://u017633.ehu.eus:28080/GraphiAppServer/img/nivel3/Asta.jpg"),
                new Nivel3( "Barón", "Varón", 1, "http://u017633.ehu.eus:28080/GraphiAppServer/img/nivel3/Baron.jpg"),
                new Nivel3( "Cabo", "Cavó", 1, "http://u017633.ehu.eus:28080/GraphiAppServer/img/nivel3/Cabo.jpg"),
                new Nivel3( "Sumo", "Zumo", 1, "http://u017633.ehu.eus:28080/GraphiAppServer/img/nivel3/Sumo.jpg"),
                new Nivel3( "Sabia", "Sabía", 1, "http://u017633.ehu.eus:28080/GraphiAppServer/img/nivel3/Sabia.jpg"),
                new Nivel3( "Arrollo", "Arroyo", 2, "http://u017633.ehu.eus:28080/GraphiAppServer/img/nivel3/Arroyo.jpg"),
                new Nivel3( "Vaca", "Baca", 2, "http://u017633.ehu.eus:28080/GraphiAppServer/img/nivel3/Baca.jpg"),
                new Nivel3( "Hola", "Ola", 2, "http://u017633.ehu.eus:28080/GraphiAppServer/img/nivel3/Ola.jpg"),
                new Nivel3( "Bobina", "Bovina", 1, "http://u017633.ehu.eus:28080/GraphiAppServer/img/nivel3/Bobina.jpg")
        };
    }

    @Override
    public Nivel4[] getNivel4(Integer pin) {
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
    public Nivel5[] getNivel5(Integer pin) {
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
}

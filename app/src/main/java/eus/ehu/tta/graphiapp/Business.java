package eus.ehu.tta.graphiapp;

import org.json.JSONObject;

import eus.ehu.tta.graphiapp.Levels.Nivel1;
import eus.ehu.tta.graphiapp.Levels.Nivel2;
import eus.ehu.tta.graphiapp.Levels.Nivel3;
import eus.ehu.tta.graphiapp.Levels.Nivel4;
import eus.ehu.tta.graphiapp.Levels.Nivel5;

public interface Business {

    public static final int TIPO_ALUMNO = 0;
    public static final int TIPO_PROFESOR = 1;

    public abstract String register (String name, String surname, String password, int userType); // Devuelve el nickname del usuario, para introducir en el login, o string vacio para indicar error
    public abstract boolean login (String userName, String password, int userType);

    public abstract Nivel1[] getNivel1(Integer pin); //pin = null en modo individual
    public abstract Nivel2[] getNivel2(Integer pin);
    public abstract Nivel3[] getNivel3(Integer pin);
    public abstract Nivel4[] getNivel4(Integer pin);
    public abstract Nivel5[] getNivel5(Integer pin);
    public abstract String registerClass(String tematica, int fecha, String login);
    public abstract JSONObject getClasses(String nickname);
    public abstract JSONObject getResults(String nickname, String tematica);
    public abstract String postLevel1(String correcta, String incorrecta, String nickname);
}

package eus.ehu.tta.graphiapp;

import eus.ehu.tta.graphiapp.Levels.Nivel1;
import eus.ehu.tta.graphiapp.Levels.Nivel2;
import eus.ehu.tta.graphiapp.Levels.Nivel3;
import eus.ehu.tta.graphiapp.Levels.Nivel4;
import eus.ehu.tta.graphiapp.Levels.Nivel5;

public interface Business {

    public static final int TIPO_ALUMNO = 0;
    public static final int TIPO_PROFESOR = 1;

    public abstract boolean register (String name, String surname, String password, int userType);
    public abstract boolean login (String userName, String password, int userType);

    public abstract Nivel1[] getNivel1(Integer pin); //pin = null en modo individual
    public abstract Nivel2[] getNivel2(Integer pin);
    public abstract Nivel3[] getNivel3(Integer pin);
    public abstract Nivel4[] getNivel4(Integer pin);
    public abstract Nivel5[] getNivel5(Integer pin);
}

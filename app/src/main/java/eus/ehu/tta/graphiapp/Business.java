package eus.ehu.tta.graphiapp;

public interface Business {

    public static final int TIPO_ALUMNO = 0;
    public static final int TIPO_PROFESOR = 1;

    public abstract boolean register (String name, String surname, String password, int userType);
    public abstract boolean login (String userName, String password, int userType);
}

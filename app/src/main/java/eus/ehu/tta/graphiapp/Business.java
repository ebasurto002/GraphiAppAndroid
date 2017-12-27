package eus.ehu.tta.graphiapp;

public interface Business {

    public static final int TIPO_ALUMNO = 0;
    public static final int TIPO_PROFESOR = 1;

    public abstract String register (String name, String surname, String password, int userType); // Devuelve el nickname del usuario, para introducir en el login, o string vacio para indicar error
    public abstract boolean login (String userName, String password, int userType);
}

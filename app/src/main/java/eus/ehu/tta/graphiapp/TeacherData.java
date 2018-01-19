package eus.ehu.tta.graphiapp;

/**
 * Created by estre on 19/01/2018.
 */

public class TeacherData {


    private TeacherData instance = null;
    private String login;
    private int idClase;

    private TeacherData(){

    }

    public TeacherData getInstance() {
        if (instance == null){
            instance = new TeacherData();
        }
        return instance;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getIdClase() {
        return idClase;
    }

    public void setIdClase(int idClase) {
        this.idClase = idClase;
    }
}

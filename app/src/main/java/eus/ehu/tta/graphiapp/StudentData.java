package eus.ehu.tta.graphiapp;

public class StudentData {
    private static StudentData instance = null;
    private String nickname;
    double[] resultados = new double [8];

    private StudentData() {
    }

    public String getNickname() {
        return "eb000";
        //TODO Cambiar cuando esté el registro
        //return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public double[] getResultados() {
        return resultados;
    }

    public void setResultados(double[] resultados) {
        this.resultados = resultados;
    }

    public static synchronized StudentData getInstance() {
        if (instance == null)
        {
            instance = new StudentData();
        }
        return instance;
    }
}

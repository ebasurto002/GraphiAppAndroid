package eus.ehu.tta.graphiapp.Levels;

public class Nivel4 {
    private String titular;
    private int incorrecta;

    public Nivel4(String titular, int incorrecta) {
        this.titular = titular;
        this.incorrecta = incorrecta;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public int getIncorrecta() {
        return incorrecta;
    }

    public void setIncorrecta(int incorrecta) {
        this.incorrecta = incorrecta;
    }
}

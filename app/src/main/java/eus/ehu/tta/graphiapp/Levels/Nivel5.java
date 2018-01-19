package eus.ehu.tta.graphiapp.Levels;

public class Nivel5 {
    private String frase1;
    private String frase2;
    private  String palabra1;
    private String palabra2;

    public Nivel5()
    {

    }

    public Nivel5(String frase1, String frase2, String palabra1, String palabra2) {
        this.frase1 = frase1;
        this.frase2 = frase2;
        this.palabra1 = palabra1;
        this.palabra2 = palabra2;
    }

    public String getFrase1() {
        return frase1;
    }

    public void setFrase1(String frase1) {
        this.frase1 = frase1;
    }

    public String getFrase2() {
        return frase2;
    }

    public void setFrase2(String frase2) {
        this.frase2 = frase2;
    }

    public String getPalabra1() {
        return palabra1;
    }

    public void setPalabra1(String palabra1) {
        this.palabra1 = palabra1;
    }

    public String getPalabra2() {
        return palabra2;
    }

    public void setPalabra2(String palabra2) {
        this.palabra2 = palabra2;
    }
}

package eus.ehu.tta.graphiapp.Levels;

public class Nivel8 {
    private String palabra;
    private int acento;

    public Nivel8() {
    }

    public Nivel8(String palabra, int acento) {
        this.palabra = palabra;
        this.acento = acento;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public int getAcento() {
        return acento;
    }

    public void setAcento(int acento) {
        this.acento = acento;
    }
}

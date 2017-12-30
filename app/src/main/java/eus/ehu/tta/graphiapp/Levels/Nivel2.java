package eus.ehu.tta.graphiapp.Levels;

public class Nivel2 {
    String palabra;
    int tildada;
    String audio;

    public Nivel2(String palabra, int tildada, String audio) {
        this.palabra = palabra;
        this.tildada = tildada;
        this.audio = audio;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public int getTildada() {
        return tildada;
    }

    public void setTildada(int tildada) {
        this.tildada = tildada;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }
}

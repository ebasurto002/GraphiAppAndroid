package eus.ehu.tta.graphiapp.Levels;

public class Nivel3 {
    private String palabra1;
    private String palabra2;
    private int correcta;
    private String urlImagen;

    public Nivel3(String palabra1, String palabra2, int correcta, String urlImagen) {
        this.palabra1 = palabra1;
        this.palabra2 = palabra2;
        this.correcta = correcta;
        this.urlImagen = urlImagen;
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

    public int getCorrecta() {
        return correcta;
    }

    public void setCorrecta(int correcta) {
        this.correcta = correcta;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }
}

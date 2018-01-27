package eus.ehu.tta.graphiapp.Levels;

import android.os.Parcel;
import android.os.Parcelable;

public class Nivel2 implements Parcelable {
    private String palabra;
    private int tildada;
    private String audio;

    public Nivel2()
    {

    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.palabra);
        dest.writeInt(this.tildada);
        dest.writeString(this.audio);
    }

    protected Nivel2(Parcel in) {
        this.palabra = in.readString();
        this.tildada = in.readInt();
        this.audio = in.readString();
    }

    public static final Parcelable.Creator<Nivel2> CREATOR = new Parcelable.Creator<Nivel2>() {
        @Override
        public Nivel2 createFromParcel(Parcel source) {
            return new Nivel2(source);
        }

        @Override
        public Nivel2[] newArray(int size) {
            return new Nivel2[size];
        }
    };
}

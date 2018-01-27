package eus.ehu.tta.graphiapp.Levels;

import android.os.Parcel;
import android.os.Parcelable;

public class Nivel8 implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.palabra);
        dest.writeInt(this.acento);
    }

    protected Nivel8(Parcel in) {
        this.palabra = in.readString();
        this.acento = in.readInt();
    }

    public static final Parcelable.Creator<Nivel8> CREATOR = new Parcelable.Creator<Nivel8>() {
        @Override
        public Nivel8 createFromParcel(Parcel source) {
            return new Nivel8(source);
        }

        @Override
        public Nivel8[] newArray(int size) {
            return new Nivel8[size];
        }
    };
}

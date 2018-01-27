package eus.ehu.tta.graphiapp.Levels;

import android.os.Parcel;
import android.os.Parcelable;

public class Nivel1 implements Parcelable {
    private String palabra1;
    private String palabra2;
    private int correcta;

    public Nivel1()
    {

    }

    public Nivel1(String palabra1, String palabra2, int correcta) {
        this.palabra1 = palabra1;
        this.palabra2 = palabra2;
        this.correcta = correcta;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.palabra1);
        dest.writeString(this.palabra2);
        dest.writeInt(this.correcta);
    }

    protected Nivel1(Parcel in) {
        this.palabra1 = in.readString();
        this.palabra2 = in.readString();
        this.correcta = in.readInt();
    }

    public static final Parcelable.Creator<Nivel1> CREATOR = new Parcelable.Creator<Nivel1>() {
        @Override
        public Nivel1 createFromParcel(Parcel source) {
            return new Nivel1(source);
        }

        @Override
        public Nivel1[] newArray(int size) {
            return new Nivel1[size];
        }
    };
}

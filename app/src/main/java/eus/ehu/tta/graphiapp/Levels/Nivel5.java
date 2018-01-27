package eus.ehu.tta.graphiapp.Levels;

import android.os.Parcel;
import android.os.Parcelable;

public class Nivel5 implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.frase1);
        dest.writeString(this.frase2);
        dest.writeString(this.palabra1);
        dest.writeString(this.palabra2);
    }

    protected Nivel5(Parcel in) {
        this.frase1 = in.readString();
        this.frase2 = in.readString();
        this.palabra1 = in.readString();
        this.palabra2 = in.readString();
    }

    public static final Parcelable.Creator<Nivel5> CREATOR = new Parcelable.Creator<Nivel5>() {
        @Override
        public Nivel5 createFromParcel(Parcel source) {
            return new Nivel5(source);
        }

        @Override
        public Nivel5[] newArray(int size) {
            return new Nivel5[size];
        }
    };
}

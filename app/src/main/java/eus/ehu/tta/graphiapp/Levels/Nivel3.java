package eus.ehu.tta.graphiapp.Levels;

import android.os.Parcel;
import android.os.Parcelable;

public class Nivel3 implements Parcelable {
    private String palabra1;
    private String palabra2;
    private int correcta;
    private String urlImagen;

    public Nivel3()
    {

    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.palabra1);
        dest.writeString(this.palabra2);
        dest.writeInt(this.correcta);
        dest.writeString(this.urlImagen);
    }

    protected Nivel3(Parcel in) {
        this.palabra1 = in.readString();
        this.palabra2 = in.readString();
        this.correcta = in.readInt();
        this.urlImagen = in.readString();
    }

    public static final Parcelable.Creator<Nivel3> CREATOR = new Parcelable.Creator<Nivel3>() {
        @Override
        public Nivel3 createFromParcel(Parcel source) {
            return new Nivel3(source);
        }

        @Override
        public Nivel3[] newArray(int size) {
            return new Nivel3[size];
        }
    };
}

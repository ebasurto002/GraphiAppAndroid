package eus.ehu.tta.graphiapp.Levels;

import android.os.Parcel;
import android.os.Parcelable;

public class Nivel4 implements Parcelable {
    private String titular;
    private int incorrecta;

    public Nivel4()
    {

    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.titular);
        dest.writeInt(this.incorrecta);
    }

    protected Nivel4(Parcel in) {
        this.titular = in.readString();
        this.incorrecta = in.readInt();
    }

    public static final Parcelable.Creator<Nivel4> CREATOR = new Parcelable.Creator<Nivel4>() {
        @Override
        public Nivel4 createFromParcel(Parcel source) {
            return new Nivel4(source);
        }

        @Override
        public Nivel4[] newArray(int size) {
            return new Nivel4[size];
        }
    };
}

package ar.edu.utn.frsf.isi.dam.marketplace.model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.util.function.Consumer;

public class Categoria implements Parcelable {
    private String id;
    private String nombre;
    private int color;

    public Categoria() {}

    public Categoria(String id, String nombre, int color) {
        this.id = id;
        this.nombre = nombre;
        this.color = color;
    }

    public Categoria(Consumer<Categoria> c) {
        c.accept(this);
    }

    protected Categoria(Parcel in) {
        id = in.readString();
        nombre = in.readString();
        color = in.readInt();
    }

    public static final Creator<Categoria> CREATOR = new Creator<Categoria>() {
        @Override
        public Categoria createFromParcel(Parcel in) {
            return new Categoria(in);
        }

        @Override
        public Categoria[] newArray(int size) {
            return new Categoria[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String toString() {
        return nombre;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(nombre);
        parcel.writeInt(color);
    }
}
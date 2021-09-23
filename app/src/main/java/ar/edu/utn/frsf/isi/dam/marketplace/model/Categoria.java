package ar.edu.utn.frsf.isi.dam.marketplace.model;

import java.util.function.Consumer;

public class Categoria {
    private Integer id;
    private String nombre;

    public Categoria() {}

    public Categoria(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Categoria(Consumer<Categoria> c) {
        c.accept(this);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
}
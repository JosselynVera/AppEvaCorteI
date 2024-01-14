package com.example.appevacortei.Modelos;

import androidx.annotation.NonNull;

public class CB_seleccionar {
    int id;
    String descripcion;

    @NonNull
    @Override
    public String toString() {
        return descripcion;
    }

    public CB_seleccionar(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

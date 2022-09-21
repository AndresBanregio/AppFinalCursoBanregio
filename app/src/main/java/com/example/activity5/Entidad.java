package com.example.activity5;

import java.io.Serializable;

public class Entidad implements Serializable {
    private String nombre;
    public Entidad(String nombre){
        this.nombre=nombre;
    }
    public String getName(){return nombre;}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableview;

import java.util.Locale;

/**
 *
 * @author PC
 */
public class Persona {

    private int id;
    private String nombre;
    private int edad;
    private String ciudad = "";

    public Persona(int id, String nombre, int edad, String ciudad) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.ciudad = ciudad;
    }

    public Persona() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {

        this.edad = edad;

    }

    public String getciudad() {
        return ciudad;
    }

    public void setciudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public String toString() {

        return " Nombre: " + nombre + "\n"+
                " Edad : " + edad + "\n"+
                " Ciudad : " + ciudad + "\n";
    }

}

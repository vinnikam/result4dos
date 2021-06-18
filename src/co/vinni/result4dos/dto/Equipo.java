/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.vinni.result4dos.dto;

/**
 *
 * @author Vinni
 */
public class Equipo {
    private Long id;
    private String  nombre;
    private String  entrenador;

    public Equipo() {
    }
     
    public Equipo(Long id, String nombre, String entrenador) {
        this.id = id;
        this.nombre = nombre;
        this.entrenador = entrenador;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(String entrenador) {
        this.entrenador = entrenador;
    }
    
    
    
}

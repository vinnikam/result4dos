/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.vinni.result4dos.dto;

import co.vinni.result4dos.dao.OperacionesEquipo;
import co.vinni.result4dos.util.Utilidades;
import java.util.Date;

/**
 *
 * @author Vinni
 */
public class Partido {
    private Long id;
    private Date fecha;
    private Equipo equipoLocal;
    private Equipo equipoVisitante;
    private int golesLocal;
    private int golesVisitante;

    public Partido() {
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Equipo getEquipoLocal() {
        return equipoLocal;
    }

    public void setEquipoLocal(Equipo equipoLocal) {
        this.equipoLocal = equipoLocal;
    }

    public Equipo getEquipoVisitante() {
        return equipoVisitante;
    }

    public void setEquipoVisitante(Equipo equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
    }

    public int getGolesLocal() {
        return golesLocal;
    }

    public void setGolesLocal(int golesLocal) {
        this.golesLocal = golesLocal;
    }

    public int getGolesVisitante() {
        return golesVisitante;
    }

    public void setGolesVisitante(int golesVisitante) {
        this.golesVisitante = golesVisitante;
    }

    @Override
    public String toString() {
        return Utilidades.formatoFecha("dd-MM-yyyy", fecha)+" - "+
                this.equipoLocal.getNombre()+" ("+this.golesLocal+") VS "+
                "("+this.golesVisitante+") "+this.equipoVisitante.getNombre();
    }
    
    
}

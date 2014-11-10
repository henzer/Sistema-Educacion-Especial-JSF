/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelos;

/**
 *
 * @author Cesar Luis
 */

public class Ejercicio {
    int idEjercicio;
    int idLeccion;
    String nombreEjercicio;
    String estado;

    public Ejercicio(int idEjercicio, int idLeccion, String nombreEjercicio, String estado){
        this.idEjercicio = idEjercicio;
        this.idLeccion = idLeccion;
        this.nombreEjercicio = nombreEjercicio;
        this.estado = estado;
    }
    
    
    public int getIdEjercicio() {
        return idEjercicio;
    }

    public void setIdEjercicio(int idEjercicio) {
        this.idEjercicio = idEjercicio;
    }

    public int getIdLeccion() {
        return idLeccion;
    }

    public void setIdLeccion(int idLeccion) {
        this.idLeccion = idLeccion;
    }

    public String getNombreEjercicio() {
        return nombreEjercicio;
    }

    public void setNombreEjercicio(String nombreEjercicio) {
        this.nombreEjercicio = nombreEjercicio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
    
}

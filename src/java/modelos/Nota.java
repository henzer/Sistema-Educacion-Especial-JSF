package modelos;
public class Nota{
    private double suma;
    private int cantidad;
    private int idLeccion;

import java.io.Serializable;

public class Nota implements Serializable{

    public Nota(double suma, int cantidad, int idLeccion) {
        this.suma = suma;
        this.cantidad = cantidad;
        this.idLeccion = idLeccion;
    }

    public double getSuma() {
        return suma;
    }

    public void setSuma(double suma) {
        this.suma = suma;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdLeccion() {
        return idLeccion;
    }

    public void setIdLeccion(int idLeccion) {
        this.idLeccion = idLeccion;
    }
}

package modelos;

import conexion.Conexion;
import java.io.Serializable;
import java.util.ArrayList;

public class Unidad implements Serializable{
    private int idUnidad;
    private String nombreUnidad;
    private int limite;

    private ArrayList<Leccion> lecciones;
    
    public Unidad() {
        lecciones = new ArrayList();
    }

    public Unidad(int idUnidad, String nombreUnidad, int limite) {
        this.idUnidad = idUnidad;
        this.nombreUnidad = nombreUnidad;
        this.limite = limite;
        this.lecciones = new ArrayList();
    }

    public int getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(int idUnidad) {
        this.idUnidad = idUnidad;
    }

    public String getNombreUnidad() {
        return nombreUnidad;
    }

    public void setNombreUnidad(String nombreUnidad) {
        this.nombreUnidad = nombreUnidad;
    }

    public int getLimite() {
        return limite;
    }

    public void setLimite(int limite) {
        this.limite = limite;
    }

    public ArrayList<Leccion> getLecciones() {
        return lecciones;
    }

    public void addLeccion(Leccion nueva) {
        this.lecciones.add(nueva);
    }

    public void setLecciones(ArrayList<Leccion> lecciones) {
        this.lecciones = lecciones;
    }

    @Override
    public String toString() {
        return "Unidad{" + "idUnidad=" + idUnidad + ", nombreUnidad=" + nombreUnidad + '}';
    }
    public int agregarConRetorno(){
        System.out.println("INSERT INTO unidad(nombreUnidad, limite) VALUES ('"+nombreUnidad+"', "+limite+")");
	return Conexion.getInstancia().ejecutarSentenciaConRetorno("INSERT INTO unidad(nombreUnidad, limite) VALUES ('"+nombreUnidad+"', "+limite+")");
    }
    public boolean eliminar(){
	return Conexion.getInstancia().ejecutarSentencia("DELETE FROM unidad WHERE idUnidad =" + idUnidad);
    }
    public boolean modificar(){
	return Conexion.getInstancia().ejecutarSentencia("UPDATE unidad SET nombreUnidad='"+nombreUnidad+"',limite="+limite+" WHERE idUnidad =" + idUnidad);
    }
}

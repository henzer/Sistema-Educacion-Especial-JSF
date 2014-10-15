package modelos;

public class Leccion {
    private int idLeccion;
    private int idUnidad;
    private String nombreUnidad; 
    private String nombreLeccion;
    private String archivo;
    public Leccion() {
    }

    public Leccion(int idLeccion, int idUnidad, String nombreUnidad, String nombreLeccion, String archivo) {
        this.idLeccion = idLeccion;
        this.idUnidad = idUnidad;
        this.nombreUnidad = nombreUnidad;
        this.nombreLeccion = nombreLeccion;
        this.archivo = archivo;
    }

    public int getIdLeccion() {
        return idLeccion;
    }

    public void setIdLeccion(int idLeccion) {
        this.idLeccion = idLeccion;
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

    public String getNombreLeccion() {
        return nombreLeccion;
    }

    public void setNombreLeccion(String nombreLeccion) {
        this.nombreLeccion = nombreLeccion;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    @Override
    public String toString() {
        return "Leccion{" + "idLeccion=" + idLeccion + ", idUnidad=" + idUnidad + ", nombreUnidad=" + nombreUnidad + ", nombreLeccion=" + nombreLeccion + ", archivo=" + archivo + '}';
    }
    
	

}

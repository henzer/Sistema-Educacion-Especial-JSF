package modelos;

import beans.Contenido;
import conexion.Conexion;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class Leccion implements Serializable{
    private boolean modified;
    private int idLeccion;
    private int idUnidad;
    private String nombreUnidad; 
    private String nombreLeccion;
    private String archivo;
    private ArrayList<Ejercicio> ejercicios;
    private ArrayList<Contenido> contenido;
    public Leccion() {
        modified=false;
    }

    public Leccion(int idLeccion, int idUnidad, String nombreUnidad, String nombreLeccion, String archivo) {
        this.idLeccion = idLeccion;
        this.idUnidad = idUnidad;
        this.nombreUnidad = nombreUnidad;
        this.nombreLeccion = nombreLeccion;
        this.archivo = archivo;
        ejercicios = new ArrayList();
        contenido=new ArrayList<Contenido>();
        modified=false;
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

    public ArrayList<Ejercicio> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(ArrayList<Ejercicio> ejercicios) {
        this.ejercicios = ejercicios;
    }    

    public ArrayList<Contenido> getContenido() {
        return contenido;
    }

    public void setContenido(ArrayList<Contenido> contenido) {
        this.contenido = contenido;
    }
    public void generarArchivo(){
        String salida="nombres";
        System.out.println("tamanho "+contenido.size());
        for (int x=0;x<contenido.size();x++){
            salida+="\n"+contenido.get(x).getNombre();
        }
        salida+="\nimagenes";
        for (int x=0;x<contenido.size();x++){
            salida+="\n"+contenido.get(x).getDirImagen();
        }
        salida+="\nsonidos";
        for (int x=0;x<contenido.size();x++){
            salida+="\n"+contenido.get(x).getDirSonido();
        }
        //crear archivo de texto
        try {
            ExternalContext extContext=FacesContext.getCurrentInstance().getExternalContext();
            File file = new File(extContext.getRealPath("//resources//txtLecciones//"+nombreLeccion+".txt"));
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(salida);
            bw.close();
            System.out.println("Done");
        } 
        catch (IOException e) {
            e.printStackTrace();
	}
    }
    @Override
    public String toString() {
        return "Leccion{" + "idLeccion=" + idLeccion + ", idUnidad=" + idUnidad + ", nombreUnidad=" + nombreUnidad + ", nombreLeccion=" + nombreLeccion + ", archivo=" + archivo + '}';
    }
    
    public void eliminar(Contenido elemento){
        contenido.remove(elemento);
        generarArchivo();
    }

    public boolean isModified() {
        return modified;
    }

    public void setModified(boolean modified) {
        this.modified = modified;
    }
    public int agregarConRetorno(){
        return Conexion.getInstancia().ejecutarSentenciaConRetorno("INSERT INTO leccion(idUnidad, nombreLeccion, archivo) VALUES ('"+idUnidad+"','"+nombreLeccion+"','"+archivo+"')");
    }
    public boolean eliminarL(){
        File aBorrar= new File("//resources//txtLecciones//"+nombreLeccion+".txt");
        aBorrar.delete();
	return Conexion.getInstancia().ejecutarSentencia("DELETE FROM leccion WHERE idLeccion =" + idLeccion);
    }
	
    public void addContenido(Contenido content){
        contenido.add(content);
        generarArchivo();
    }
}

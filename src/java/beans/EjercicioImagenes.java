package beans;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.event.DragDropEvent;

/**
 *
 * @author Cesar Luis
 */

@ManagedBean(name = "ejercicioImagenes")
@ViewScoped
public class EjercicioImagenes implements Serializable {
    private String dirLeccion="//resources//txtLecciones//";
    private List<String> nombres;
    private List<String> imagenes;
    private List<String> sonidos;
    private String imagen1, imagen2;
    private String imagenCorrecta;
    private String sonido;
    private String archivo;
    private int cantidadCorrectas;
    private int cantidadEjercicios;
            
    @PostConstruct
    public void Init(){
        System.out.println("Se construyó otra vez");
        nombres = new ArrayList();
        imagenes = new ArrayList();
        sonidos = new ArrayList();
    }
    
    public void cargarImagenesActuales(){
        System.out.println("Archivo: " + archivo);
        armarListas(archivo);
        int index1, index2;
        index1 =  (int)Math.floor(Math.random()*(sonidos.size()));
        sonido = sonidos.get(index1);
        imagenCorrecta = imagenes.get(index1);
        
        //Seleccion aleatoria de las imagenes hasta encontrar el asociado con el sonido,
        index2 =  (int)Math.floor(Math.random()*(imagenes.size()));
        while (index2 == index1)
            index2 =  (int)Math.floor(Math.random()*(imagenes.size()));            
        
        //Aleatorizar quien es Imagen1.
        double random = Math.random();
        if (random <= 0.5){
            imagen1 = imagenes.get(index1);
            imagen2 = imagenes.get(index2);
        }
        else{
            imagen1 = imagenes.get(index2);
            imagen2 = imagenes.get(index1);
        }
    }
    
    public void validarEleccion(String eleccion){
        if(eleccion.equals(imagenCorrecta)){
            cantidadCorrectas++;
        }
        System.out.println("Correctas: " + cantidadCorrectas);
        cantidadEjercicios++;
        if(cantidadEjercicios>=10){
            terminarLeccion();
        }
        cargarImagenesActuales();
    }
    
    public void terminarLeccion(){
        try {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath() + "/faces/alumno.xhtml?faces-redirect=true");
        } catch (IOException ex) {
            System.out.println("Ocurrio un error en EjercicioImagenes");
        }
    }
    
    public void handleComplete(){
        System.out.println("Leccion terminada");
        FacesMessage message = new FacesMessage();
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        message.setSummary("Calificacion Ejercicio:");
        message.setDetail("Correcto..! Buen trabajo.");
        addMessage(message);
    }
    
    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void armarListas(String archivo){
        ExternalContext extContext=FacesContext.getCurrentInstance().getExternalContext();
        try (BufferedReader br = new BufferedReader(new FileReader(extContext.getRealPath(dirLeccion + archivo + ".txt")))){
            String sCurrentLine;
            int array=0;
            while ((sCurrentLine = br.readLine()) != null) {
               	if(sCurrentLine.equals("nombres")){
                    array=1;
                }
                else if(sCurrentLine.equals("imagenes")){
                    array=2;
                }
                else if(sCurrentLine.equals("sonidos")){
                    array=3;
                }
                else{
                    if(array==1){
                        nombres.add(sCurrentLine);
                    }
                    else if(array==2){
                        imagenes.add(sCurrentLine);
                    }
                    else if (array ==3)
                        sonidos.add(sCurrentLine);
                }
            }
        } 
        catch (Exception e) {
            e.printStackTrace();            
	}        
    }

    public String getImagen1() {
        return imagen1;
    }

    public void setImagen1(String imagen1) {
        this.imagen1 = imagen1;
    }

    public String getImagen2() {
        return imagen2;
    }

    public void setImagen2(String imagen2) {
        this.imagen2 = imagen2;
    }

    public String getImagenCorrecta() {
        return imagenCorrecta;
    }

    public void setImagenCorrecta(String imagenCorrecta) {
        this.imagenCorrecta = imagenCorrecta;
    }

    public String getSonido() {
        return sonido;
    }

    public void setSonido(String sonido) {
        this.sonido = sonido;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public int getCantidadCorrectas() {
        return cantidadCorrectas;
    }

    public void setCantidadCorrectas(int cantidadCorrectas) {
        this.cantidadCorrectas = cantidadCorrectas;
    }

    public int getCantidadEjercicios() {
        return cantidadEjercicios;
    }

    public void setCantidadEjercicios(int cantidadEjercicios) {
        this.cantidadEjercicios = cantidadEjercicios;
    }
    
    
}

package beans;

import java.io.BufferedReader;
import java.io.FileReader;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
    
            
    @PostConstruct
    public void Init(){
        nombres = new ArrayList();
        imagenes = new ArrayList();
        sonidos = new ArrayList();
    }
    
    public void cargarImagenesActuales(String archivo){
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
}

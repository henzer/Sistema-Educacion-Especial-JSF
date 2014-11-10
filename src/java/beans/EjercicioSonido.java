/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Cesar Luis
 */
@ManagedBean(name = "ejercicioSonido")
@ViewScoped
public class EjercicioSonido implements Serializable{
    private String dirSonido;
    private String dirImagen1;
    private String dirImagen2;
    private String dirImagenResultado;    
    
    private String dirLeccion="//resources//txtLecciones//";
    private List<String> nombres;
    private List<String> imagenes;
    private List<String> sonidos;    
    private boolean bandera = false;
    
    @PostConstruct
    public void init(){
        if (bandera == false){
            bandera = true;
            nombres=new ArrayList<String>();
            imagenes=new ArrayList<String>();
            sonidos=new ArrayList<String>();

            this.armarListas("Abecedario");
            this.obtenerElementosEjercicio();
            System.out.println("dirImagen1: " + dirImagen1);
            System.out.println("dirImagen2: " + dirImagen2);        
        }
    }
    
    
        
    
    private void obtenerElementosEjercicio(){
        int index1, index2;
        index1 =  (int)Math.floor(Math.random()*(sonidos.size()));
        dirSonido = sonidos.get(index1);
        dirImagenResultado = imagenes.get(index1);
        
        //Seleccion aleatoria de las imagenes hasta encontrar el asociado con el sonido,
        index2 =  (int)Math.floor(Math.random()*(imagenes.size()));
        while (index2 == index1)
            index2 =  (int)Math.floor(Math.random()*(imagenes.size()));            
        
        //Aleatorizar quien es Imagen1.
        double random = Math.random();
        if (random <= 0.5){
            dirImagen1 = imagenes.get(index1);
            dirImagen2 = imagenes.get(index2);
        }
        else{
            dirImagen1 = imagenes.get(index2);
            dirImagen2 = imagenes.get(index1);
        }
    }
    
    private void armarListas(String archivo){
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

    public String getDirSonido() {
        return dirSonido;
    }

    public void setDirSonido(String dirSonido) {
        this.dirSonido = dirSonido;
    }

    public String getDirImagen1() {
        return dirImagen1;
    }

    public void setDirImagen1(String dirImagen1) {
        this.dirImagen1 = dirImagen1;
    }

    public String getDirImagen2() {
        return dirImagen2;
    }

    public void setDirImagen2(String dirImagen2) {
        this.dirImagen2 = dirImagen2;
    }

    public String getDirImagenResultado() {
        return dirImagenResultado;
    }

    public void setDirImagenResultado(String dirImagenResultado) {
        this.dirImagenResultado = dirImagenResultado;
    }
    
    public void imagenSeleccionada(String idImage){
        System.out.println("Imagen Clickeada: " + idImage);
        bandera = false;
    }
    
    
    
    
}

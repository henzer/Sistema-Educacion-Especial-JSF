/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

@ManagedBean(name = "ejercicioAsociacion1")
@ViewScoped
public class EjercicioAsociacion1 implements Serializable {
    
    private List<String> imagesName;
    private List<String> imagesIdent;    
    private List<String> droppedImages;    
    private List<String> result;
    private int auxNext = -1;    
    
    
    private String dirLeccion="//resources//txtLecciones//";   
    private List<String> nombres;
    private List<String> imagenes;    
    
    @PostConstruct
    public void Init(){
        
        imagesName = new ArrayList();
        droppedImages = new ArrayList();        
        result = new ArrayList();
        nombres = new ArrayList();
        imagenes = new ArrayList();
                
        this.armarListas("Abecedario");
        System.out.println("Imagenes encontrados: " + imagenes.toString());
        
        for (int i = 0; i < 3; i++) {
            int elegir =  (int)Math.floor(Math.random()*(imagenes.size()));           
            while (imagesName.contains(imagenes.get(elegir))){
                elegir =  (int)Math.floor(Math.random()*(imagenes.size()));
            }
            imagesName.add(imagenes.get(elegir));
            result.add(nombres.get(elegir));
            droppedImages.add("question-mark.png");            
        }
        
        this.desordenarResultado();
        
        System.out.println("imagesName: " + imagesName.toString());
        System.out.println("droppedImages: " + droppedImages.toString());
        System.out.println("result: " + result.toString());
        
    }
    
    private void ordenarImagenes(){
        result.addAll(imagesIdent);
        String auxName;
        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < result.size()-1; j++) {
                if (result.get(j).charAt(0) > result.get(j+1).charAt(0)){
                    auxName = result.get(j);
                    result.set(j, result.get(j+1));
                    result.set(j+1, auxName);
                }
            }
        }
    }
    
    private void desordenarResultado(){
        String aux;
        int index1, index2;
        for (int i = 0; i < 100; i++) {
            index1 =  (int)Math.floor(Math.random()*(result.size()));
            index2 = (int)Math.floor(Math.random()*(result.size()));
            while (index2 == index1)
                index2 = (int)Math.floor(Math.random()*(result.size()));
            aux = result.get(index1);
            result.set(index1, result.get(index2));
            result.set(index2, aux);            
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
                }
            }
        } 
        catch (Exception e) {
            e.printStackTrace();            
	}        
    }
    
    
    
    public void getAsociations(){
        String[] asociation = {"",""};
        
    }
    
    public List<String> getImagesName() {
        return imagesName;
    }

    public void setImagesName(List<String> imagesName) {
        this.imagesName = imagesName;
    }

    public List<String> getDroppedImages() {
        return droppedImages;
    }

    public void setDroppedImages(List<String> droppedImages) {
        this.droppedImages = droppedImages;
    }

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }    
    
    public boolean hasImages(){
        return imagesName.isEmpty();
    }
    
    public void onImageDrop(DragDropEvent ddEvent) {
        String imagen = ddEvent.getData().toString();
        System.out.println("Imagen dropeada: " + imagen);
        String elementoDropeado = String.valueOf(ddEvent.getDropId().charAt(ddEvent.getDropId().indexOf("dropArea")-2));
        int index = Integer.parseInt(elementoDropeado);
        System.out.println("Elemento: " + index);

        droppedImages.set(index, imagen);        
        index = imagesName.indexOf(imagen);
        imagesName.set(index, "Blanco.png");
                
        System.out.println("Images name: " + imagesName.toString());
        System.out.println("Images Droped: " + droppedImages.toString());
        
        this.calificarEjercicio();        
    }
    
    public String nextElementOfResult(){
        if (auxNext == 2)
            auxNext = -1;
        auxNext++;
        return result.get(auxNext);        
    }
    
    
    public void calificarEjercicio(){
        boolean finish = true;
        for (String img: droppedImages) {
            if (img.equals("question-mark.png")){
                finish = false;
                break;
            }
        }
        
        if (finish){
            boolean correct = true;
            for (int i = 0; i < 3; i++) {
                if (!droppedImages.get(i).equals("letra"+result.get(i)+".jpg")){
                    correct = false;
                    break;
                }
            }

            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_INFO);
            message.setSummary("Calificacion Ejercicio:");
            if (correct){
                message.setDetail("Correcto..! Buen trabajo.");
                System.out.println("Correcto..! Buen trabajo.");
            }
            else{
                message.setDetail("Incorrecto..! Intente de nuevo.");
                System.out.println("Incorrecto..! Intente de nuevo.");
            }
            addMessage(message);
        }            
    }
    
    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void reiniciarEjercicio(){
        
        System.out.println("Reiniciando Ejercicio...!!");
        
        auxNext = -1;
        imagesName.clear();
        droppedImages.clear();
        
        for (int i = 0; i < 3; i++) {
            int elegir =  (int)Math.floor(Math.random()*(imagenes.size()));           
            while (imagesName.contains(imagenes.get(elegir))){
                elegir =  (int)Math.floor(Math.random()*(imagenes.size()));                
            }
            imagesName.add(imagenes.get(elegir));
            droppedImages.add("question-mark.png");
        }
        
        System.out.println("Images name: " + imagesName.toString());
        System.out.println("Images Droped: " + droppedImages.toString());                        
        
    }

    public void hacerEjercicio(String leccion){
    }
    
    private class Image{
        String ident;
        String nombre;
        public Image(){}

        public Image(String ident, String nombre) {
            this.ident = ident;
            this.nombre = nombre;
        }

        public String getIdent() {
            return ident;
        }

        public void setIdent(String ident) {
            this.ident = ident;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }
        
        
    }
}

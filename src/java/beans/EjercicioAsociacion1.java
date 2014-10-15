/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.DragDropEvent;

/**
 *
 * @author Cesar Luis
 */

@ManagedBean(name = "ejercicioAsociacion1")
@ViewScoped
public class EjercicioAsociacion1 implements Serializable {
    
    List<String> imagesName;
    List<String> droppedImages;    
    
    @PostConstruct
    public void Init(){
        imagesName = new ArrayList();
        droppedImages = new ArrayList();
        for (int i = 0; i < 5; i++) {
            imagesName.add("logo"+String.valueOf(i+1));
            droppedImages.add(null);
        }
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
        imagesName.remove(imagen);

        System.out.println("Images name: " + imagesName.toString());
        System.out.println("Images Droped: " + droppedImages.toString());
    }
}

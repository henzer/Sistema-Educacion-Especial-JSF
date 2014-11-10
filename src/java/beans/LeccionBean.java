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
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author DiegoRenato
 */
@ManagedBean
@ViewScoped
public class LeccionBean implements Serializable{
    //direccion de la leccion
    private String dirLeccion="//resources//txtLecciones//";
    private List<Contenido> tabs;
    private List<String> nombres;
    private List<String> imagenes;
    private List<String> sonidos;
    private boolean encurso;
    @PostConstruct
    public void init(){
        tabs=new ArrayList<Contenido>();
        nombres=new ArrayList<String>();
        imagenes=new ArrayList<String>();
        sonidos=new ArrayList<String>();
        encurso=true;
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
                    else if(array==3){
                        sonidos.add(sCurrentLine);
                    }
                }
            }
        } 
        catch (Exception e) {
            e.printStackTrace();            
	}
        for (int i=0;i<nombres.size();i++){
            tabs.add(new Contenido(nombres.get(i),sonidos.get(i),imagenes.get(i)));
        }        
    }

    public List<Contenido> getTabs() {
        return tabs;
    }
    
    public List<Contenido> obtenerTabs(String archivo){
        armarListas(archivo);
        return tabs;
    }
    public void checkFalse(Contenido tab){
        int index=tabs.indexOf(tab);
        if(index==tabs.size()-1){
            encurso=false;
            System.out.println("se desactivo");
        }
        else{
            System.out.println("se activo");
        }
    }

    public void setTabs(List<Contenido> tabs) {
        this.tabs = tabs;
    }

    public boolean isEncurso() {
        return encurso;
    }

    public void setEncurso(boolean encurso) {
        this.encurso = encurso;
    }
    public void prueba(){
        System.out.println("cambiando");
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import controladores.ControlAlumno;
import controladores.ControlUnidad;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import modelos.Leccion;
import modelos.Unidad;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "gestionun")
@SessionScoped
public class GestionUnidadesBean implements Serializable {
    private String dirLeccion="//resources//txtLecciones//";
    private List<Unidad> list;
    private Unidad unidad = new Unidad();
    private Unidad nuevo = new Unidad();
    private Unidad tempUnidad = new Unidad();
    private List<List<Contenido>> leccionesD;
    private boolean edit;
    private ControlUnidad controlU;
    @PostConstruct
    public void init() {
        controlU=new ControlUnidad();
        list = controlU.obtenerTodas();
    }
    
    public void mostrarVentanaModal(){
        nuevo = new Unidad();
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('ventanaModalUnidad2').show()");
    }
    
    public void add() {
        System.out.println(nuevo);
        int res=nuevo.agregarConRetorno();
        if (res !=0){
            nuevo.setIdUnidad(res);
            list.add(nuevo);
            addMessage("Success", "Agregado con exito");
        }else{
            addMessage("Error", "No se puede agregar");
        }
        nuevo = new Unidad();
        
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('ventanaModalUnidad2').hide()");
        
    }
    public String defUnidad(Unidad unidad){
        tempUnidad=unidad;
        return "crearLeccion?indexU=" + list.indexOf(unidad)+"faces-redirect=true";
    }
    public void edit(Unidad unidad) {
        this.unidad = unidad;
        edit = true;
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('ventanaModalUnidad1').show()");
    }

    public void save() {
        if(unidad.modificar()){
            addMessage("Success", "Modificado con exito");
        }else{
            addMessage("Error", "Ocurrio un error");
        }
        unidad = new Unidad();
        edit = false;
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('ventanaModalUnidad1').hide()");
        
    }

    public void delete(Unidad unidad) {
        if(unidad.eliminar()){
            list.remove(unidad);
            addMessage("Success", "Eliminado con exito");
        }else{
            addMessage("Error", "No se puede eliminar");
        }
        
    }

    public List<Unidad> getList() {
        return list;
    }

    public Unidad getUnidad() {
        return unidad;
    }

    public Unidad getNuevo() {
        return nuevo;
    }

    public void setNuevo(Unidad nuevo) {
        this.nuevo = nuevo;
    }

    
    public boolean isEdit() {
        return edit;
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public List<List<Contenido>> getLeccionesD() {
        return leccionesD;
    }

    public void setLeccionesD(List<List<Contenido>> leccionesD) {
        this.leccionesD = leccionesD;
    }

    public Unidad getTempUnidad() {
        return tempUnidad;
    }

    public void setTempUnidad(Unidad tempUnidad) {
        this.tempUnidad = tempUnidad;
    }
    public void eliminate(Leccion leccion, Contenido contenido){
        leccion.eliminar(contenido);
    }
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import controladores.ControlAlumno;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelos.Alumno;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "gestiona")
@SessionScoped
public class GestionAlumnosBean implements Serializable {

    private List<Alumno> list;
    private Alumno alumno = new Alumno();
    private Alumno nuevo = new Alumno();
    
    private boolean edit;
    private ControlAlumno controlA;
    @PostConstruct
    public void init() {
        controlA=new ControlAlumno();
        list = controlA.obtenerTodos();
    }
    
    public void mostrarVentanaModal(){
        nuevo = new Alumno();
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("ventanaModalAlumno2.show()");
    }
    
    public void add() {
        int res=nuevo.agregarConRetorno();
        if (res !=0){
            nuevo.setIdAlumno(res);
            list.add(nuevo);
            addMessage("Success", "Agregado con exito");
        }else{
            addMessage("Error", "No se puede agregar");
        }
        nuevo = new Alumno();
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("ventanaModalAlumno2.hide()");
        
    }

    public void edit(Alumno alumno) {
        this.alumno = alumno;
        edit = true;
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("ventanaModalAlumno1.show()");
    }

    public void save() {
        if(alumno.modificar()){
            addMessage("Success", "Modificado con exito");
        }else{
            addMessage("Error", "Ocurrio un error");
        }
        alumno = new Alumno();
        edit = false;
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("ventanaModalAlumno1.hide()");
        
    }

    public void delete(Alumno alumno) {
        if(alumno.eliminar()){
            list.remove(alumno);
            addMessage("Success", "Eliminado con exito");
        }else{
            addMessage("Error", "No se puede eliminar");
        }
        
    }

    public List<Alumno> getList() {
        return list;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public Alumno getNuevo() {
        return nuevo;
    }

    public void setNuevo(Alumno nuevo) {
        this.nuevo = nuevo;
    }

    
    public boolean isEdit() {
        return edit;
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }


}
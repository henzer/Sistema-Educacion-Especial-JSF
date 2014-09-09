/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;
import controladores.ControlProfesor;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modelos.Profesor;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "gestionp")
@SessionScoped
public class GestionProfesoresBean implements Serializable {

    private List<Profesor> list;
    private Profesor profesor = new Profesor();
    private Profesor nuevo = new Profesor();
    private boolean edit;
    private ControlProfesor controlP;
    
    @PostConstruct
    public void init() {
        controlP=new ControlProfesor();
        list = controlP.obtenerTodos();
    }
    
    public void mostrarVentanaModal(){
        nuevo = new Profesor();
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("ventanaModalProfesor2.show()");
    }
    
    public void add() {
        int res=nuevo.agregarConRetorno();
        if (res !=0){
            nuevo.setIdProfesor(res);
            list.add(nuevo);
            addMessage("Success", "Agregado con exito");
        }else{
            addMessage("Error", "No se puede agregar");
        }
        nuevo = new Profesor();
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("ventanaModalProfesor2.hide()");
        
    }

    public void edit(Profesor profesor) {
        this.profesor = profesor;
        edit = true;
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("ventanaModalProfesor1.show()");
    }

    public void save() {
        System.out.println(profesor.getNombreProfesor());
        if(profesor.modificar()){
            addMessage("Success", "Modificado con exito");
        }else{
            addMessage("Error", "Ocurrio un error");
        }
        profesor = new Profesor();
        edit = false;
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("ventanaModalProfesor1.hide()");
        
    }

    public void delete(Profesor profesor) {
        if(profesor.eliminar()){
            list.remove(profesor);
            addMessage("Success", "Eliminado con exito");
        }else{
            addMessage("Error", "No se puede eliminar");
        }
        
    }

    public List<Profesor> getList() {
        return list;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public Profesor getNuevo() {
        return nuevo;
    }

    public void setNuevo(Profesor nuevo) {
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

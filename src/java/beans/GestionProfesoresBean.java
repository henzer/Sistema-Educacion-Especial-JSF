/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import controladores.ControlAlumno;
import controladores.ControlProfesor;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelos.Alumno;
import modelos.Profesor;
import modelos.Usuario;

@ManagedBean(name = "gestionp")
@ViewScoped
public class GestionProfesoresBean implements Serializable {

    private List<Profesor> list;
    private Profesor profesor = new Profesor();
    private boolean edit;
    private ControlProfesor controlP;
    @PostConstruct
    public void init() {
        controlP=new ControlProfesor();
        list = controlP.obtenerTodos();
    }
    
    public void add() {
        int res=profesor.agregarConRetorno();
        if (res !=0){
            profesor.setIdProfesor(res);
            list.add(profesor);
        }
        profesor = new Profesor(); 
    }

    public void edit(Profesor profesor) {
        this.profesor = profesor;
        edit = true;
    }

    public void save() {
        profesor.modificar();
        profesor = new Profesor();
        edit = false;
    }

    public void delete(Profesor profesor) {
        profesor.eliminar();
        list.remove(profesor);
    }

    public List<Profesor> getList() {
        return list;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public boolean isEdit() {
        return edit;
    }


}

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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import modelos.Alumno;
import modelos.Profesor;
import modelos.Usuario;

@ManagedBean(name = "gestiona")
@SessionScoped
public class GestionAlumnosBean implements Serializable {

    private List<Alumno> list;
    private Alumno alumno = new Alumno();
    private boolean edit;
    private ControlAlumno controlA;
    @PostConstruct
    public void init() {
        controlA=new ControlAlumno();
        list = controlA.obtenerTodos();
    }
    
    public void add() {
        int res=alumno.agregarConRetorno();
        if (res !=0){
            alumno.setIdAlumno(res);
            list.add(alumno);
        }
        alumno = new Alumno(); 
    }

    public void edit(Alumno alumno) {
        this.alumno = alumno;
        edit = true;
    }

    public void save() {
        alumno.modificar();
        alumno = new Alumno();
        edit = false;
    }

    public void delete(Alumno alumno) {
        alumno.eliminar();
        list.remove(alumno);
    }

    public List<Alumno> getList() {
        return list;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public boolean isEdit() {
        return edit;
    }


}

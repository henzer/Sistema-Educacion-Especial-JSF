/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import controladores.ControlLeccion;
import controladores.ControlUnidad;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import modelos.Leccion;
import modelos.Unidad;

@ManagedBean (name="ctrlAlumno")
@ViewScoped

public class ControlInterfazAlumno implements Serializable {
    private ControlUnidad ctrlUnidad;
    private List<Unidad> lista;
    
    @PostConstruct
    public void init(){
        ctrlUnidad =  new ControlUnidad();
        lista =  ctrlUnidad.obtenerTodas();
        System.out.println("Se recargo otro vez: " + lista.size());
    }

    public List<Unidad> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Unidad> lista) {
        this.lista = lista;
    }
    
    public String irLeccion(Leccion leccion){        
        return "leccion?idLeccion=" + leccion.getIdLeccion() + "&nombreLeccion="+leccion.getNombreLeccion()+"faces-redirect=true";
    }
    
    public String cerrarSesion(){
        ((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true)).invalidate();
        return "index.xhtml";
    }
}

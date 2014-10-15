/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
@ManagedBean (name="ctrlProfesor")
@SessionScoped

public class ControlInterfazProfesor implements Serializable {
    public String cerrarSesion(){
        ((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true)).invalidate();
        return "index.xhtml";
    }
}

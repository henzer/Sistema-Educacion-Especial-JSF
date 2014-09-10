package beans;

import controladores.ControlAlumno;
import controladores.ControlProfesor;
import controladores.ControlUsuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import modelos.Alumno;
import modelos.Profesor;
import modelos.Usuario;
@ManagedBean (name = "login")
@SessionScoped
public class Login implements Serializable{
    private String nombreUsuario;
    private String password;
    private ControlUsuario controlU;
    private Usuario actual;
    
    @PostConstruct
    public void init() {
        controlU=new ControlUsuario();
    }
    
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String autenticar(){
        actual = null;
        Usuario actual = controlU.autenticarUsuario(nombreUsuario, password);
        if(actual!=null){
            HttpSession miSesion = ((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true));
            miSesion.setAttribute("usuarioActual", actual);
            if(actual.getTipo().equals("1")){
                return "administrador.xhtml";
            }else if(actual.getTipo().equals("2")){
                ControlProfesor ctlProfesor = new ControlProfesor();
                Profesor p = ctlProfesor.getProfesor(actual.getIdUsuario());
                if(p==null){
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Peligro", "El usuario ingresado no es valido");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return "index.xhtml";
                }
                miSesion.setAttribute("profesorActual", p);
                return "profesor.xhtml";
            }else{
                ControlAlumno ctlAlumno = new ControlAlumno();
                Alumno a = ctlAlumno.getAlumno(actual.getIdUsuario());
                if(a==null){
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Peligro", "El usuario ingresado no es valido");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return "index.xhtml";
                }
                miSesion.setAttribute("alumnoActual", a);
                return "alumno.xhtml";
            }
        }else{
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Incorrecto", "Credenciales incorrectas");
            FacesContext.getCurrentInstance().addMessage(null, message);
        
        }
        return "index.xhtml";
    }
    
}

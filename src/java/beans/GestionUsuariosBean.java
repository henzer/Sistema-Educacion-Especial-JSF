/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package beans;

/**
 *
 * @author semilla2013
 */
import controladores.ControlUsuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import modelos.Usuario;

@ManagedBean(name = "gestionu")
@SessionScoped
public class GestionUsuariosBean implements Serializable {

    private List<Usuario> list;
    private Usuario usuario = new Usuario();
    private boolean edit;
    private ControlUsuario controlU;
    
    @PostConstruct
    public void init() {
        // list = dao.list();
        // Actually, you should retrieve the list from DAO. This is just for demo.
        System.out.println("ocurrio algo");
        controlU=new ControlUsuario();
        list = controlU.obtenerTodos();
    }
    
    public void add() {
        // dao.create(item);
        // Actually, the DAO should already have set the ID from DB. This is just for demo.
        usuario.setIdUsuario(usuario.agregarConRetorno());
        list.add(usuario);
        usuario = new Usuario(); // Reset placeholder.
    }

    public void edit(Usuario usuario) {
        this.usuario = usuario;
        edit = true;
    }

    public void save() {
        // dao.update(item);
        usuario.modificar();
        usuario = new Usuario(); // Reset placeholder.
        edit = false;
    }

    public void delete(Usuario usuario) {
        // dao.delete(item);
        usuario.eliminar();
        list.remove(usuario);
    }

    public List<Usuario> getList() {
        return list;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public boolean isEdit() {
        return edit;
    }

    // Other getters/setters are actually unnecessary. Feel free to add them though.

}

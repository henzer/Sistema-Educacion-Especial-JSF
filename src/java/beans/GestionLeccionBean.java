/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import controladores.ControlUnidad;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import modelos.Leccion;
import modelos.Unidad;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Estudiante
 */
@ManagedBean(name="gestionL")
@ViewScoped
public class GestionLeccionBean {
    private static final int BUFFER_SIZE = 6124;
    private boolean render;
    private Contenido actualV;
    private String nombre;
    private UploadedFile imagen;
    private UploadedFile sonido;
    private Unidad actual;
    private ControlUnidad cUnidad;
    private String editandoS;
    private List<Contenido> editandoL;
    private Leccion leccionActual;
    private List<Leccion> nLecciones;
    private String leccionN;
    @PostConstruct
    public void init(){
        editandoL=new ArrayList<Contenido>();
        render=false;
        nLecciones=new ArrayList<Leccion>();
        actualV=new Contenido("","","");
        editandoS="Elija una leccion para editar...";
        nombre="";
        leccionN="";
        imagen=null;
        sonido=null;
    }
    public void nuevaLeccion(){
        if(!leccionN.equals("")){
            Leccion nueva= new Leccion(-1,actual.getIdUnidad(),actual.getNombreUnidad(),leccionN,leccionN+".txt");
            leccionN="";
            int res=nueva.agregarConRetorno();
            System.out.println("Este es el int: " + res);
            nueva.setIdLeccion(res);
            actual.getLecciones().add(nueva);
            nLecciones.add(nueva);
        }
    }
    public String iniciar(int index){
        cUnidad=new ControlUnidad();
        actual=cUnidad.obtenerTodas().get(index);
        return actual.getNombreUnidad();
    }
    
    public Unidad getActual() {
        return actual;
    }

    public void setActual(Unidad actual) {
        this.actual = actual;
    }
    
    public void eliminar(Contenido contenido){
        leccionActual.eliminar(contenido);
    }

    public UploadedFile getImagen() {
        return imagen;
    }

    public void setImagen(UploadedFile imagen) {
        this.imagen = imagen;
    }

    public UploadedFile getSonido() {
        return sonido;
    }

    public void setSonido(UploadedFile sonido) {
        this.sonido = sonido;
    }
    public void upload(UploadedFile file,int tipo) {
        String idProfesor="";
        if(file != null) {
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            ExternalContext extContext=FacesContext.getCurrentInstance().getExternalContext();
            File result=null;
            if (tipo==0){
                result = new File(extContext.getRealPath("//resources//images//" +idProfesor+ file.getFileName()));
            }
            else if(tipo==1){
                result = new File(extContext.getRealPath("//resources//sonidos//" +idProfesor+ file.getFileName()));
            }
            boolean res=true;
            try {
                InputStream iStream= file.getInputstream();
                FileOutputStream oStream= new FileOutputStream(result);
                byte[] buffer = new byte[BUFFER_SIZE];
                int bulk;
                while (true) {
                    bulk = iStream.read(buffer);
                    if (bulk < 0) {
                        break;
                    }
                    oStream.write(buffer, 0, bulk);
                    oStream.flush();
                }
                oStream.close();
                iStream.close();


            } catch (IOException ex) {
                Logger.getLogger(UploadBean.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("error culley");
                res=false;
            }
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void save(){
        System.out.println("nombre: "+nombre);
        if(!nombre.equals("")&&sonido!=null&&imagen!=null&&leccionActual!=null){
            //guardar imagen
            upload(imagen,0);
            //guardar sonido
            upload(sonido,1);
            System.out.println("size before "+leccionActual.getContenido().size());
            leccionActual.addContenido(new Contenido(nombre,sonido.getFileName(),imagen.getFileName()));
            leccionActual.isModified();
            System.out.println("Nuevo contenido nombre: "+nombre+" imagen: "+imagen.getFileName()+" sonido: "+sonido.getFileName());
            System.out.println("size after "+leccionActual.getContenido().size());
            nombre="";
            sonido=null;
            imagen=null;
        }
        else{
            System.out.println("contenido null");
        }
        
    }
    public void delete(Leccion leccion){
        actual.getLecciones().remove(leccion);
        nLecciones.remove(leccion);
        leccion.eliminarL();
    }
    public void editando(Leccion leccion){
        editandoS=leccion.getNombreLeccion();
        editandoL=leccion.getContenido();
        leccionActual=leccion;
        leccionActual.setModified(true);
        render=true;
    }

    public String getEditandoS() {
        return editandoS;
    }

    public void setEditandoS(String editandoS) {
        this.editandoS = editandoS;
    }

    public List<Contenido> getEditandoL() {
        return editandoL;
    }

    public void setEditandoL(List<Contenido> editandoL) {
        this.editandoL = editandoL;
    }
    public String guardarSalir(){
        
        return "gestionUnidad?faces-redirect=true";
    }

    public List<Leccion> getnLecciones() {
        return nLecciones;
    }

    public void setnLecciones(List<Leccion> nLecciones) {
        this.nLecciones = nLecciones;
    }

    public String getLeccionN() {
        return leccionN;
    }

    public void setLeccionN(String leccionN) {
        this.leccionN = leccionN;
    }

    public Contenido getActualV() {
        return actualV;
    }

    public void setActualV(Contenido actualV) {
        this.actualV = actualV;
    }

    public boolean isRender() {
        return render;
    }

    public void setRender(boolean render) {
        this.render = render;
    }
    
}
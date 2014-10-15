package beans; 
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
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
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@ViewScoped
public class UploadBean implements Serializable{
    private static final int BUFFER_SIZE = 6124;
    private UploadedFile imagen;
    private UploadedFile sonido;
    private ArrayList<String> nombres;
    private ArrayList<String> sonidos;
    private ArrayList<String> imagenes;
    private String nombre="";
    private List<Contenido> datos;
    @PostConstruct
    public void init(){
        sonidos=new ArrayList<String>();
        imagenes=new ArrayList<String>();
        nombres=new ArrayList<String>();
        datos=new ArrayList<Contenido>();
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void save(){
        //guardar imagen
        nombres.add(nombre);
        upload(imagen,0);
        //guardar sonido
        upload(sonido,1);
        datos.add(new Contenido(nombre,imagen.getFileName(),sonido.getFileName()));
        nombre="";
        sonido=null;
        imagen=null;
    }
    public void crear(){
        String salida="nombres";
        for (int x=0;x<nombres.size();x++){
            salida+="\n"+nombres.get(x);
        }
        salida+="\nimagenes";
        for (int x=0;x<imagenes.size();x++){
            salida+="\n"+imagenes.get(x);
        }
        salida+="\nsonidos";
        for (int x=0;x<sonidos.size();x++){
            salida+="\n"+sonidos.get(x);
        }
        //crear archivo de texto
        try {
            ExternalContext extContext=FacesContext.getCurrentInstance().getExternalContext();
            File file = new File(extContext.getRealPath("//resources//txtLecciones//"+"leccion.txt"));
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(salida);
            bw.close();
            System.out.println("Done");
        } 
        catch (IOException e) {
            e.printStackTrace();
	}
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
                imagenes.add(file.getFileName());
            }
            else if(tipo==1){
                result = new File(extContext.getRealPath("//resources//sonidos//" +idProfesor+ file.getFileName()));
                sonidos.add(file.getFileName());
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

    public List<Contenido> getDatos() {
        return datos;
    }

    public void setDatos(List<Contenido> datos) {
        this.datos = datos;
    }
    
}

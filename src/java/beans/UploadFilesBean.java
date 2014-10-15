/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package beans;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
 
@ManagedBean(name="uploadb")
public class UploadFilesBean {
    private static final int BUFFER_SIZE = 6124;  
    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
        UploadedFile fileu =event.getFile();
        ExternalContext extContext=FacesContext.getCurrentInstance().getExternalContext();
        System.out.println(extContext.getRealPath("//WEB-INF//files//" + event.getFile().getFileName()));
        File result = new File(extContext.getRealPath("//WEB-INF//files//" + event.getFile().getFileName()));
        try {
            InputStream iStream= fileu.getInputstream();
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
            System.out.println("funciono supuestamente");
           
        } catch (IOException ex) {
            Logger.getLogger(UploadFilesBean.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error culley");
        }
        
    }
}
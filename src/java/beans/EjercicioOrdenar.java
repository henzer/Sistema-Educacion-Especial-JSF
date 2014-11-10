/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;
 

/**
 *
 * @author Cesar Luis
 */
@ManagedBean(name = "ejercicioOrdenar")
@ViewScoped
public class EjercicioOrdenar implements Serializable{
    private DashboardModel model, auxModel;
    private ArrayList<String> titles;
    private ArrayList<String> objects;
     
    @PostConstruct
    public void init() {
        model = new DefaultDashboardModel();
        DashboardColumn column1 = new DefaultDashboardColumn();
        DashboardColumn column2 = new DefaultDashboardColumn();
        DashboardColumn column3 = new DefaultDashboardColumn();
        
        titles = new ArrayList();
        objects = new ArrayList();
        this.getComponents();
        
        column1.addWidget("columna1");
        column1.addWidget(objects.get(2));
        column2.addWidget("columna2");
        column2.addWidget(objects.get(0));
        column3.addWidget("columna3");
        column3.addWidget(objects.get(1));
        
        model.addColumn(column1);
        model.addColumn(column2);
        model.addColumn(column3);
        
    }
    
    private void getComponents(){
        for (int i = 0; i < 5; i++) {
            titles.add("Imagen " + String.valueOf(i+1));            
        }
        objects.add("letraA");
        objects.add("letraB");
        objects.add("letraC");
    }
     
    public void handleReorder(DashboardReorderEvent event) {
        
        //Esta linea pone al widget molde siempre de primero
        model.getColumn(event.getColumnIndex()).reorderWidget(0, "columna"+String.valueOf(event.getColumnIndex()+1));
        //Este bloque hace el intercambio de widgets
        int index = event.getColumnIndex();
        for (int i = 0; i < model.getColumn(index).getWidgetCount(); i++) {
            String widget = model.getColumn(index).getWidget(i);
            if (!widget.equals("columna"+String.valueOf(index+1)) && !widget.equals(event.getWidgetId())){
                model.getColumn(event.getSenderColumnIndex()).addWidget(widget);
                model.getColumn(index).removeWidget(widget);                
            }
        }        
        FacesMessage message = new FacesMessage();
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        message.setSummary("Reordered: " + event.getWidgetId());
        message.setDetail("Item index: " + event.getItemIndex() + ", Column index: " + event.getColumnIndex() + ", Sender index: " + event.getSenderColumnIndex());
        addMessage(message);
    }
    
    public void calificarEjercicio(){
        FacesMessage message = new FacesMessage();
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        message.setSummary("Calificacion Ejercicio:");
        if (model.getColumn(0).getWidget(1).equals(objects.get(0)) && model.getColumn(1).getWidget(1).equals(objects.get(1)) && model.getColumn(2).getWidget(1).equals(objects.get(2)))            {
            message.setDetail("Correcto..! Buen trabajo.");
            System.out.println("Correcto..! Buen trabajo.");
        }
        else{
            message.setDetail("Incorrecto..! Intente de nuevo.");
            System.out.println("Incorrecto..! Intente de nuevo.");
        }        
        addMessage(message);
    }
     
    public void handleClose(CloseEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Panel Closed", "Closed panel id:'" + event.getComponent().getId() + "'");         
        addMessage(message);
    }
     
    public void handleToggle(ToggleEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, event.getComponent().getId() + " toggled", "Status:" + event.getVisibility().name());         
        addMessage(message);
    }
     
    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
     
    public DashboardModel getModel() {
        return model;
    }
    
    public ArrayList getTitles(){
        return titles;
    }
    
    public ArrayList getObjects(){
        return objects;
    }    
}
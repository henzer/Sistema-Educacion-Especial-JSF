package beans;

import controladores.ControlNota;
import controladores.ControlUnidad;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import modelos.Alumno;
import modelos.Nota;
import modelos.Unidad;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

@SessionScoped
@ManagedBean (name = "graficaBarraPersonal") 
public class GraficaBarraPersonal implements Serializable{
    private BarChartModel barModel;
    @PostConstruct
    public void init() {
        createBarModels();
    }
    private void createBarModels() {
        createBarModel();
    }
    
    public BarChartModel getBarModel() {
        return barModel;
    }
    
    private void createBarModel() {
        Alumno alumno = (Alumno)((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false)).getAttribute("alumnoActual");
        ControlUnidad ctrlUnidades = new ControlUnidad();
        ArrayList<Unidad> unidades = ctrlUnidades.obtenerTodas();
        ControlNota ctrlNotas = new ControlNota();
      
        barModel = new BarChartModel();
        ChartSeries boy = new ChartSeries();
        for(Unidad u: unidades){
            ArrayList<Nota> notas = ctrlNotas.obtenerNotas(u.getIdUnidad(), "R", alumno.getIdAlumno());
            boy.setLabel(alumno.getNombreAlumno());
            double sumaTotal = 0;
            int cantidadLecciones = notas.size();
            int cantidadEjercicios = 0;
            for(Nota n: notas){
                sumaTotal+= n.getSuma()/n.getCantidad();
                cantidadEjercicios += n.getCantidad();
            }
            boy.set(u.getNombreUnidad(), sumaTotal/cantidadLecciones);
        
        }
        
       
        barModel.addSeries(boy);
        barModel.setTitle("Progreso Personal");
        barModel.setLegendPosition("ne");
         
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Unidades");
        xAxis.setTickAngle(45); 
        
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Nota");
        yAxis.setMin(0);
        yAxis.setMax(100);
        yAxis.setTickCount(3);
        barModel.setShadow(false);
        barModel.setSeriesColors("5473F2");
    }
}

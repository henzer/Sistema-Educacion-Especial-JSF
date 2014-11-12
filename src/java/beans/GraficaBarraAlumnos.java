package beans;
import controladores.ControlAlumno;
import controladores.ControlNota;
import controladores.ControlUnidad;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modelos.Alumno;
import modelos.Nota;
import modelos.Unidad;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
 
@ViewScoped 
@ManagedBean (name = "graficaBarraAlumnos") 
public class GraficaBarraAlumnos implements Serializable {
 
    private HashMap<Integer, BarChartModel> modelos;
    
    @PostConstruct
    public void init() {
        modelos = new HashMap();
        createBarModels();
    }
 
    public BarChartModel getBarModel(int idUnidad) {
        return modelos.get(idUnidad);
    }
    
    private void createBarModels() {
        createBarModel();
    }
     
    private void createBarModel() {
        ControlUnidad ctrlUnidad = new ControlUnidad();
        ArrayList<Unidad> unidades = ctrlUnidad.obtenerTodas();
        ControlAlumno ctrlAlumno = new ControlAlumno();
        ArrayList<Alumno> alumnos = ctrlAlumno.obtenerTodos();
        ControlNota ctrlNota = new ControlNota();
        
        for(Unidad u: unidades){
            BarChartModel barModel = new BarChartModel();
            ChartSeries boys = new ChartSeries();
            boys.setLabel("Alumnos");
            for(Alumno a: alumnos){
                ArrayList<Nota> notas = ctrlNota.obtenerNotas(u.getIdUnidad(), "R", a.getIdAlumno());
                double sumaTotal = 0;
                int cantidadLecciones = notas.size();
                int cantidadEjercicios = 0;
                for(Nota n: notas){
                    sumaTotal+= n.getSuma()/n.getCantidad();
                    cantidadEjercicios += n.getCantidad();
                }
                boys.set(a.getNombreAlumno() + " (" + cantidadEjercicios + "/" + (cantidadLecciones*3) + ")", sumaTotal/cantidadLecciones);
            }
            barModel.addSeries(boys);
            
            barModel.setTitle("Rendimiento de Alumnos");
            barModel.setLegendPosition("ne");

            Axis xAxis = barModel.getAxis(AxisType.X);
            xAxis.setLabel("Alumnos");
            xAxis.setTickAngle(25); 

            Axis yAxis = barModel.getAxis(AxisType.Y);
            yAxis.setLabel("Nota");
            yAxis.setMin(0);
            yAxis.setMax(100);
            yAxis.setTickCount(3);
            barModel.setShadow(false);
            barModel.setSeriesColors("5473F2");
            modelos.put(u.getIdUnidad(), barModel);
        }
    }

    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                        "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());
         
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}


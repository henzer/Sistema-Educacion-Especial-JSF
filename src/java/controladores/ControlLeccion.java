package controladores;

import beans.Contenido;
import conexion.Conexion;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import modelos.Ejercicio;
import modelos.Leccion;

public class ControlLeccion {
    private String dirLeccion="//resources//txtLecciones//";
    public ArrayList<Leccion> obtenerTodas(int idUnidad){
        ResultSet rs= Conexion.getInstancia().hacerConsulta("select idLeccion, leccion.idUnidad, nombreUnidad, nombreLeccion, archivo from leccion inner join unidad on leccion.idUnidad = unidad.idUnidad where leccion.idUnidad = " + idUnidad);
        ArrayList<Leccion> resultado = new ArrayList();
        Leccion leccion;
        ControlEjercicio ctrlEjercicio = new ControlEjercicio();
        try{
            while(rs.next()){
                leccion = new Leccion (rs.getInt("idLeccion"), rs.getInt("idUnidad"), rs.getString("nombreUnidad"), rs.getString("nombreLeccion"), rs.getString("archivo"));
                leccion.setContenido((ArrayList)getContenido(leccion.getNombreLeccion()));
                resultado.add(leccion);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
                Conexion.getInstancia().liberarConexion();
        }
        for (Leccion e: resultado)
            e.setEjercicios(ctrlEjercicio.obtenerTodas(e.getIdLeccion()));
        
        return resultado;
    }
    public List<Contenido> getContenido(String name){
        ExternalContext extContext=FacesContext.getCurrentInstance().getExternalContext();
            ArrayList<String> nombres=new ArrayList<String>();
            ArrayList<String> imagenes=new ArrayList<String>();
            ArrayList<String> sonidos=new ArrayList<String>();
            List<Contenido> tabs= new ArrayList<Contenido>();
            try (BufferedReader br = new BufferedReader(new FileReader(extContext.getRealPath(dirLeccion + name+".txt")))){
                String sCurrentLine;
                int array=0;
                while ((sCurrentLine = br.readLine()) != null) {
                    if(sCurrentLine.equals("nombres")){
                        array=1;
                    }
                    else if(sCurrentLine.equals("imagenes")){
                        array=2;
                    }
                    else if(sCurrentLine.equals("sonidos")){
                        array=3;
                    }
                    else{
                        if(array==1){
                            nombres.add(sCurrentLine);
                        }
                        else if(array==2){
                            imagenes.add(sCurrentLine);
                        }
                        else if(array==3){
                            sonidos.add(sCurrentLine);
                        }
                    }
                }
            } 
            catch (Exception e) {
                e.printStackTrace();            
            }
            for (int i=0;i<nombres.size();i++){
                tabs.add(new Contenido(nombres.get(i),sonidos.get(i),imagenes.get(i)));
            }
            return tabs;
    }
}

package controladores;

import conexion.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelos.Unidad;

public class ControlUnidad {
    
    public ArrayList<Unidad> obtenerTodas(){
        ResultSet rs= Conexion.getInstancia().hacerConsulta("SELECT * FROM unidad");
        ArrayList<Unidad> resultado = new ArrayList();
        Unidad unidad;
        ControlLeccion ctrlLeccion = new ControlLeccion();
        try{
            while(rs.next()){
                unidad = new Unidad(rs.getInt("idUnidad"), rs.getString("nombreUnidad"), rs.getInt("limite"));                
                resultado.add(unidad);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            Conexion.getInstancia().liberarConexion();
        }
        for(Unidad u: resultado){
            u.setLecciones(ctrlLeccion.obtenerTodas(u.getIdUnidad()));
        }
        return resultado;
    }
}

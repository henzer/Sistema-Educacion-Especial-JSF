package controladores;

import conexion.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelos.Leccion;

public class ControlLeccion {
    public ArrayList<Leccion> obtenerTodas(int idUnidad){
        ResultSet rs= Conexion.getInstancia().hacerConsulta("select idLeccion, leccion.idUnidad, nombreUnidad, nombreLeccion, archivo from leccion inner join unidad on leccion.idUnidad = unidad.idUnidad where leccion.idUnidad = " + idUnidad);
        ArrayList<Leccion> resultado = new ArrayList();
        Leccion leccion;
        try{
            while(rs.next()){
                leccion = new Leccion (rs.getInt("idLeccion"), rs.getInt("idUnidad"), rs.getString("nombreUnidad"), rs.getString("nombreLeccion"), rs.getString("archivo"));
                resultado.add(leccion);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return resultado;
    }
}

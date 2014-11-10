package controladores;

import conexion.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelos.Ejercicio;
import modelos.Leccion;
import modelos.Unidad;

/**
 *
 * @author Cesar Luis
 */
public class ControlEjercicio {
    
    public ArrayList<Ejercicio> obtenerTodas(int idLeccion){
        ResultSet rs= Conexion.getInstancia().hacerConsulta("select * FROM ejercicio where idLeccion=" + idLeccion);
        ArrayList<Ejercicio> resultado = new ArrayList();
        Ejercicio ejercicio;
        try{
            while(rs.next()){
                ejercicio = new Ejercicio (rs.getInt("idEjercicio"), rs.getInt("idLeccion"), rs.getString("nombreEjercicio"), rs.getString("estado"));
                resultado.add(ejercicio);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            Conexion.getInstancia().liberarConexion();
        }
        return resultado;
    }
    
}

package controladores;
import conexion.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelos.Nota;

/**
 *
 * @author Henzer
 */
public class ControlNota {
    public ArrayList<Nota> obtenerNotas(int idUnidad, String estado, int idAlumno){
        ResultSet rs= Conexion.getInstancia().hacerConsulta("select sum(nota.nota) as 'total', count(nota.nota) as 'realizadas', ejercicio.idLeccion from nota inner join ejercicio on nota.idEjercicio = ejercicio.idEjercicio inner join leccion on ejercicio.idLeccion = leccion.idLeccion where leccion.idUnidad = "+idUnidad+" and nota.estado = '"+estado+"' and nota.idAlumno = "+idAlumno+" group by ejercicio.idLeccion");
        ArrayList<Nota> resultado = new ArrayList();
        Nota nota;
        try{
            while(rs.next()){
                nota = new Nota (rs.getDouble("total"), rs.getInt("realizadas"), rs.getInt("idLeccion"));
                resultado.add(nota);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            Conexion.getInstancia().liberarConexion();
        }
        return resultado;
    }
}

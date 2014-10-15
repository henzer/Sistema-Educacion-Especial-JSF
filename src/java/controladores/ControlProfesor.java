package controladores;

import conexion.Conexion;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelos.Nota;
import modelos.Profesor;
import modelos.Usuario;


public class ControlProfesor implements Serializable{

	public boolean editarAlumno(int idAlumno, String nombreAlumno, String apellidoAlumno,
			String telefono1, String telefono2, String descripcion,
			String foto, String sexo, ArrayList<Nota> notas){
		return true;
		
	}
	public boolean eliminarAlumno(int idAlumno){
		return true;
	}
	public ArrayList<Profesor> obtenerTodos(){
		ArrayList<Profesor> lista = new ArrayList<Profesor>();
		ResultSet resultado = Conexion.getInstancia().hacerConsulta("SELECT * FROM profesor inner join usuario on profesor.idUsuario = usuario.idUsuario");
		try {
			while(resultado.next()){
				lista.add(new Profesor(resultado.getInt("idProfesor"), resultado.getString("nombreProfesor"), resultado.getString("apellidoProfesor"), resultado.getString("telefono1"), resultado.getString("telefono2"), resultado.getString("descripcion"), resultado.getString("foto"), resultado.getString("sexo"), new Usuario(resultado.getInt("idUsuario"), resultado.getString("nombreUsuario"))));
				System.out.println("Agregado");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
        public Profesor getProfesor(int idUsuario){
            ResultSet resultado = Conexion.getInstancia().hacerConsulta("SELECT * FROM profesor inner join usuario on profesor.idUsuario = usuario.idUsuario where usuario.idUsuario = " + idUsuario);
            try {
                while(resultado.next()){
                    return new Profesor(resultado.getInt("idProfesor"), resultado.getString("nombreProfesor"), resultado.getString("apellidoProfesor"), resultado.getString("telefono1"), resultado.getString("telefono2"), resultado.getString("descripcion"), resultado.getString("foto"), resultado.getString("sexo"), new Usuario(resultado.getInt("idUsuario"), resultado.getString("nombreUsuario")));
                }
            } catch (SQLException e) {
                    e.printStackTrace();
            }
            return null;
        }
        
}

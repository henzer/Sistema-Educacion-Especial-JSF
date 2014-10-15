package controladores;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelos.Alumno;
import modelos.Profesor;
import modelos.Usuario;
import conexion.Conexion;

public class ControlAlumno {
	public ArrayList<Alumno> obtenerTodos(){
		ResultSet rs= Conexion.getInstancia().hacerConsulta("SELECT * FROM alumno");
		ArrayList<Alumno> resultado=new ArrayList<Alumno>();
		Alumno alumno;
		try {
			while (rs.next()) {
			    alumno = new Alumno();
			    /*Retrieve one employee details
			    and store it in employee object*/
			    alumno.setIdAlumno(rs.getInt("idAlumno"));
			    alumno.setNombreAlumno(rs.getString("nombreAlumno"));
			    alumno.setApellidoAlumno(rs.getString("apellidoAlumno"));
			    alumno.setTelefono1(rs.getString("telefono1"));
			    alumno.setTelefono2(rs.getString("telefono2"));
			    alumno.setDescripcion(rs.getString("descripcion"));
			    alumno.setFoto(rs.getString("foto"));
			    alumno.setSexo(rs.getString("sexo"));
			    alumno.setUsuario(new Usuario(rs.getInt("idUsuario"),""));
			    alumno.setProfesor(new Profesor(rs.getInt("idProfesor"),""));
			    //add each employee to the list
			    resultado.add(alumno);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		System.out.println(resultado);
		return resultado;
	}
        public Alumno getAlumno(int idUsuario){
		ResultSet rs= Conexion.getInstancia().hacerConsulta("SELECT * FROM alumno inner join usuario on alumno.idUsuario = usuario.idUsuario where usuario.idUsuario = " + idUsuario);
		Alumno alumno;
		try {
			while (rs.next()) {
			    alumno = new Alumno();
			    /*Retrieve one employee details
			    and store it in employee object*/
			    alumno.setIdAlumno(rs.getInt("idAlumno"));
			    alumno.setNombreAlumno(rs.getString("nombreAlumno"));
			    alumno.setApellidoAlumno(rs.getString("apellidoAlumno"));
			    alumno.setTelefono1(rs.getString("telefono1"));
			    alumno.setTelefono2(rs.getString("telefono2"));
			    alumno.setDescripcion(rs.getString("descripcion"));
			    alumno.setFoto(rs.getString("foto"));
			    alumno.setSexo(rs.getString("sexo"));
			    alumno.setUsuario(new Usuario(rs.getInt("idUsuario"),""));
			    alumno.setProfesor(new Profesor(rs.getInt("idProfesor"),""));
			    //add each employee to the list
			    return alumno;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
                return null;
	}
}

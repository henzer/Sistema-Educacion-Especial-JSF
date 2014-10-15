package controladores;

import conexion.Conexion;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelos.Usuario;

public class ControlUsuario implements Serializable{
	public Usuario autenticarUsuario(String nombreUsuario, String password){
		Usuario aux=new Usuario(0,nombreUsuario,password,"","");
		if(aux.autenticarUsuario()){
			return aux;
		}
		return null;
	}
	public ArrayList<Usuario> obtenerTodosTipo(int tipo){
		ResultSet rs= Conexion.getInstancia().hacerConsulta("select * from usuario where usuario.tipo="+tipo+" and not exists (Select 1 from profesor where usuario.idUsuario = profesor.idUsuario)");
		ArrayList<Usuario> resultado=new ArrayList<Usuario>();
		Usuario usuario;
		try {
			while (rs.next()) {
				System.out.println(rs.getFetchSize());
			    usuario = new Usuario();
			    /*Retrieve one employee details
			    and store it in employee object*/
			    usuario.setIdUsuario(rs.getInt("idUsuario"));
			    usuario.setNombreUsuario(rs.getString("nombreUsuario"));
			    usuario.setPassword("");
			    usuario.setTipo(rs.getString("tipo"));
			    usuario.setEstado(rs.getString("estado"));
			    //add each employee to the list
			    resultado.add(usuario);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error");
		}finally{
                    Conexion.getInstancia().liberarConexion();
                }
		System.out.println(resultado);
		return resultado;
	}
	public ArrayList<Usuario> obtenerTodos(){
		ResultSet rs= Conexion.getInstancia().hacerConsulta("SELECT * FROM usuario");
		ArrayList<Usuario> resultado=new ArrayList<Usuario>();
		Usuario usuario;
		try {
			while (rs.next()) {
				System.out.println(rs.getFetchSize());
			    usuario = new Usuario();
			    
			    usuario.setIdUsuario(rs.getInt("idUsuario"));
			    usuario.setNombreUsuario(rs.getString("nombreUsuario"));
			    usuario.setPassword(rs.getString("password"));
			    usuario.setTipo(rs.getString("tipo"));
			    usuario.setEstado(rs.getString("estado"));
			    //add each employee to the list
			    resultado.add(usuario);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error");
		}finally{
                    Conexion.getInstancia().liberarConexion();
                }
		System.out.println(resultado);
		return resultado;
	}
}

package controladores;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexion.Conexion;
import modelos.Alumno;
import modelos.Usuario;

public class ControlUsuario {
    private String nombreUsuario;
    private String password;

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public boolean autenticar(){
        return (autenticarUsuario(nombreUsuario, password)!=null);
    }
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
			    /*Retrieve one employee details
			    and store it in employee object*/
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
		}
		System.out.println(resultado);
		return resultado;
	}
}

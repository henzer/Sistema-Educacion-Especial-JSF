package modelos;

import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.Conexion;

public class Usuario {
	int idUsuario;
	String nombreUsuario;
	String password;
	String tipo;
	String estado;	
	
	public Usuario() {
		// TODO Auto-generated constructor stub	
	}
	
	public Usuario(int idUsuario, String nombreUsuario){
		this.idUsuario =idUsuario;
		this.nombreUsuario = nombreUsuario;
	}
	
	public Usuario(int idUsuario, String nombreUsuario, String password,
			String tipo, String estado) {
		super();
		this.idUsuario = idUsuario;
		this.nombreUsuario = nombreUsuario;
		this.password = password;
		this.tipo = tipo;
		this.estado = estado;
	}

	/// M�todos sets y gets para los atributos
	
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
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
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public boolean autenticarUsuario(){
		System.out.println("Llegue a la conexion");
		if(this.nombreUsuario!=null && this.password!=null && this.nombreUsuario!="" && this.password!=""){
			ResultSet lista = null;
			try {
				System.out.println("SELECT * FROM usuario WHERE usuario.nombreUsuario = '"+nombreUsuario+"' AND usuario.password = '" + password + "'");
				lista = Conexion.getInstancia().hacerConsulta("SELECT * FROM usuario WHERE usuario.nombreUsuario = '"+nombreUsuario+"' AND usuario.password = '" + password + "'");
				while(lista.next()){
					this.idUsuario = lista.getInt("idUsuario");
					this.nombreUsuario = lista.getString("nombreUsuario");
					this.tipo = lista.getString("tipo");
					this.estado = lista.getString("estado");
					return true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	//funciones de agregar eliminar y modificar
	//agregar que devuelve boolean
	public boolean agregar(){
		return Conexion.getInstancia().ejecutarSentencia("INSERT INTO usuario(nombreUsuario, password, tipo, estado) VALUES ('"+nombreUsuario+"','"+password+"','"+tipo+"','"+estado+"')");
	}
	//agregar que devuelve integer
	public int agregarConRetorno(){
		return Conexion.getInstancia().ejecutarSentenciaConRetorno("INSERT INTO usuario(nombreUsuario, password, tipo, estado) VALUES ('"+nombreUsuario+"','"+password+"','"+tipo+"','"+estado+"')");
	}
	public boolean eliminar(){
		return Conexion.getInstancia().ejecutarSentencia("DELETE FROM usuario WHERE idUsuario =" + this.getIdUsuario());
	}
	
	public boolean modificar(){
		return Conexion.getInstancia().ejecutarSentencia("UPDATE usuario SET nombreUsuario='"+this.getNombreUsuario()+"',password='"+this.getPassword()+"',"
				+ "tipo='"+this.getTipo()+"',estado='"+this.getEstado()+"' WHERE idUsuario =" + this.getIdUsuario());
	}
}

package modelos;

import java.util.ArrayList;

import conexion.Conexion;
import java.io.Serializable;

public class Profesor implements Serializable{
	
	int idProfesor;
	String nombreProfesor;
	String apellidoProfesor;
	String telefono1;
	String telefono2;
	String descripcion;
	String foto;
	String sexo;
	private Usuario usuario;	
	
	
	public Profesor(int idProfesor, String nombreProfesor,
			String apellidoProfesor, String telefono1, String telefono2,
			String descripcion, String foto, String sexo,
			Usuario usuario) {
		super();
		this.idProfesor = idProfesor;
		this.nombreProfesor = nombreProfesor;
		this.apellidoProfesor = apellidoProfesor;
		this.telefono1 = telefono1;
		this.telefono2 = telefono2;
		this.descripcion = descripcion;
		this.foto = foto;
		this.sexo = sexo;
		this.usuario = usuario;
	}
	public Profesor(int idProfesor,String nombreProfesor){
		this.idProfesor=idProfesor;
		this.nombreProfesor=nombreProfesor;
	}
	public Profesor() {
            usuario=new Usuario(0,"blah");
	}
	
	public boolean agregar(){
		return Conexion.getInstancia().ejecutarSentencia("INSERT INTO `profesor`(`nombreProfesor`, `apellidoProfesor`, `telefono1`, `telefono2`, `descripcion`, `foto`, `sexo`, `idUsuario`) VALUES ('"+nombreProfesor+"','"+apellidoProfesor+"','"+telefono1+"','"+telefono2+"','"+descripcion+"','"+foto+"','"+sexo+"',"+usuario.getIdUsuario()+")");
	}
	public int agregarConRetorno(){
		return Conexion.getInstancia().ejecutarSentenciaConRetorno("INSERT INTO `profesor`(`nombreProfesor`, `apellidoProfesor`, `telefono1`, `telefono2`, `descripcion`, `foto`, `sexo`, `idUsuario`) VALUES ('"+nombreProfesor+"','"+apellidoProfesor+"','"+telefono1+"','"+telefono2+"','"+descripcion+"','"+foto+"','"+sexo+"',"+usuario.getIdUsuario()+")");
	}
	public boolean eliminar(){
		return Conexion.getInstancia().ejecutarSentencia("DELETE FROM profesor WHERE idProfesor =" + this.getIdProfesor());
	}
	
	public boolean modificar(){
		return Conexion.getInstancia().ejecutarSentencia("UPDATE profesor SET nombreProfesor='"+this.getNombreProfesor()+"',apellidoProfesor='"+this.getApellidoProfesor()+"',"
				+ "telefono1='"+this.getTelefono1()+"',telefono2='"+this.getTelefono2()+"',descripcion='"+this.getDescripcion()+"',"
						+ "foto='"+this.getFoto()+"',sexo='"+this.getSexo()+"',idUsuario="+this.getUsuario().getIdUsuario()+" WHERE idProfesor =" + this.getIdProfesor());
	}

	// M�todos sets y gets para los atributos
	
	public int getIdProfesor() {
		return idProfesor;
	}

	public void setIdProfesor(int idProfesor) {
		this.idProfesor = idProfesor;
	}

	public String getNombreProfesor() {
		return nombreProfesor;
	}

	public void setNombreProfesor(String nombreProfesor) {
		this.nombreProfesor = nombreProfesor;
	}

	public String getApellidoProfesor() {
		return apellidoProfesor;
	}

	public void setApellidoProfesor(String apellidoProfesor) {
		this.apellidoProfesor = apellidoProfesor;
	}

	public String getTelefono1() {
		return telefono1;
	}

	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}

	public String getTelefono2() {
		return telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
}

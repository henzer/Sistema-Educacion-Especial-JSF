package modelos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexion.Conexion;
import java.io.Serializable;

public class Alumno implements Serializable{

	private int idAlumno;
	private String nombreAlumno;
	private String apellidoAlumno;
	private String telefono1;
	private String telefono2;
	private String descripcion;
	private String foto;
	private String sexo;
	private Usuario usuario;
	private Profesor profesor;
	public Alumno(int idAlumno, String nombreAlumno, String apellidoAlumno,
			String telefono1, String telefono2, String descripcion,
			String foto, String sexo, Usuario usuario, Profesor profesor) {
		super();
		this.idAlumno = idAlumno;
		this.nombreAlumno = nombreAlumno;
		this.apellidoAlumno = apellidoAlumno;
		this.telefono1 = telefono1;
		this.telefono2 = telefono2;
		this.descripcion = descripcion;
		this.foto = foto;
		this.sexo = sexo;
		this.usuario=usuario;
		this.profesor=profesor;
	}

	public Alumno() {
		// TODO Auto-generated constructor stub
            usuario=new Usuario(0,"blah");
            profesor=new Profesor(0,"blah");
	}
	
	public boolean agregar(){
		System.out.println("INSERT INTO alumno(idUsuario, idProfesor, nombreAlumno, apellidoAlumno, telefono1, telefono2, descripcion, foto, sexo) VALUES ("+usuario.getIdUsuario()+", "+profesor.getIdProfesor()+", '"+nombreAlumno+"','"+apellidoAlumno+"','"+telefono1+"','"+telefono2+"','"+descripcion+"','"+foto+"','"+sexo+"')");
		return Conexion.getInstancia().ejecutarSentencia("INSERT INTO alumno(idUsuario, idProfesor, nombreAlumno, apellidoAlumno, telefono1, telefono2, descripcion, foto, sexo) VALUES ("+usuario.getIdUsuario()+", "+profesor.getIdProfesor()+", '"+nombreAlumno+"','"+apellidoAlumno+"','"+telefono1+"','"+telefono2+"','"+descripcion+"','"+foto+"','"+sexo+"')");
	}
	
	public int agregarConRetorno(){
		System.out.println("INSERT INTO alumno(idUsuario, idProfesor, nombreAlumno, apellidoAlumno, telefono1, telefono2, descripcion, foto, sexo) VALUES ("+usuario.getIdUsuario()+", "+profesor.getIdProfesor()+", '"+nombreAlumno+"','"+apellidoAlumno+"','"+telefono1+"','"+telefono2+"','"+descripcion+"','"+foto+"','"+sexo+"')");
		return Conexion.getInstancia().ejecutarSentenciaConRetorno("INSERT INTO alumno(idUsuario, idProfesor, nombreAlumno, apellidoAlumno, telefono1, telefono2, descripcion, foto, sexo) VALUES ("+usuario.getIdUsuario()+", "+profesor.getIdProfesor()+", '"+nombreAlumno+"','"+apellidoAlumno+"','"+telefono1+"','"+telefono2+"','"+descripcion+"','"+foto+"','"+sexo+"')");
	}
	
	public boolean eliminar(){
		return Conexion.getInstancia().ejecutarSentencia("DELETE FROM alumno WHERE idAlumno =" + this.getIdAlumno());
	}
	
	public boolean modificar(){
		return Conexion.getInstancia().ejecutarSentencia("UPDATE alumno SET nombreAlumno='"+this.getNombreAlumno()+"',apellidoAlumno='"+this.getApellidoAlumno()+"',"
				+ "telefono1='"+this.getTelefono1()+"',telefono2='"+this.getTelefono2()+"',descripcion='"+this.getDescripcion()+"',"
						+ "foto='"+this.getFoto()+"',sexo='"+this.getSexo()+"',idUsuario="+usuario.getIdUsuario()+",idProfesor="+profesor.getIdProfesor()+" WHERE idAlumno =" + this.getIdAlumno());
	}
	//obtener todos los elementos de una base de datos
	
	// M�todos sets y gets para los atributos
	
	public int getIdAlumno() {
		return idAlumno;
	}


	public void setIdAlumno(int idAlumno) {
		this.idAlumno = idAlumno;
	}


	public String getNombreAlumno() {
		return nombreAlumno;
	}


	public void setNombreAlumno(String nombreAlumno) {
		this.nombreAlumno = nombreAlumno;
	}


	public String getApellidoAlumno() {
		return apellidoAlumno;
	}


	public void setApellidoAlumno(String apellidoAlumno) {
		this.apellidoAlumno = apellidoAlumno;
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

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}
	

}

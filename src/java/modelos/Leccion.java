package modelos;

public class Leccion {

	int idLeccion;
	int idUnidad;
	String nombreLeccion;
	String ejercicios;
	
	
	public Leccion(int idLeccion, int idUnidad, String nombreLeccion,
			String ejercicios) {
		super();
		this.idLeccion = idLeccion;
		this.idUnidad = idUnidad;
		this.nombreLeccion = nombreLeccion;
		this.ejercicios = ejercicios;
	}

	public Leccion() {
		// TODO Auto-generated constructor stub
	}

	// Métodos sets y gets para los atributos
	
	public int getIdLeccion() {
		return idLeccion;
	}

	public void setIdLeccion(int idLeccion) {
		this.idLeccion = idLeccion;
	}

	public int getIdUnidad() {
		return idUnidad;
	}

	public void setIdUnidad(int idUnidad) {
		this.idUnidad = idUnidad;
	}

	public String getNombreLeccion() {
		return nombreLeccion;
	}

	public void setNombreLeccion(String nombreLeccion) {
		this.nombreLeccion = nombreLeccion;
	}

	public String getEjercicios() {
		return ejercicios;
	}

	public void setEjercicios(String ejercicios) {
		this.ejercicios = ejercicios;
	}

}

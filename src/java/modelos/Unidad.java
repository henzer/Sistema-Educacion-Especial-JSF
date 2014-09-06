package modelos;

import java.util.ArrayList;

public class Unidad {
	
	int idUnidad;
	String nombreUnidad;
	ArrayList<Leccion> lecciones;

	
	public Unidad(int idUnidad, String nombreUnidad,
			ArrayList<Leccion> lecciones) {
		super();
		this.idUnidad = idUnidad;
		this.nombreUnidad = nombreUnidad;
		this.lecciones = lecciones;
	}

	public Unidad() {
		// TODO Auto-generated constructor stub
	}
	
	/// Métodos sets y gets para los atributos

	public int getIdUnidad() {
		return idUnidad;
	}

	public void setIdUnidad(int idUnidad) {
		this.idUnidad = idUnidad;
	}

	public String getNombreUnidad() {
		return nombreUnidad;
	}

	public void setNombreUnidad(String nombreUnidad) {
		this.nombreUnidad = nombreUnidad;
	}

	public ArrayList<Leccion> getLecciones() {
		return lecciones;
	}

	public void setLecciones(ArrayList<Leccion> lecciones) {
		this.lecciones = lecciones;
	}
	
}

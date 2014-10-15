package modelos;

public class Nota {

	int idAlumno;
	int idLeccion;
	int idUnidad;
	String fecha;
	double nota;

	
	public Nota(int idAlumno, int idLeccion, int idUnidad, String fecha,
			double nota) {
		super();
		this.idAlumno = idAlumno;
		this.idLeccion = idLeccion;
		this.idUnidad = idUnidad;
		this.fecha = fecha;
		this.nota = nota;
	}

	public Nota() {
		// TODO Auto-generated constructor stub
	}

	// M�todos sets y gets para los atributos
	
	public int getIdAlumno() {
		return idAlumno;
	}

	public void setIdAlumno(int idAlumno) {
		this.idAlumno = idAlumno;
	}

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

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}
	
	

}

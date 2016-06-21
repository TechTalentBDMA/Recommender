package upc.bdam.recommender.graphupdater.schema;

/**
 * Clase que define el esquema almacenado en Big2 para contenidos de video
 * @author Grupo 9: 
 *           - Antol�n Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David P�rez Rodr�guez
 *
 */
public class Big2SchemaVideoBean {
	
	//declaraci�n de variables
	private String titulo;
	private String[] actores;
	private String director;
	private String productor;
	private String guionista;
	private int clasificacion;
	private int year;
	
	//declaraci�n de m�todos setter
	private String[] generos;

	public String getTitulo() {
		return titulo;
	}

	public String[] getActores() {
		return actores;
	}

	public String getDirector() {
		return director;
	}

	public String getProductor() {
		return productor;
	}

	public String getGuionista() {
		return guionista;
	}

	public int getClasificacion() {
		return clasificacion;
	}

	public int getYear() {
		return year;
	}

	public String[] getGeneros() {
		return generos;
	}

	//declaraci�n de m�todos setter
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setActores(String[] actores) {
		this.actores = actores;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public void setProductor(String productor) {
		this.productor = productor;
	}

	public void setGuionista(String guionista) {
		this.guionista = guionista;
	}

	public void setClasificacion(int clasificacion) {
		this.clasificacion = clasificacion;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setGeneros(String[] generos) {
		this.generos = generos;
	}

}

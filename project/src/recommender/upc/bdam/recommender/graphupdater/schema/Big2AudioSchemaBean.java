package upc.bdam.recommender.graphupdater.schema;

/**
 * Clase que define el esquema almacenado en Big2 para contenidos de audio
 * @author Grupo 9: 
 *           - Antol�n Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David P�rez Rodr�guez
 *
 */
public class Big2AudioSchemaBean extends Big2SGraphGuardSchema{
	
	//declaraci�n de variables
	private String titulo;
	private String album;
	private int clasificacion;
	private String interprete;
	private String colaboradores;
	private int year;
	private String generos;
	private String compositores;

	//declaraci�n de m�todos getter
	public String getTitulo() {
		return titulo;
	}

	public String getAlbum() {
		return album;
	}

	public int getClasificacion() {
		return clasificacion;
	}

	public String getInterprete() {
		return interprete;
	}

	public String getColaboradores() {
		return colaboradores;
	}

	public int getYear() {
		return year;
	}

	public String getGeneros() {
		return generos;
	}

	public String getCompositores() {
		return compositores;
	}

	//declaraci�n de m�todos setter
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public void setClasificacion(int clasificacion) {
		this.clasificacion = clasificacion;
	}

	public void setInterprete(String interprete) {
		this.interprete = interprete;
	}

	public void setColaboradores(String colaboradores) {
		this.colaboradores = colaboradores;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setGeneros(String generos) {
		this.generos = generos;
	}

	public void setCompositores(String compositores) {
		this.compositores = compositores;
	}

}

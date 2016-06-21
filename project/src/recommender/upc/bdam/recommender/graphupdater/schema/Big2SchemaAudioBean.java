package upc.bdam.recommender.graphupdater.schema;

/**
 * Clase que define el esquema almacenado en Big2 para contenidos de audio
 * @author Grupo 9: 
 *           - Antolín Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David Pérez Rodríguez
 *
 */
public class Big2SchemaAudioBean {
	
	//declaración de variables
	private String titulo;
	private String album;
	private byte clasificacion;
	private String interprete;
	private String[] colanboradores;
	private int year;
	private String[] generos;
	private String compositores;

	//declaración de métodos getter
	public String getTitulo() {
		return titulo;
	}

	public String getAlbum() {
		return album;
	}

	public byte getClasificacion() {
		return clasificacion;
	}

	public String getInterprete() {
		return interprete;
	}

	public String[] getColanboradores() {
		return colanboradores;
	}

	public int getYear() {
		return year;
	}

	public String[] getGeneros() {
		return generos;
	}

	public String getCompositores() {
		return compositores;
	}

	//declaración de métodos setter
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public void setClasificacion(byte clasificacion) {
		this.clasificacion = clasificacion;
	}

	public void setInterprete(String interprete) {
		this.interprete = interprete;
	}

	public void setColanboradores(String[] colanboradores) {
		this.colanboradores = colanboradores;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setGeneros(String[] generos) {
		this.generos = generos;
	}

	public void setCompositores(String compositores) {
		this.compositores = compositores;
	}

}

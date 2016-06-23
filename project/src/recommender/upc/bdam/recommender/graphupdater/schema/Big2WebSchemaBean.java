package upc.bdam.recommender.graphupdater.schema;


/**
 * Clase que define el esquema almacenado en Big2 para contenidos web
 * @author Grupo 9: 
 *           - Antol�n Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David P�rez Rodr�guez
 *
 */
public class Big2WebSchemaBean  extends Big2SGraphGuardSchema{

	//declaraci�n de variables
	private String nombres;
	private String temas;
	private String claves;
	private String generos;
	private String uri;
	private String titulo;
	private String descripcion;

	//declaraci�n de m�todos getter
	public String getNombres() {
		return nombres;
	}

	public String getTemas() {
		return temas;
	}

	public String getClaves() {
		return claves;
	}

	public String getGeneros() {
		return generos;
	}

	public String getUri() {
		return uri;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	//declaraci�n de m�todos setter
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public void setTemas(String temas) {
		this.temas = temas;
	}

	public void setClaves(String claves) {
		this.claves = claves;
	}

	public void setGeneros(String generos) {
		this.generos = generos;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}

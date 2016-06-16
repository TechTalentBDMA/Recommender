package upc.bdam.recommender.graphupdater.schema;

public class GraphSchemaWebBean {

	private String[] nombres;
	private String[] temas;
	private String[] claves;
	private String[] generos;
	private String uri;
	private String titulo;
	private String descripcion;

	public String[] getNombres() {
		return nombres;
	}

	public String[] getTemas() {
		return temas;
	}

	public String[] getClaves() {
		return claves;
	}

	public String[] getGeneros() {
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

	public void setNombres(String[] nombres) {
		this.nombres = nombres;
	}

	public void setTemas(String[] temas) {
		this.temas = temas;
	}

	public void setClaves(String[] claves) {
		this.claves = claves;
	}

	public void setGeneros(String[] generos) {
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

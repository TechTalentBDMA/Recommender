package upc.bdam.recommender.graphupdater.schema;

public class GraphSchemaAudioBean {

	private String titulo;
	private String album;
	private byte clasificacion;
	private String interprete;
	private String[] colanboradores;
	private int year;
	private String[] generos;
	private String compositores;

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

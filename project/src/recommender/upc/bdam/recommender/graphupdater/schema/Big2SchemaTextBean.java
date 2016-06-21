package upc.bdam.recommender.graphupdater.schema;

/**
 * Clase que define el esquema almacenado en Big2 para contenidos de texto
 * @author Grupo 9: 
 *           - Antol�n Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David P�rez Rodr�guez
 *
 */
public class Big2SchemaTextBean {

	//declaraci�n de variables
	private String titulo;
	private String autor;
	private String idioma;
	private long isbn;
	private String creator;
	private int year;
	private String subject;
	private byte type;

	//declaraci�n de m�todos getter
	public String getTitulo() {
		return titulo;
	}

	public String getAutor() {
		return autor;
	}

	public String getIdioma() {
		return idioma;
	}

	public long getIsbn() {
		return isbn;
	}

	public String getCreator() {
		return creator;
	}

	public int getYear() {
		return year;
	}

	public String getSubject() {
		return subject;
	}

	public byte getType() {
		return type;
	}

	//declaraci�n de m�todos setter
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setType(byte type) {
		this.type = type;
	}

}

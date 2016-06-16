package upc.bdam.recommender.graphupdater.schema;

public class GraphSchemaTextBean {

	private String titulo;
	private String autor;
	private String idioma;
	private long isbn;
	private String creator;
	private int year;
	private String subject;
	private byte type;

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

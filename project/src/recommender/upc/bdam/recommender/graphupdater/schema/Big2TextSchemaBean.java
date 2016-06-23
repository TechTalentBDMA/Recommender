package upc.bdam.recommender.graphupdater.schema;

/**
 * Clase que define el esquema almacenado en Big2 para contenidos de texto
 * @author Grupo 9: 
 *           - Antolín Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David Pérez Rodríguez
 *
 */
public class Big2TextSchemaBean  extends Big2SGraphGuardSchema{

	//declaración de variables
	private String nombres;
	private String temas;
	private String claves;
	private String generos;
	private String palabras;
	private String titulo;
	private String autor;
	private String idioma;
	private long isbn;
	private String creator;
	private int year;
	private String subject;
	private String type;

	//declaración de métodos getter
	
	public String getTitulo() {
		return titulo;
	}

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

	public String getPalabras() {
		return palabras;
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

	public String getType() {
		return type;
	}

	//declaración de métodos setter
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

	public void setPalabras(String palabras) {
		this.palabras = palabras;
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

	public void setType(String type) {
		this.type = type;
	}

}

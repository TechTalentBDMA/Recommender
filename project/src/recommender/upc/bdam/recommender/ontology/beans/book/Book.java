package upc.bdam.recommender.ontology.beans.book;

import upc.bdam.recommender.ontology.beans.Person;

public class Book {
	
	private String id;
	private String name;
	private String[] genre;
	private String released;
	private Person autor;
	
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String[] getGenre() {
		return genre;
	}
	public String getReleased() {
		return released;
	}
	public Person getAutor() {
		return autor;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setGenre(String[] genre) {
		this.genre = genre;
	}
	public void setReleased(String released) {
		this.released = released;
	}
	public void setAutor(Person autor) {
		this.autor = autor;
	}
}

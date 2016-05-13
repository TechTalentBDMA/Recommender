package upc.bdam.recommender.ontology.beans.film;

import upc.bdam.recommender.ontology.beans.Person;

public class Film {
	
	private String id;
	private String title;
	private String released;
	private String[] genre;
	private Person actor;
	private Person Director;
	
	public String getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getReleased() {
		return released;
	}
	public String[] getGenre() {
		return genre;
	}
	public Person getActor() {
		return actor;
	}
	public Person getDirector() {
		return Director;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setReleased(String released) {
		this.released = released;
	}
	public void setGenre(String[] genre) {
		this.genre = genre;
	}
	public void setActor(Person actor) {
		this.actor = actor;
	}
	public void setDirector(Person director) {
		Director = director;
	}
	
	
	
}

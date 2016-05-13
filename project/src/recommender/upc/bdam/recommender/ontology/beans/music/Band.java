package upc.bdam.recommender.ontology.beans.music;

import upc.bdam.recommender.ontology.beans.Person;

public class Band {
	
	private String id;
	private String name;
	private String[] genre;
	private String released;
	private Person[] components;
	
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
	public Person[] getComponents() {
		return components;
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
	public void setComponents(Person[] components) {
		this.components = components;
	}
}

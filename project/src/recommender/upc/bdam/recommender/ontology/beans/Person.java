package upc.bdam.recommender.ontology.beans;

public class Person {

	private String id;
	private String name;
	private String born;
	private String ocupation;
	
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getBorn() {
		return born;
	}
	public String getOcupation() {
		return ocupation;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setBorn(String born) {
		this.born = born;
	}
	public void setOcupation(String ocupation) {
		this.ocupation = ocupation;
	}	
	
}

package upc.bdam.recommender.documentDDBB.dao.json;

/**
 * Clase que encapsula los datos relativos a una persona
 * @author Grupo 9: 
 *           - Antolín Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David Pérez Rodríguez
 *
 */
public class Person {

	public String id = new String();
	public String born = new String();
	public String name = new String();

	public String getId() {
		return id;
	}

	public String getBorn() {
		return born;
	}

	public String getName() {
		return name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setBorn(String born) {
		this.born = born;
	}

	public void setName(String name) {
		this.name = name;
	}

}

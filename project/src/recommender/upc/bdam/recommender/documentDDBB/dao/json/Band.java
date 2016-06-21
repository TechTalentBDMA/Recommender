package upc.bdam.recommender.documentDDBB.dao.json;

import upc.bdam.recommender.ontology.json.IBinding;

/**
 * Clase en la que se encapsulan los valores obtenidos de wikidata para grupos musicales
 * @author Grupo 9: 
 *           - Antol�n Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David P�rez Rodr�guez
 *
 */
public class Band implements IBinding {

	public String id=new String();
	public String released=new String();
	public String name=new String();
	public String getId() {
		return id;
	}
	public String getReleased() {
		return released;
	}
	public String getName() {
		return name;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setReleased(String released) {
		this.released = released;
	}
	public void setName(String name) {
		this.name = name;
	}


}
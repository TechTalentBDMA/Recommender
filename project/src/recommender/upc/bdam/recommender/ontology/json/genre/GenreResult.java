package upc.bdam.recommender.ontology.json.genre;

import upc.bdam.recommender.ontology.json.IBinding;


/**
 * Clase que encapsula el resultado de la respuesta de acuerdo a la estructura json 
 * que devuelve wikidata
 * @author Grupo 9: 
 *           - Antolín Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David Pérez Rodríguez
 *
 */
public class GenreResult implements IBinding{
	
	private Genre[] bindings;

	public Genre[] getBindings() {
		return bindings;
	}

	public void setBindings(Genre[] bindings) {
		this.bindings = bindings;
	}
}

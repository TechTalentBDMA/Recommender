package upc.bdam.recommender.ontology.json.band;

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
public class BandResult implements IBinding{
	
	private Band[] bindings;

	public Band[] getBindings() {
		return bindings;
	}

	public void setBindings(Band[] bindings) {
		this.bindings = bindings;
	}
}

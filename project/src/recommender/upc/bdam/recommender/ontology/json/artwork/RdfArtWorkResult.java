package upc.bdam.recommender.ontology.json.artwork;

import upc.bdam.recommender.ontology.json.Head;

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
public class RdfArtWorkResult {
	
	public Head head;
	public ArtWorkResult results;	
	
	public Head getHead() {
		return head;
	}
	public ArtWorkResult getResult() {
		return results;
	}
	public void setHead(Head head) {
		this.head = head;
	}
	public void setResult(ArtWorkResult result) {
		this.results = result;
	}
}

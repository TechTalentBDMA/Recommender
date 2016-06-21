package upc.bdam.recommender.ontology.json.genre;

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
public class RdfGenreResult {

	public Head head;

	public GenreResult results;

	public GenreResult getResult() {
		return results;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public void setResults(GenreResult results) {
		this.results = results;
	}

	public Head getHead() {
		return head;
	}

}

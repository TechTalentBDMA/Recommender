package upc.bdam.recommender.ontology.json.author;

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
public class RdfAuthorResult {

	public Head head;

	public AuthorResult results;

	public AuthorResult getResult() {
		return results;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public void setResults(AuthorResult results) {
		this.results = results;
	}

	public Head getHead() {
		return head;
	}

}

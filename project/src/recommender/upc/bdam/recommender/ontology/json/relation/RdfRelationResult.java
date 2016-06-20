package upc.bdam.recommender.ontology.json.relation;

import upc.bdam.recommender.ontology.json.Head;

/**
 * Clase que respeta el esquema de respuesta del servicio JSON de wikidata. Recibe la información y la deserializa 
 * a los beans utilizados por el recomendador
 * 
 * @author Grupo 9: 
 *           - Antolín Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David Pérez Rodríguez
 *
 */
public class RdfRelationResult {
	
	//declaración de los atributos de respueesta del servicio de wikidata
	public Head head;
	public RelationResult results;	
	
	//declaración de métodos getter
	public Head getHead() {
		return head;
	}
	public RelationResult getResult() {
		return results;
	}
	
	//declaración de métodos setter
	public void setHead(Head head) {
		this.head = head;
	}
	public void setResult(RelationResult result) {
		this.results = result;
	}
}

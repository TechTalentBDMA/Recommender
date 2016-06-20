package upc.bdam.recommender.ontology.json.relation;

import upc.bdam.recommender.ontology.json.Head;

/**
 * Clase que respeta el esquema de respuesta del servicio JSON de wikidata. Recibe la informaci�n y la deserializa 
 * a los beans utilizados por el recomendador
 * 
 * @author Grupo 9: 
 *           - Antol�n Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David P�rez Rodr�guez
 *
 */
public class RdfRelationResult {
	
	//declaraci�n de los atributos de respueesta del servicio de wikidata
	public Head head;
	public RelationResult results;	
	
	//declaraci�n de m�todos getter
	public Head getHead() {
		return head;
	}
	public RelationResult getResult() {
		return results;
	}
	
	//declaraci�n de m�todos setter
	public void setHead(Head head) {
		this.head = head;
	}
	public void setResult(RelationResult result) {
		this.results = result;
	}
}

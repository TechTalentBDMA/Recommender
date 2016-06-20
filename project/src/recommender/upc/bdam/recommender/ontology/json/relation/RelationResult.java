package upc.bdam.recommender.ontology.json.relation;

/**
 * Clase en la que se encapsula el resultado dentro del JSON devuelto por wikidata
 * @author Grupo 9: 
 *           - Antol�n Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David P�rez Rodr�guez
 *
 */
public class RelationResult{
	
	//array de resultados
	private LeftRightRelation[] bindings;

	//declaraci�n de m�todos de acceso
	public LeftRightRelation[] getBindings() {
		return bindings;
	}

	public void setBindings(LeftRightRelation[] bindings) {
		this.bindings = bindings;
	}
}

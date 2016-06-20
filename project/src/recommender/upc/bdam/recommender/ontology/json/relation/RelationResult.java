package upc.bdam.recommender.ontology.json.relation;

/**
 * Clase en la que se encapsula el resultado dentro del JSON devuelto por wikidata
 * @author Grupo 9: 
 *           - Antolín Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David Pérez Rodríguez
 *
 */
public class RelationResult{
	
	//array de resultados
	private LeftRightRelation[] bindings;

	//declaración de métodos de acceso
	public LeftRightRelation[] getBindings() {
		return bindings;
	}

	public void setBindings(LeftRightRelation[] bindings) {
		this.bindings = bindings;
	}
}

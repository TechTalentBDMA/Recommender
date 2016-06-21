package upc.bdam.recommender.ontology.json.artwork;

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
public class ArtWorkResult {
	
	private ArtWork[] bindings;

	public ArtWork[] getBindings() {
		return bindings;
	}

	public void setBindings(ArtWork[] bindings) {
		this.bindings = bindings;
	}
}

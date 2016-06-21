package upc.bdam.recommender.ontology.json.artwork;

/**
 * Clase que encapsula el resultado de la respuesta de acuerdo a la estructura json 
 * que devuelve wikidata
 * @author Grupo 9: 
 *           - Antol�n Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David P�rez Rodr�guez
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

package upc.bdam.recommender.ontology.json.author;

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
import upc.bdam.recommender.ontology.json.IBinding;

public class AuthorResult implements IBinding{
	
	private Author[] bindings;

	public Author[] getBindings() {
		return bindings;
	}

	public void setBindings(Author[] bindings) {
		this.bindings = bindings;
	}
}

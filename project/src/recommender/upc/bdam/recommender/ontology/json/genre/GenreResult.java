package upc.bdam.recommender.ontology.json.genre;

import upc.bdam.recommender.ontology.json.IBinding;

public class GenreResult implements IBinding{
	
	private Genre[] bindings;

	public Genre[] getBindings() {
		return bindings;
	}

	public void setBindings(Genre[] bindings) {
		this.bindings = bindings;
	}
}

package upc.bdam.recommender.ontology.json.author;

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

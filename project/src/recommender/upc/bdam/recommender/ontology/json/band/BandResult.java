package upc.bdam.recommender.ontology.json.band;

import upc.bdam.recommender.ontology.json.IBinding;

public class BandResult implements IBinding{
	
	private Band[] bindings;

	public Band[] getBindings() {
		return bindings;
	}

	public void setBindings(Band[] bindings) {
		this.bindings = bindings;
	}
}

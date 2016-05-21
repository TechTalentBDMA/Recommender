package upc.bdam.recommender.graph.dao;

import upc.bdam.recommender.ontology.json.IBinding;

public abstract class GraphDDBBObserver {

	public abstract void update(IBinding[] values);
}
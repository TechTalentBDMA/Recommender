package upc.bdam.recommender.graph.dao;

import upc.bdam.recommender.ontology.json.IBinding;

public abstract class GraphDDBBObserver {

	public abstract void insert(IBinding[] values);

	public abstract void update(IBinding[] values);

	public abstract void delete(IBinding[] values);

}
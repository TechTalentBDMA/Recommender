package upc.bdam.recommender.ontology.json.relation;

import upc.bdam.recommender.ontology.json.Head;

public class RdfRelationResult {
	
	public Head head;
	public RelationResult results;	
	
	public Head getHead() {
		return head;
	}
	public RelationResult getResult() {
		return results;
	}
	public void setHead(Head head) {
		this.head = head;
	}
	public void setResult(RelationResult result) {
		this.results = result;
	}
}

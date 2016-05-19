package upc.bdam.recommender.ontology.json.artwork;

import upc.bdam.recommender.ontology.json.Head;

public class RdfArtWorkResult {
	
	public Head head;
	public ArtWorkResult results;	
	
	public Head getHead() {
		return head;
	}
	public ArtWorkResult getResult() {
		return results;
	}
	public void setHead(Head head) {
		this.head = head;
	}
	public void setResult(ArtWorkResult result) {
		this.results = result;
	}
}

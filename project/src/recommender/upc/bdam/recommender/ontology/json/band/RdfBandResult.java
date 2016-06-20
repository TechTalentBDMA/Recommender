package upc.bdam.recommender.ontology.json.band;

import upc.bdam.recommender.ontology.json.Head;

public class RdfBandResult {

	public Head head;

	public BandResult results;

	public BandResult getResult() {
		return results;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public void setResults(BandResult results) {
		this.results = results;
	}

	public Head getHead() {
		return head;
	}

}

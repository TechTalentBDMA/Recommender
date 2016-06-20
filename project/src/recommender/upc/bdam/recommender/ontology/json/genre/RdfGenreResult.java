package upc.bdam.recommender.ontology.json.genre;

import upc.bdam.recommender.ontology.json.Head;

public class RdfGenreResult {

	public Head head;

	public GenreResult results;

	public GenreResult getResult() {
		return results;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public void setResults(GenreResult results) {
		this.results = results;
	}

	public Head getHead() {
		return head;
	}

}

package upc.bdam.recommender.ontology.json.author;

import upc.bdam.recommender.ontology.json.Head;

public class RdfAuthorResult {

	public Head head;

	public AuthorResult results;

	public AuthorResult getResult() {
		return results;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public void setResults(AuthorResult results) {
		this.results = results;
	}

	public Head getHead() {
		return head;
	}

}

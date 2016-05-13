package upc.bdam.recommender.neo4j;

import upc.bdam.recommender.neo4j.books.relation.Result;

public class RdfResult {
	
	public Head head;
	
	public upc.bdam.recommender.neo4j.books.relation.Result results;
	public upc.bdam.recommender.neo4j.books.book.node.Result bookResult;
	public upc.bdam.recommender.neo4j.books.author.node.Result authorResult;

	
	
	
	public Head getHead() {
		return head;
	}
	public Result getResult() {
		return results;
	}
	public void setHead(Head head) {
		this.head = head;
	}
	public void setResult(Result result) {
		this.results = result;
	}

}

package upc.bdam.recommender.neo4j.books;

import upc.bdam.recommender.connection.http.BookResult;

public class Result {
	
	private BookResult[] bindings;

	public BookResult[] getBindings() {
		return bindings;
	}

	public void setBindings(BookResult[] bindings) {
		this.bindings = bindings;
	}



}

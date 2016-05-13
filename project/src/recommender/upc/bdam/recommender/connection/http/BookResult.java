package upc.bdam.recommender.connection.http;

import upc.bdam.recommender.neo4j.books.NodeBook;
import upc.bdam.recommender.neo4j.books.TittleLabel;

public class BookResult {
	
	public TittleLabel titleLabel;
	public NodeBook NodeBook;
	public TittleLabel getTitleLabel() {
		return titleLabel;
	}
	public NodeBook getNodeBook() {
		return NodeBook;
	}
	public void setTitleLabel(TittleLabel titleLabel) {
		this.titleLabel = titleLabel;
	}
	public void setNodeBook(NodeBook nodeBook) {
		this.NodeBook = nodeBook;
	}
	
	

}

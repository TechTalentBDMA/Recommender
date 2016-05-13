package upc.bdam.recommender.connection.http;

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

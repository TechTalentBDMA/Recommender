package upc.bdam.recommender.neo4j.books.book.node;

public class BookResult{
	
	public TitleLabel authorLabel;
	public NodeBook NodePerson;
	public TitleLabel getAuthorLabel() {
		return authorLabel;
	}
	public NodeBook getNodePerson() {
		return NodePerson;
	}
	public void setAuthorLabel(TitleLabel authorLabel) {
		this.authorLabel = authorLabel;
	}
	public void setNodePerson(NodeBook nodePerson) {
		NodePerson = nodePerson;
	}
	
	
}

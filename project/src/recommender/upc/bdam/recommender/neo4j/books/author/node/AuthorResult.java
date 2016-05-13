package upc.bdam.recommender.neo4j.books.author.node;

public class AuthorResult{
	
	public AuthorLabel authorLabel;
	public NodePerson NodePerson;
	public AuthorLabel getAuthorLabel() {
		return authorLabel;
	}
	public NodePerson getNodePerson() {
		return NodePerson;
	}
	public void setAuthorLabel(AuthorLabel authorLabel) {
		this.authorLabel = authorLabel;
	}
	public void setNodePerson(NodePerson nodePerson) {
		NodePerson = nodePerson;
	}
	
	
}

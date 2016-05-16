package upc.bdam.recommender.neo4j.books.author.node2;

import upc.bdam.recommender.neo4j.GenericResult;
import upc.bdam.recommender.neo4j.IBinding;

public class AuthorResult implements IBinding{
	
	public GenericResult author;
	public GenericResult born;
	public GenericResult authorLabel;
	public GenericResult occupationLabel;
	public GenericResult getAuthor() {
		return author;
	}
	public GenericResult getBorn() {
		return born;
	}
	public GenericResult getAuthorLabel() {
		return authorLabel;
	}
	public GenericResult getOccupationLabel() {
		return occupationLabel;
	}
	
	public void setAuthor(GenericResult author) {
		this.author = author;
	}
	public void setBorn(GenericResult born) {
		this.born = born;
	}
	public void setAuthorLabel(GenericResult authorLabel) {
		this.authorLabel = authorLabel;
	}
	public void setOccupationLabel(GenericResult occupationLabel) {
		this.occupationLabel = occupationLabel;
	}

}

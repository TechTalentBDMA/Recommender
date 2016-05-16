package upc.bdam.recommender.neo4j.films.music.bandMemberRelation;

import upc.bdam.recommender.neo4j.GenericResult;
import upc.bdam.recommender.neo4j.IBinding;

public class RelationResult implements IBinding {
	
	public GenericResult titleLabel;
	public GenericResult book;
	public GenericResult authorLabel;
	public GenericResult author;
	
	public GenericResult getTitleLabel() {
		return titleLabel;
	}
	public GenericResult getBook() {
		return book;
	}
	public GenericResult getAuthorLabel() {
		return authorLabel;
	}
	public GenericResult getAuthor() {
		return author;
	}

	public void setTitleLabel(GenericResult titleLabel) {
		this.titleLabel = titleLabel;
	}
	public void setBook(GenericResult book) {
		this.book = book;
	}
	public void setAuthorLabel(GenericResult authorLabel) {
		this.authorLabel = authorLabel;
	}
	public void setAuthor(GenericResult author) {
		this.author = author;
	}

}

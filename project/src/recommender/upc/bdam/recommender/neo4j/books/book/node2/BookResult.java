package upc.bdam.recommender.neo4j.books.book.node2;

import upc.bdam.recommender.neo4j.GenericResult;
import upc.bdam.recommender.neo4j.IBinding;

public class BookResult implements IBinding{
	
	public GenericResult book;
	public GenericResult titleLabel;
	public GenericResult genreLabel;
	public GenericResult released;
	
	public GenericResult getBook() {
		return book;
	}
	public GenericResult getTitleLabel() {
		return titleLabel;
	}
	public GenericResult getGenreLabel() {
		return genreLabel;
	}
	public GenericResult getReleased() {
		return released;
	}
	public void setBook(GenericResult book) {
		this.book = book;
	}
	public void setTitleLabel(GenericResult titleLabel) {
		this.titleLabel = titleLabel;
	}
	public void setGenreLabel(GenericResult genreLabel) {
		this.genreLabel = genreLabel;
	}
	public void setReleased(GenericResult released) {
		this.released = released;
	}

	
}

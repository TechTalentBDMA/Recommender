package upc.bdam.recommender.neo4j.books.relation;

import upc.bdam.recommender.neo4j.IBinding;
import upc.bdam.recommender.neo4j.books.author.node.AuthorLabel;
import upc.bdam.recommender.neo4j.books.author.node.NodePerson;
import upc.bdam.recommender.neo4j.books.book.node.NodeBook;
import upc.bdam.recommender.neo4j.books.book.node.TitleLabel;

public class RelationResult implements IBinding{
	
	public TitleLabel titleLabel;
	public NodeBook book;
	public AuthorLabel authorLabel;
	public NodePerson author;
	public AuthorWroteBook AuthorWroteBook;
	public TitleLabel getTitleLabel() {
		return titleLabel;
	}

	public AuthorLabel getAuthorLabel() {
		return authorLabel;
	}
	public NodePerson getAuthor() {
		return author;
	}
	public AuthorWroteBook getAuthorWroteBook() {
		return AuthorWroteBook;
	}
	public void setTitleLabel(TitleLabel titleLabel) {
		this.titleLabel = titleLabel;
	}

	public NodeBook getBook() {
		return book;
	}

	public void setBook(NodeBook book) {
		this.book = book;
	}

	public void setAuthorLabel(AuthorLabel authorLabel) {
		this.authorLabel = authorLabel;
	}
	public void setAuthor(NodePerson author) {
		this.author = author;
	}
	public void setAuthorWroteBook(AuthorWroteBook authorWroteBook) {
		AuthorWroteBook = authorWroteBook;
	}	
}

package upc.bdam.recommender.neo4j.films.directedFilms;

import upc.bdam.recommender.neo4j.GenericResult;
import upc.bdam.recommender.neo4j.IBinding;

public class DirectedFilmsResult implements IBinding{
	
	public GenericResult director;
	public GenericResult film;
	public GenericResult getDirector() {
		return director;
	}
	public GenericResult getFilm() {
		return film;
	}
	public void setDirector(GenericResult director) {
		this.director = director;
	}
	public void setFilm(GenericResult film) {
		this.film = film;
	}
}

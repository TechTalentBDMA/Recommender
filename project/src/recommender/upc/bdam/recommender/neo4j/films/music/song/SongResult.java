package upc.bdam.recommender.neo4j.films.music.song;

import upc.bdam.recommender.neo4j.GenericResult;
import upc.bdam.recommender.neo4j.IBinding;

public class SongResult implements IBinding{
	
	public GenericResult id;
	public GenericResult filmLabel;
	public GenericResult released; 
	public GenericResult genre;
	public GenericResult getId() {
		return id;
	}
	public GenericResult getFilmLabel() {
		return filmLabel;
	}
	public GenericResult getReleased() {
		return released;
	}
	public GenericResult getGenre() {
		return genre;
	}
	public void setId(GenericResult id) {
		this.id = id;
	}
	public void setFilmLabel(GenericResult filmLabel) {
		this.filmLabel = filmLabel;
	}
	public void setReleased(GenericResult released) {
		this.released = released;
	}
	public void setGenre(GenericResult genre) {
		this.genre = genre;
	}

	
}

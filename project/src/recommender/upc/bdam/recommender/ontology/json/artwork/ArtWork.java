package upc.bdam.recommender.ontology.json.artwork;

import upc.bdam.recommender.ontology.json.GenericResult;
import upc.bdam.recommender.ontology.json.IBinding;

public class ArtWork implements IBinding {

	public GenericResult id=new GenericResult();
	public GenericResult title=new GenericResult();
	public GenericResult genre=new GenericResult();
	public GenericResult released=new GenericResult();

	public GenericResult getId() {
		return id;
	}

	public GenericResult getTitle() {
		return title;
	}

	public GenericResult getGenre() {
		return genre;
	}

	public GenericResult getReleased() {
		return released;
	}

	public void setId(GenericResult id) {
		this.id = id;
	}

	public void setTitle(GenericResult title) {
		this.title = title;
	}

	public void setGenre(GenericResult genre) {
		this.genre = genre;
	}

	public void setReleased(GenericResult released) {
		this.released = released;
	}
}

package upc.bdam.recommender.ontology.json.artwork;

import upc.bdam.recommender.ontology.json.GenericResult;
import upc.bdam.recommender.ontology.json.IBinding;

/**
 * Clase en la que se encapsulan los valores obtenidos de wikidata para artWork
 * @author Grupo 9: 
 *           - Antolín Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David Pérez Rodríguez
 *
 */
public class ArtWork implements IBinding {

	public GenericResult id				=new GenericResult();
	public GenericResult title				=new GenericResult();
	public GenericResult genre		=	new GenericResult();
	public GenericResult released		=new GenericResult();

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

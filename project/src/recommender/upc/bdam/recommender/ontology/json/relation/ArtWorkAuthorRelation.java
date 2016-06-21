package upc.bdam.recommender.ontology.json.relation;

import upc.bdam.recommender.ontology.json.GenericResult;
import upc.bdam.recommender.ontology.json.IBinding;

/**
 * Clase en la que se encapsulan los valores obtenidos de wikidata para relaciones con artWork
 * @author Grupo 9: 
 *           - Antol�n Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David P�rez Rodr�guez
 *
 */
public class ArtWorkAuthorRelation implements IBinding {

	public GenericResult artWorkTitle;
	public GenericResult artWorkId;
	public GenericResult authorName;
	public GenericResult authorId;

	public GenericResult getArtWorkTitle() {
		return artWorkTitle;
	}

	public GenericResult getArtWorkId() {
		return artWorkId;
	}

	public GenericResult getAuthorName() {
		return authorName;
	}

	public GenericResult getAuthorId() {
		return authorId;
	}

	public void setArtWorkTitle(GenericResult artWorkTitle) {
		this.artWorkTitle = artWorkTitle;
	}

	public void setArtWorkId(GenericResult artWorkId) {
		this.artWorkId = artWorkId;
	}

	public void setAuthorName(GenericResult authorName) {
		this.authorName = authorName;
	}

	public void setAuthorId(GenericResult authorId) {
		this.authorId = authorId;
	}

}
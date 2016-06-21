package upc.bdam.recommender.ontology.json.author;

import upc.bdam.recommender.ontology.json.GenericResult;
import upc.bdam.recommender.ontology.json.IBinding;

/**
 * Clase en la que se encapsulan los valores obtenidos de wikidata para autores
 * @author Grupo 9: 
 *           - Antolín Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David Pérez Rodríguez
 *
 */
public class Author implements IBinding {

	public GenericResult id=new GenericResult();
	public GenericResult born=new GenericResult();
	public GenericResult name=new GenericResult();

	public GenericResult getId() {
		return id;
	}

	public GenericResult getBorn() {
		return born;
	}

	public GenericResult getName() {
		return name;
	}

	public void setId(GenericResult id) {
		this.id = id;
	}

	public void setBorn(GenericResult born) {
		this.born = born;
	}

	public void setName(GenericResult name) {
		this.name = name;
	}

}
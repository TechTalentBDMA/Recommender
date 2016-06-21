package upc.bdam.recommender.ontology.json.genre;

import upc.bdam.recommender.ontology.json.GenericResult;
import upc.bdam.recommender.ontology.json.IBinding;

/**
 * Clase en la que se encapsulan los valores obtenidos de wikidata para género
 * @author Grupo 9: 
 *           - Antolín Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David Pérez Rodríguez
 *
 */
public class Genre implements IBinding {

	public GenericResult id=new GenericResult();
	public GenericResult name=new GenericResult();
	//public GenericResult nameNorm=new GenericResult();

	public GenericResult getId() {
		return id;
	}

	public GenericResult getName() {
		return name;
	}

	//public GenericResult getNameNormalized() {
	//	return nameNorm;
	//}

	public void setId(GenericResult id) {
		this.id = id;
	}

	public void setName(GenericResult name) {
		this.name = name;
	}

	//public void setNameNormalized(GenericResult nameNorm) {
	//	this.name = nameNorm;
	//}

}
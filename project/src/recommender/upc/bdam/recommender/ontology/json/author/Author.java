package upc.bdam.recommender.ontology.json.author;

import upc.bdam.recommender.ontology.json.GenericResult;
import upc.bdam.recommender.ontology.json.IBinding;

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
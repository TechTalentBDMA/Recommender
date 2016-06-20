package upc.bdam.recommender.ontology.json.band;

import upc.bdam.recommender.ontology.json.GenericResult;
import upc.bdam.recommender.ontology.json.IBinding;

public class Band implements IBinding {

	public GenericResult id=new GenericResult();
	public GenericResult released=new GenericResult();
	public GenericResult name=new GenericResult();

	public GenericResult getId() {
		return id;
	}

	public GenericResult getReleased() {
		return released;
	}

	public GenericResult getName() {
		return name;
	}

	public void setId(GenericResult id) {
		this.id = id;
	}

	public void setReleased(GenericResult released) {
		this.released = released;
	}

	public void setName(GenericResult name) {
		this.name = name;
	}

}
package upc.bdam.recommender.ontology.json.relation;

import upc.bdam.recommender.ontology.json.GenericResult;
import upc.bdam.recommender.ontology.json.IBinding;

/**
 * Clase que encapsula los datos relacionales entre dos nodos
 * @author Grupo 9: 
 *           - Antol�n Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David P�rez Rodr�guez
 *
 */
public class LeftRightRelation implements IBinding {
	//declaraci�n del tipo de relaciones.
	public GenericResult leftName;
	public GenericResult leftId;
	public GenericResult RightName;
	public GenericResult RightId;
	
	//declaraci�n de m�todos getter
	public GenericResult getLeftName() {
		return leftName;
	}
	public void setLeftName(GenericResult leftName) {
		this.leftName = leftName;
	}
	public GenericResult getLeftId() {
		return leftId;
	}
	
	//declaraci�n de m�todos setter
	public void setLeftId(GenericResult leftId) {
		this.leftId = leftId;
	}
	public GenericResult getRightName() {
		return RightName;
	}
	public void setRightName(GenericResult rightName) {
		RightName = rightName;
	}
	public GenericResult getRightId() {
		return RightId;
	}
	public void setRightId(GenericResult rightId) {
		RightId = rightId;
	}


}
package upc.bdam.recommender.ontology.json.relation;

import upc.bdam.recommender.ontology.json.GenericResult;
import upc.bdam.recommender.ontology.json.IBinding;

/**
 * Clase que encapsula los datos relacionales entre dos nodos
 * @author Grupo 9: 
 *           - Antolín Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David Pérez Rodríguez
 *
 */
public class LeftRightRelation implements IBinding {
	//declaración del tipo de relaciones.
	public GenericResult leftName;
	public GenericResult leftId;
	public GenericResult RightName;
	public GenericResult RightId;
	
	//declaración de métodos getter
	public GenericResult getLeftName() {
		return leftName;
	}
	public void setLeftName(GenericResult leftName) {
		this.leftName = leftName;
	}
	public GenericResult getLeftId() {
		return leftId;
	}
	
	//declaración de métodos setter
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
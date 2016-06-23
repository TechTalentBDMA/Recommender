package upc.bdam.recommender.graphupdater.schema;

/**
 * Clase base para los esquemas almacenados en Big2
 * @author Grupo 9: 
 *           - Antol�n Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David P�rez Rodr�guez
 *
 */
public class Big2SGraphGuardSchema {
	//declaraci�n de atributos
	private long id;
	private long userId;

	//declaraci�n de m�todos setter y getter
	public long getId() {
		return id;
	}

	public long getUserId() {
		return userId;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
}

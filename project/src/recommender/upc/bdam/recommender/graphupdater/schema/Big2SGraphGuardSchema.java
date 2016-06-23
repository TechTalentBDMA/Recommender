package upc.bdam.recommender.graphupdater.schema;

/**
 * Clase base para los esquemas almacenados en Big2
 * @author Grupo 9: 
 *           - Antolín Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David Pérez Rodríguez
 *
 */
public class Big2SGraphGuardSchema {
	//declaración de atributos
	private long id;
	private long userId;

	//declaración de métodos setter y getter
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

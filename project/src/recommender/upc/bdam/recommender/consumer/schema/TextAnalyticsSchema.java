package upc.bdam.recommender.consumer.schema;


/**
 * Clase abstracta de la que heredan todos los esquemas de formato de ficheros analizados en text analytics
 * 
 * @author Grupo 9: 
 *           - Antolín Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David Pérez Rodríguez
 *
 */
public abstract class TextAnalyticsSchema {
	//declaración de variables
	private long timestamp;
	private String userId;
	private byte status;
	private String metadata;
	
	
	//campos de identificación 

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	//declaración de métodos getter
	public long getTimestamp() {
		return timestamp;
	}

	public String getUserId() {
		return userId;
	}

	public byte getStatus() {
		return status;
	}

	//declaración de métodos setter
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

}

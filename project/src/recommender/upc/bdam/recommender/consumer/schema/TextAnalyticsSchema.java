package upc.bdam.recommender.consumer.schema;


/**
 * Clase abstracta de la que heredan todos los esquemas de formato de ficheros analizados en text analytics
 * 
 * @author Grupo 9: 
 *           - Antol�n Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David P�rez Rodr�guez
 *
 */
public abstract class TextAnalyticsSchema {
	//declaraci�n de variables
	private long timestamp;
	private String userId;
	private byte status;
	private String metadata;
	
	
	//campos de identificaci�n 

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	//declaraci�n de m�todos getter
	public long getTimestamp() {
		return timestamp;
	}

	public String getUserId() {
		return userId;
	}

	public byte getStatus() {
		return status;
	}

	//declaraci�n de m�todos setter
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

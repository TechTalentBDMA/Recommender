package upc.bdam.recommender.consumer.schema;

/**
 * Clase que lee de Big2 el esquema generado por el analytics para ficheros de video
 * 
 * @author Grupo 9: 
 *           - Antol�n Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David P�rez Rodr�guez
 *
 */
public class SchemaVideoBean extends TextAnalyticsSchema {

	//declaraci�n de variables
	private String fileType;
	private Metadata metadata;

	//declaraci�n de m�todos getter
	public String getFileType() {
		return fileType;
	}

	public Metadata getMetadata() {
		return metadata;
	}

	//declaraci�n de setter
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

}

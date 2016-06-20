package upc.bdam.recommender.consumer.schema;

/**
 * Clase que lee de Big2 el esquema generado por el analytics para ficheros de video
 * 
 * @author Grupo 9: 
 *           - Antolín Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David Pérez Rodríguez
 *
 */
public class SchemaVideoBean extends TextAnalyticsSchema {

	//declaración de variables
	private String fileType;
	private Metadata metadata;

	//declaración de métodos getter
	public String getFileType() {
		return fileType;
	}

	public Metadata getMetadata() {
		return metadata;
	}

	//declaración de setter
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

}

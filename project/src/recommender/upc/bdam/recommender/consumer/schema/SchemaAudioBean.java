package upc.bdam.recommender.consumer.schema;

/**
 * Clase que lee de Big2 el esquema generado por el analytics para ficheros de audio
 * 
 * @author Grupo 9: 
 *           - Antolín Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David Pérez Rodríguez
 *
 */
public class SchemaAudioBean extends TextAnalyticsSchema {

	//definición de variables
	private String fileType;
	private Metadata metadata;

	//Métodos getter
	public String getFileType() {
		return fileType;
	}

	public Metadata getMetadata() {
		return metadata;
	}

	
	//métodos getter
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

}

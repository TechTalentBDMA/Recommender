package upc.bdam.recommender.consumer.schema;

/**
 * Clase que lee de Big2 el esquema generado por el analytics para ficheros de audio
 * 
 * @author Grupo 9: 
 *           - Antol�n Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David P�rez Rodr�guez
 *
 */
public class SchemaAudioBean extends TextAnalyticsSchema {

	//definici�n de variables
	private String fileType;
	private Metadata metadata;

	//M�todos getter
	public String getFileType() {
		return fileType;
	}

	public Metadata getMetadata() {
		return metadata;
	}

	
	//m�todos getter
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

}

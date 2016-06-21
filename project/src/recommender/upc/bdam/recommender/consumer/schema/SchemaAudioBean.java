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

	//Métodos getter
	public String getFileType() {
		return fileType;
	}
	
	//métodos getter
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
}

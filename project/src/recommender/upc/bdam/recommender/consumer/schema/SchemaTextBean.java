package upc.bdam.recommender.consumer.schema;

/**
 * Clase que lee de Big2 el esquema generado por el analytics para ficheros de texto
 * 
 * @author Grupo 9: 
 *           - Antolín Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David Pérez Rodríguez
 *
 */
public class SchemaTextBean extends TextAnalyticsSchema {

	private String fileType;
	private String content;
	private Metadata metadata;
	private int palabras;

	public String getFileType() {
		return fileType;
	}

	public String getContent() {
		return content;
	}

	public Metadata getMetadata() {
		return metadata;
	}

	public int getPalabras() {
		return palabras;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	public void setPalabras(int palabras) {
		this.palabras = palabras;
	}

}

package upc.bdam.recommender.consumer.schema;

/**
 * Clase que lee de Big2 el esquema generado por el analytics para ficheros web
 * 
 * @author Grupo 9: 
 *           - Antolín Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David Pérez Rodríguez
 *
 */
public class SchemaWebBean extends TextAnalyticsSchema {

	//declaración de variables
	private String url;
	private String content;

	//declaración de métodos getter
	public String getUrl() {
		return url;
	}

	public String getContent() {
		return content;
	}

	//métodos setter
	public void setUrl(String url) {
		this.url = url;
	}

	public void setContent(String content) {
		this.content = content;
	}
}

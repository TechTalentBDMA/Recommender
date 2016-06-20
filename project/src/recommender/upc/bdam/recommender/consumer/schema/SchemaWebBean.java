package upc.bdam.recommender.consumer.schema;

/**
 * Clase que lee de Big2 el esquema generado por el analytics para ficheros web
 * 
 * @author Grupo 9: 
 *           - Antol�n Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David P�rez Rodr�guez
 *
 */
public class SchemaWebBean extends TextAnalyticsSchema {

	//declaraci�n de variables
	private String url;
	private String content;

	//declaraci�n de m�todos getter
	public String getUrl() {
		return url;
	}

	public String getContent() {
		return content;
	}

	//m�todos setter
	public void setUrl(String url) {
		this.url = url;
	}

	public void setContent(String content) {
		this.content = content;
	}
}

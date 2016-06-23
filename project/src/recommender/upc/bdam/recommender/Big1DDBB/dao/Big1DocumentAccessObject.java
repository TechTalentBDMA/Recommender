package upc.bdam.recommender.Big1DDBB.dao;

import upc.bdam.recommender.consumer.schema.SchemaAudioBean;
import upc.bdam.recommender.consumer.schema.SchemaTextBean;
import upc.bdam.recommender.consumer.schema.SchemaVideoBean;
import upc.bdam.recommender.consumer.schema.SchemaWebBean;
import upc.bdam.recommender.consumer.schema.TextAnalyticsSchema;

/**
 * Clase de interfaz para la inserci�n de datos en Big1
 * 
 * @author Grupo 9: - Antol�n Barrena Rico - Carles Castillejo - Raffaele
 *         Ghermandi - David P�rez Rodr�guez
 *
 */
public class Big1DocumentAccessObject {
	// declaraci�n de la clsae dataSource que implementa el manejo con BBDD
	private Big1DataSource B1DS = new Big1DataSource();

	/**
	 * Interfaz para la escritura de la informaci�n de texto
	 * 
	 * @param text
	 */
	public void consumeText(TextAnalyticsSchema text) {
		SchemaTextBean textSchema = (SchemaTextBean) text;
		B1DS.insertTextSchema(textSchema);
	}

	/**
	 * Interfaz para la escritura de la informaci�n web
	 * 
	 * @param video
	 */
	public void consumeWeb(TextAnalyticsSchema video) {
		SchemaWebBean webSchema = (SchemaWebBean) video;
		B1DS.insertWebSchema(webSchema);
	}

	/**
	 * Interfaz para la escritura de la informaci�n de audio
	 * 
	 * @param audio
	 */
	public void consumeAudio(TextAnalyticsSchema audio) {
		SchemaAudioBean audioSchema = (SchemaAudioBean) audio;
		B1DS.insertAudioSchema(audioSchema);
	}

	/**
	 * Replica el contenido de video insertado en la BBDD de conocimiento desde
	 * Big2
	 * 
	 * @param web
	 */
	public void consumeVideo(TextAnalyticsSchema web) {
		SchemaVideoBean audioSchema = (SchemaVideoBean) web;
		B1DS.insertVideoSchema(audioSchema);
	}
}

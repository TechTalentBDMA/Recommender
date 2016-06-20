package upc.bdam.recommender.documentDDBB.dao;

import upc.bdam.recommender.consumer.schema.SchemaAudioBean;
import upc.bdam.recommender.consumer.schema.SchemaTextBean;
import upc.bdam.recommender.consumer.schema.SchemaVideoBean;
import upc.bdam.recommender.consumer.schema.SchemaWebBean;
import upc.bdam.recommender.consumer.schema.TextAnalyticsSchema;

/**
 * Clase de interfaz para la inserción de datos en Big1
 * @author Grupo 9: 
 *           - Antolín Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David Pérez Rodríguez
 *
 */
public class TextAnalyticsDocumentAccesObject {
	//declaración de la clsae dataSource que implementa el manejo con BBDD
	private TextAnalyticsDataSource textAnalyticsDS=new TextAnalyticsDataSource();
	
	/**
	 * Interfaz para la escritura de la información de texto
	 * @param text
	 */
	public void consumeText(TextAnalyticsSchema text) {
		SchemaTextBean textSchema = (SchemaTextBean) text;
		textAnalyticsDS.insertTextSchema(textSchema);
	}

	/**
	 * Interfaz para la escritura de la información web
	 * @param video
	 */
	public void consumeWeb(TextAnalyticsSchema video) {
		SchemaWebBean webSchema = (SchemaWebBean) video;
		textAnalyticsDS.insertWebSchema(webSchema);
	}

	/**
	 * Interfaz para la escritura de la información de audio
	 * @param audio
	 */
	public void consumeAudio(TextAnalyticsSchema audio) {
		SchemaAudioBean audioSchema = (SchemaAudioBean) audio;
		textAnalyticsDS.insertAudioSchema(audioSchema);
	}

	/**
	 * Interfaz para la escritura de la información de video
	 * @param web
	 */
	public void consumeVideo(TextAnalyticsSchema web) {
		SchemaVideoBean audioSchema = (SchemaVideoBean) web;
		textAnalyticsDS.insertVideoSchema(audioSchema);
	}
}

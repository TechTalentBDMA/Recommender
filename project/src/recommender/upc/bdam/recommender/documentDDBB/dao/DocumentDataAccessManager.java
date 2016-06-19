package upc.bdam.recommender.documentDDBB.dao;

import java.util.Date;

import upc.bdam.recommender.consumer.schema.SchemaAudioBean;
import upc.bdam.recommender.consumer.schema.SchemaTextBean;
import upc.bdam.recommender.consumer.schema.SchemaVideoBean;
import upc.bdam.recommender.consumer.schema.SchemaWebBean;
import upc.bdam.recommender.kafka.KafkaBean;
import upc.bdam.recommender.ontology.json.IBinding;

/**
 * Clase que gestiona la inserción en el Mongo de persistencia.
 * Incluye tantos Access Object como esquemas de BBDD en las que va a insertar.
 * 
 *
 */
public class DocumentDataAccessManager {

	//Se definen constantes para accesos de inserción, actualización y borrado en la base de datos ontológica
	public static final byte DOCUMENT_PERSON_INSERT = 1;
	public static final byte DOCUMENT_ARTWORK_INSERT = 2;
	public static final byte DOCUMENT_SHEMA_TEXT_INSERT = 3;
	public static final byte DOCUMENT_SHEMA_WEB_INSERT = 4;
	public static final byte DOCUMENT_SHEMA_AUDIO_INSERT = 5;
	public static final byte DOCUMENT_SHEMA_VIDEO_INSERT = 6;

	public static final byte DOCUMENT_PERSON_UPDATE = 31;
	public static final byte DOCUMENT_ARTWORK_UPDATE = 32;
	public static final byte DOCUMENT_SHEMA_TEXT_UPDATE = 33;
	public static final byte DOCUMENT_SHEMA_WEB_UPDATE = 34;
	public static final byte DOCUMENT_SHEMA_AUDIO_UPDATE_ = 35;
	public static final byte DOCUMENT_SHEMA_VIDEO_UPDATE = 36;
	
	public static final byte DOCUMENT_PERSON_DELETE = 71;
	public static final byte DOCUMENT_ARTWORK_DELETE = 72;
	public static final byte DOCUMENT_SHEMA_TEXT_DELETE = 73;
	public static final byte DOCUMENT_SHEMA_WEB_DELETE = 74;
	public static final byte DOCUMENT_SHEMA_AUDIO_DELETE = 75;
	public static final byte DOCUMENT_SHEMA_VIDEO_DELETE = 76;

	//declaración de las variables de clase
	private static DocumentDataAccessManager instance = null;
	
	//Acceso a la BBDD de persistencia
	private DocumentDataAccessObject documentAO = new DocumentDataAccessObject();
	
	//Acceso a Big2
	private TextAnalyticsDocumentAccesObject textAnalyticsAO =new TextAnalyticsDocumentAccesObject();
	
	/**
	 * Declaración del constructor. Privado para la implementación del patrón singleton
	 */
	private DocumentDataAccessManager() {}

	/**
	 * Métod estático para el acceso a la única instancia de la clase
	 * @return
	 */
	public static final DocumentDataAccessManager getInstance() {
		if (instance != null)
			return instance;
		else {
			instance = new DocumentDataAccessManager();
			return instance;
		}
	}

	/**
	 * Inserta información en la BBDD de ontologías.
	 * @param query
	 * @param values
	 * @throws Exception
	 */
	public void insert(byte query, IBinding[] values) throws Exception {
		switch (query) {
		case DOCUMENT_PERSON_INSERT:
			insertPersonDocument(values);
			break;
		case DOCUMENT_ARTWORK_INSERT:
			insertArtWorkDocument(values);
			break;

		default:
			break;
		}
	}

	
	/**
	 * Inserción de los esquemas de los documentos
	 * @param query
	 * @param value
	 * @throws Exception
	 */
	public void insert(byte query, KafkaBean value) throws Exception {
		switch (query) {
		case DOCUMENT_SHEMA_TEXT_INSERT:
			consumeText(value);
			break;
		case DOCUMENT_SHEMA_WEB_INSERT:
			consumeWeb(value);
			break;
		case DOCUMENT_SHEMA_AUDIO_INSERT:
			consumeVideo(value);
			break;
		case DOCUMENT_SHEMA_VIDEO_INSERT:
			consumeVideo(value);
			break;
		default:
			break;
		}
	}
	//////////////////////////////////////////////////////////////////////
	//  INSERCIÓN DE LA INFORMACIÓN INSERTADA EN LA BBDD DE ONTOLOGIAS  //  
	//////////////////////////////////////////////////////////////////////
	
	/**
	 * Inserta nodos de tipo persona, lo cual incluye directores, escritores
	 * cantantes, etc.
	 * @param values
	 */
	private void insertPersonDocument(IBinding[] values) {
		documentAO.insertPersonDocument(values);
	}

	/**
	 * Inserta la nodos cuya información define nodos de trabajos de 
	 * producciones artísticas o intelectuales.
	 * @param values
	 */
	private void insertArtWorkDocument(IBinding[] values) {
		documentAO.insertArtWorkDocument(values);
	}
	
	
	//////////////////////////////////////////////////////////////////////
	//   BLOQUE DE MÉTODOS DE INSERCIÓN EN BIG1                         //
	//////////////////////////////////////////////////////////////////////
	/**
	 * Inserta en el esquema de text analytics la salida del análisis de la 
	 * información de documentos de texto recabada de los usuarios del recomendador
	 * @param text
	 */
	private void consumeText(KafkaBean value){

		Date timestamp=new Date();
		SchemaTextBean text=new SchemaTextBean();
		text.setContent(value.getContent());
		text.setFileType(value.getMimeType());
		text.setTimestamp(timestamp.getTime());
		text.setUserId(value.getId());
		//web.setMetadata(value.getMetadata());
	
		textAnalyticsAO.consumeText(text);
	}
	
	/**
	 * Inserta en el esquema de text analytics la salida del análisis de la 
	 * información de análisis de navegación web recabada de los usuarios del recomendador
	 * @param video
	 */
	private void consumeWeb(KafkaBean value){
		Date timestamp=new Date();
		SchemaWebBean web=new SchemaWebBean();
		web.setContent(value.getContent());
//		audio.setMetadata(value.getMetadata());
		web.setTimestamp(timestamp.getTime());
		web.setUserId(value.getId());
		web.setUrl(value.getMetadata());
		textAnalyticsAO.consumeWeb(web);
	}
	
	/**
	 * Inserta en el esquema de text analytics la salida del análisis de la 
	 * información de documentos de audio recabada de los usuarios del recomendador
	 * @param audio
	 */
	private void consumeAudio(KafkaBean value){
		Date timestamp=new Date();
		SchemaAudioBean audio=new SchemaAudioBean();
		audio.setFileType("");
//		audio.setMetadata(value.getMetadata());
		audio.setTimestamp(timestamp.getTime());
		audio.setUserId(value.getId());
		textAnalyticsAO.consumeAudio(audio);
	}
	
	/**
	 * Inserta en el esquema de text analytics la salida del análisis de la 
	 * información de documentos de video recabada de los usuarios del recomendador
	 * @param audio
	 */
	private void consumeVideo(KafkaBean value){
		Date timestamp=new Date();
		SchemaVideoBean video=new SchemaVideoBean();
		video.setFileType("");
//		audio.setMetadata(value.getMetadata());
		video.setTimestamp(timestamp.getTime());
		video.setUserId(value.getId());
		textAnalyticsAO.consumeVideo(video);
	}
	
	public void consume(KafkaBean bean){
		byte tipo=bean.getType();
		switch(tipo){
		case KafkaBean.KAFKA_AUDIO:
			consumeAudio(bean);
			break;
		case KafkaBean.KAFKA_TEXTO:
			consumeText(bean);
			break;
		case KafkaBean.KAFKA_VIDEO:
			consumeVideo(bean);
			break;
		case KafkaBean.KAFKA_WEB:
			consumeWeb(bean);
			break;
		}
	}
}

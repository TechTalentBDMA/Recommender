package upc.bdam.recommender.documentDDBB.dao;

import upc.bdam.recommender.consumer.schema.SchemaAudioBean;
import upc.bdam.recommender.consumer.schema.SchemaTextBean;
import upc.bdam.recommender.consumer.schema.SchemaVideoBean;
import upc.bdam.recommender.consumer.schema.SchemaWebBean;
import upc.bdam.recommender.consumer.schema.TextAnalyticsSchema;
import upc.bdam.recommender.ontology.json.IBinding;

/**
 * Clase que gestiona la inserción en la BBDD documental (MongoDB).
 * Incluye tantos Access Object como esquemas de BBDD en las que va a insertar.
 * 
 *
 */
public class DocumentDataAccessManager {

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


	private static DocumentDataAccessManager instance = null;
	private DocumentDataAccessObject documentAO = new DocumentDataAccessObject();
	private TextAnalyticsDocumentAccesObject textAnalyticsAO =new TextAnalyticsDocumentAccesObject();
	
	private DocumentDataAccessManager() {

	}

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

	
	public void insert(byte query, TextAnalyticsSchema value) throws Exception {
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
	/////////////////////////////////////////////////////////////////
	//   BLOQUE DE MÉTODOS DE INSERCIÓN EN LA BBDD DE ONTOLOGÍAS   //
	/////////////////////////////////////////////////////////////////
	
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
	//   BLOQUE DE MÉTODOS DE INSERCIÓN DE LA SALIDA DE TEXT ANALYTICS  //
	//////////////////////////////////////////////////////////////////////
	/**
	 * Inserta en el esquema de text analytics la salida del análisis de la 
	 * información de documentos de texto recabada de los usuarios del recomendador
	 * @param text
	 */
	public void consumeText(TextAnalyticsSchema text){
		textAnalyticsAO.consumeText(text);
	}
	
	/**
	 * Inserta en el esquema de text analytics la salida del análisis de la 
	 * información de análisis de navegación web recabada de los usuarios del recomendador
	 * @param video
	 */
	public void consumeWeb(TextAnalyticsSchema web){
		textAnalyticsAO.consumeWeb(web);
	}
	
	/**
	 * Inserta en el esquema de text analytics la salida del análisis de la 
	 * información de documentos de audio recabada de los usuarios del recomendador
	 * @param audio
	 */
	public void consumeAudio(TextAnalyticsSchema audio){
		textAnalyticsAO.consumeAudio(audio);
	}
	
	/**
	 * Inserta en el esquema de text analytics la salida del análisis de la 
	 * información de documentos de video recabada de los usuarios del recomendador
	 * @param audio
	 */
	public void consumeVideo(TextAnalyticsSchema video){
		textAnalyticsAO.consumeVideo(video);
	}
}

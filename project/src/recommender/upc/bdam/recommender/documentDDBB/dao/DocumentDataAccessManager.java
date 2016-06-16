package upc.bdam.recommender.documentDDBB.dao;

import upc.bdam.recommender.consumer.schema.TextAnalyticsSchema;
import upc.bdam.recommender.ontology.json.IBinding;

/**
 * Clase que gestiona la inserci�n en el Mongo de persistencia.
 * Incluye tantos Access Object como esquemas de BBDD en las que va a insertar.
 * 
 *
 */
public class DocumentDataAccessManager {

	//Se definen constantes para accesos de inserci�n, actualizaci�n y borrado en la base de datos ontol�gica
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

	//declaraci�n de las variables de clase
	private static DocumentDataAccessManager instance = null;
	
	//Acceso a la BBDD de persistencia
	private DocumentDataAccessObject documentAO = new DocumentDataAccessObject();
	
	//Acceso a Big2
	private TextAnalyticsDocumentAccesObject textAnalyticsAO =new TextAnalyticsDocumentAccesObject();
	
	/**
	 * Declaraci�n del constructor. Privado para la implementaci�n del patr�n singleton
	 */
	private DocumentDataAccessManager() {}

	/**
	 * M�tod est�tico para el acceso a la �nica instancia de la clase
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
	 * Inserta informaci�n en la BBDD de ontolog�as.
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
	 * Inserci�n de los esquemas de los documentos
	 * @param query
	 * @param value
	 * @throws Exception
	 */
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
	//////////////////////////////////////////////////////////////////////
	//  INSERCI�N DE LA INFORMACI�N INSERTADA EN LA BBDD DE ONTOLOGIAS  //  
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
	 * Inserta la nodos cuya informaci�n define nodos de trabajos de 
	 * producciones art�sticas o intelectuales.
	 * @param values
	 */
	private void insertArtWorkDocument(IBinding[] values) {
		documentAO.insertArtWorkDocument(values);
	}
	
	
	//////////////////////////////////////////////////////////////////////
	//   BLOQUE DE M�TODOS DE INSERCI�N EN BIG1                         //
	//////////////////////////////////////////////////////////////////////
	/**
	 * Inserta en el esquema de text analytics la salida del an�lisis de la 
	 * informaci�n de documentos de texto recabada de los usuarios del recomendador
	 * @param text
	 */
	public void consumeText(TextAnalyticsSchema text){
		textAnalyticsAO.consumeText(text);
	}
	
	/**
	 * Inserta en el esquema de text analytics la salida del an�lisis de la 
	 * informaci�n de an�lisis de navegaci�n web recabada de los usuarios del recomendador
	 * @param video
	 */
	public void consumeWeb(TextAnalyticsSchema web){
		textAnalyticsAO.consumeWeb(web);
	}
	
	/**
	 * Inserta en el esquema de text analytics la salida del an�lisis de la 
	 * informaci�n de documentos de audio recabada de los usuarios del recomendador
	 * @param audio
	 */
	public void consumeAudio(TextAnalyticsSchema audio){
		textAnalyticsAO.consumeAudio(audio);
	}
	
	/**
	 * Inserta en el esquema de text analytics la salida del an�lisis de la 
	 * informaci�n de documentos de video recabada de los usuarios del recomendador
	 * @param audio
	 */
	public void consumeVideo(TextAnalyticsSchema video){
		textAnalyticsAO.consumeVideo(video);
	}
}

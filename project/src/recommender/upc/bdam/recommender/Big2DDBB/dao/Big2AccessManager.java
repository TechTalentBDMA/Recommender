	package upc.bdam.recommender.Big2DDBB.dao;

import java.util.List;

import upc.bdam.recommender.graphupdater.schema.Big2SGraphGuardSchema;

/**
 * Clase que gestiona la inserción en el Mongo de persistencia.
 * Incluye tantos Access Object como esquemas de BBDD en las que va a insertar.
 * 
 *
 */
public class Big2AccessManager {

	//Se definen constantes para accesos de inserción, actualización y borrado en la base de datos ontológica
	public static final byte DOCUMENT_PERSON_INSERT = 1;
	public static final byte DOCUMENT_ARTWORK_INSERT = 2;
	public static final byte DOCUMENT_SHEMA_TEXT_INSERT = 3;
	public static final byte DOCUMENT_SHEMA_WEB_INSERT = 4;
	public static final byte DOCUMENT_SHEMA_AUDIO_INSERT = 5;
	public static final byte DOCUMENT_SHEMA_VIDEO_INSERT = 6;
	public static final byte DOCUMENT_BAND_INSERT = 7;
	public static final byte DOCUMENT_GENRE_INSERT = 8;
	public static final byte DOCUMENT_USER_INSERT = 9;

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
	private static Big2AccessManager instance = null;
	
	//Acceso a Big2
	private Big2DocumentAccessObject Big2AO =new Big2DocumentAccessObject();
	
	/**
	 * Declaración del constructor. Privado para la implementación del patrón singleton
	 */
	private Big2AccessManager() {}

	/**
	 * Métod estático para el acceso a la única instancia de la clase
	 * @return
	 */
	public static final Big2AccessManager getInstance() {
		if (instance != null)
			return instance;
		else {
			instance = new Big2AccessManager();
			return instance;
		}
	}

	
	/**
	 * Inserta en el esquema de text analytics la salida del análisis de la 
	 * información de documentos de video recabada de los usuarios del recomendador
	 * @param audio
	 */
	public List<Big2SGraphGuardSchema> checkBig2TextUpdate(Long value){
		return Big2AO.checkBig2TextUpdate(value);
	}
	
	/**
	 * Método con el que se consulta si existen más inserciones en Big2 desde la
	 * última lectura realizada
	 * 
	 * @param timestamp
	 * @return
	 */
	public List<Big2SGraphGuardSchema> checkBig2WebUpdate(long timestamp) {
		return Big2AO.checkBig2WebUpdate(timestamp);
	}

	/**
	 * Método con el que se consulta si existen más inserciones en Big2 desde la
	 * última lectura realizada
	 * 
	 * @param timestamp
	 * @return
	 */
	public List<Big2SGraphGuardSchema> checkBig2VideoUpdate(long timestamp) {
		return Big2AO.checkBig2VideoUpdate(timestamp);
	}

	/**
	 * Método con el que se consulta si existen más inserciones en Big2 desde la
	 * última lectura realizada
	 * 
	 * @param timestamp
	 * @return
	 */
	public List<Big2SGraphGuardSchema> checkBig2AudioUpdate(long timestamp) {
		return Big2AO.checkBig2AudioUpdate(timestamp);
	}

}

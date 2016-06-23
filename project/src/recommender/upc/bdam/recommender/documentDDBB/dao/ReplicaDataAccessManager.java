package upc.bdam.recommender.documentDDBB.dao;

import upc.bdam.recommender.graphupdater.schema.Big2AudioSchemaBean;
import upc.bdam.recommender.graphupdater.schema.Big2SGraphGuardSchema;
import upc.bdam.recommender.graphupdater.schema.Big2TextSchemaBean;
import upc.bdam.recommender.graphupdater.schema.Big2VideoSchemaBean;
import upc.bdam.recommender.graphupdater.schema.Big2WebSchemaBean;
import upc.bdam.recommender.kafka.KafkaBean;
import upc.bdam.recommender.ontology.json.IBinding;

/**
 * Clase que gestiona la inserción en el Mongo de persistencia. Incluye tantos
 * Access Object como esquemas de BBDD en las que va a insertar.
 * 
 *
 */
public class ReplicaDataAccessManager {

	// Se definen constantes para accesos de inserción, actualización y borrado
	// en la base de datos ontológica
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

	// declaración de las variables de clase
	private static ReplicaDataAccessManager instance = null;

	// Acceso a la BBDD de persistencia
	private ReplicaDataAccessObject documentAO = new ReplicaDataAccessObject();

	/**
	 * Declaración del constructor. Privado para la implementación del patrón
	 * singleton
	 */
	private ReplicaDataAccessManager() {
	}

	/**
	 * Métod estático para el acceso a la única instancia de la clase
	 * 
	 * @return
	 */
	public static final ReplicaDataAccessManager getInstance() {
		if (instance != null)
			return instance;
		else {
			instance = new ReplicaDataAccessManager();
			return instance;
		}
	}

	/**
	 * Inserta información en la BBDD de ontologías.
	 * 
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
		case DOCUMENT_GENRE_INSERT:
			insertGenreDocument(values);
			break;
		case DOCUMENT_BAND_INSERT:
			insertBandDocument(values);
			break;
		default:
			break;
		}
	}

	/**
	 * Inserta en el esquema de text analytics la salida del análisis de la
	 * información de documentos de video recabada de los usuarios del
	 * recomendador
	 * 
	 * @param audio
	 */
	private void insertUser(KafkaBean value) {
		documentAO.insertUser(value);
	}

	/**
	 * Inserción de los esquemas de los documentos
	 * 
	 * @param query
	 * @param value
	 * @throws Exception
	 */
	public void updateModel(KafkaBean value) throws Exception {
		insertUser(value);
	}

	/**
	 * Inserción de los esquemas de los documentos
	 * 
	 * @param query
	 * @param value
	 * @throws Exception
	 */
	public void updateModel(Big2SGraphGuardSchema value) {
		if (value instanceof Big2AudioSchemaBean)
			documentAO.updateAudio((Big2AudioSchemaBean) value);
		else if (value instanceof Big2TextSchemaBean)
			documentAO.updateText((Big2TextSchemaBean) value);
		else if (value instanceof Big2VideoSchemaBean)
			documentAO.updateVideo((Big2VideoSchemaBean) value);
		else if (value instanceof Big2WebSchemaBean)
			documentAO.updateWeb((Big2WebSchemaBean) value);
	}

	//////////////////////////////////////////////////////////////////////
	// INSERCIÓN DE LA INFORMACIÓN INSERTADA EN LA BBDD DE ONTOLOGIAS //
	//////////////////////////////////////////////////////////////////////

	/**
	 * Inserta nodos de tipo persona, lo cual incluye directores, escritores
	 * cantantes, etc.
	 * 
	 * @param values
	 */
	private void insertPersonDocument(IBinding[] values) {
		documentAO.insertPersonDocument(values);
	}

	/**
	 * Inserta la nodos cuya información define nodos grupos musicales
	 * 
	 * @param values
	 */
	private void insertBandDocument(IBinding[] values) {
		documentAO.insertBandDocument(values);
	}

	/**
	 * Inserta nodos de tipo genre, lo cual incluye directores, escritores
	 * cantantes, etc.
	 * 
	 * @param values
	 */
	private void insertGenreDocument(IBinding[] values) {
		documentAO.insertGenreDocument(values);
	}

	/**
	 * Inserta la nodos cuya información define nodos de trabajos de
	 * producciones artísticas o intelectuales.
	 * 
	 * @param values
	 */
	private void insertArtWorkDocument(IBinding[] values) {
		documentAO.insertArtWorkDocument(values);
	}

}

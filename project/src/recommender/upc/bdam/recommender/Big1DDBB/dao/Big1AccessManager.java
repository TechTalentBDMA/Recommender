	package upc.bdam.recommender.Big1DDBB.dao;

import java.util.Date;

import upc.bdam.recommender.consumer.schema.SchemaAudioBean;
import upc.bdam.recommender.consumer.schema.SchemaTextBean;
import upc.bdam.recommender.consumer.schema.SchemaVideoBean;
import upc.bdam.recommender.consumer.schema.SchemaWebBean;
import upc.bdam.recommender.kafka.KafkaBean;

/**
 * Clase que gestiona la inserción en el Mongo de persistencia.
 * Incluye tantos Access Object como esquemas de BBDD en las que va a insertar.
 * 
 *
 */
public class Big1AccessManager {

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
	private static Big1AccessManager instance = null;
	
	//Acceso a Big2
	private Big1DocumentAccessObject Big1AO =new Big1DocumentAccessObject();
	
	/**
	 * Declaración del constructor. Privado para la implementación del patrón singleton
	 */
	private Big1AccessManager() {}

	/**
	 * Métod estático para el acceso a la única instancia de la clase
	 * @return
	 */
	public static final Big1AccessManager getInstance() {
		if (instance != null)
			return instance;
		else {
			instance = new Big1AccessManager();
			return instance;
		}
	}

	/**
	 * Se identifica el tipo de documento que se va a consumir
	 * @param bean
	 */
	public void consume(KafkaBean bean){
		String tipo=bean.getMimeType();
		if (tipo.contains("audio"))
			consumeAudio(bean);
		else if (tipo.contains("pdf"))
			consumeText(bean);
		else if (tipo.contains("video"))
			consumeVideo(bean);
		else
			consumeWeb(bean);
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
		text.setUserId(value.getNickName());
		text.setMetadata(value.getMetadata());
	
		Big1AO.consumeText(text);
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
		web.setMetadata(value.getMetadata());
		web.setTimestamp(timestamp.getTime());
		web.setUserId(value.getNickName());
		web.setUrl(value.getMetadata());
		Big1AO.consumeWeb(web);
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
		audio.setMetadata(value.getMetadata());
		audio.setTimestamp(timestamp.getTime());
		audio.setUserId(value.getNickName());
		Big1AO.consumeAudio(audio);
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
		video.setMetadata(value.getMetadata());
		video.setTimestamp(timestamp.getTime());
		video.setUserId(value.getNickName());
		Big1AO.consumeVideo(video);
	}

}

package upc.bdam.recommender.consumer;

import upc.bdam.recommender.consumer.schema.SchemaAudioBean;
import upc.bdam.recommender.consumer.schema.SchemaTextBean;
import upc.bdam.recommender.consumer.schema.SchemaVideoBean;
import upc.bdam.recommender.consumer.schema.SchemaWebBean;
import upc.bdam.recommender.documentDDBB.dao.DocumentDataAccessManager;

/**
 * Clase intermediaria entre el consumer kafka y el mongo Big1
 * 
 * @author Grupo 9: 
 *           - Antolín Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David Pérez Rodríguez
 *
 */
public class ConsumerManager {

	//única instancia de la clase
	private static ConsumerManager instance = null;

	/**
	 * constructor privado
	 */
	private ConsumerManager() {

	}

	public static final ConsumerManager getInstance() {
		if (instance == null) {
			instance = new ConsumerManager();
			return instance;
		} else
			return instance;
	}

	/**
	 * Transfiere los datos de los ficheros de texto
	 * @param text
	 */
	public void consumeText(SchemaTextBean text) {
		DocumentDataAccessManager.getInstance().consumeText(text);
	}

	/**
	 * Transfiere los datos obtenidos de la web
	 * @param web
	 */
	public void consumeWeb(SchemaVideoBean web) {
		DocumentDataAccessManager.getInstance().consumeWeb(web);
	}

	/**
	 * Transfiere los datos de los ficheros de audio
	 * @param audio
	 */
	public void consumeAudio(SchemaAudioBean audio) {
		DocumentDataAccessManager.getInstance().consumeAudio(audio);
	}

	/**
	 * Transfiere los datos de los ficheros de vídeo.
	 * @param video
	 */
	public void consumeVideo(SchemaWebBean video) {
		DocumentDataAccessManager.getInstance().consumeVideo(video);
	}
}

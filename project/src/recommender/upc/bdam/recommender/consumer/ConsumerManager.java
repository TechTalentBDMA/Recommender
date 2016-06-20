package upc.bdam.recommender.consumer;

import upc.bdam.recommender.documentDDBB.dao.DocumentDataAccessManager;
import upc.bdam.recommender.kafka.KafkaBean;

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
	public void consume(KafkaBean bean) {
		DocumentDataAccessManager.getInstance().consume(bean);
	}

}

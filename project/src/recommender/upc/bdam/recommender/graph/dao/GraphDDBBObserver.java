package upc.bdam.recommender.graph.dao;

import upc.bdam.recommender.graphupdater.schema.Big2SGraphGuardSchema;
import upc.bdam.recommender.kafka.KafkaBean;
import upc.bdam.recommender.ontology.json.IBinding;

/**
 * Interfaz que define los métodos que deben ser atendidos en la BBDD de grafos
 * @author Grupo 9: 
 *           - Antolín Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David Pérez Rodríguez
 *
 */
public interface GraphDDBBObserver {

	/**
	 * Observer para inserciones
	 * @param values
	 */
	public  void insert(IBinding[] values);
	
	/**
	 * Observer para inserciones
	 * @param values
	 */
	public  void insertUser(KafkaBean values);
	
	/**
	 * Método que observa las inserciones en el modelo procedentes de Big2
	 * @param big2Schema
	 */
	public void insertBig2Schema(Big2SGraphGuardSchema big2Schema);

	/**
	 * Observer para actualizaciones
	 * @param values
	 */
	public  void update(IBinding[] values);

	/**
	 * Observer para borrados
	 * @param values
	 */
	public  void delete(IBinding[] values);

}
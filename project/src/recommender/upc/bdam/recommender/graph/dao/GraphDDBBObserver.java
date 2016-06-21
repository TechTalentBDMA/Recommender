package upc.bdam.recommender.graph.dao;

import upc.bdam.recommender.kafka.KafkaBean;
import upc.bdam.recommender.ontology.json.IBinding;

/**
 * Interfaz que define los m�todos que deben ser atendidos en la BBDD de grafos
 * @author Grupo 9: 
 *           - Antol�n Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David P�rez Rodr�guez
 *
 */
public abstract class GraphDDBBObserver {

	/**
	 * Observer para inserciones
	 * @param values
	 */
	public abstract void insert(IBinding[] values);
	
	/**
	 * Observer para inserciones
	 * @param values
	 */
	public abstract void insertUser(KafkaBean values);

	/**
	 * Observer para actualizaciones
	 * @param values
	 */
	public abstract void update(IBinding[] values);

	/**
	 * Observer para borrados
	 * @param values
	 */
	public abstract void delete(IBinding[] values);

}
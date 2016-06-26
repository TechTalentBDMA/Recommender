package upc.bdam.recommender.documentDDBB.dao;

import upc.bdam.recommender.graph.dao.GraphDDBBObserver;
import upc.bdam.recommender.graphupdater.schema.Big2SGraphGuardSchema;
import upc.bdam.recommender.kafka.KafkaBean;
import upc.bdam.recommender.ontology.json.IBinding;
import upc.bdam.recommender.ontology.json.author.Author;
import upc.bdam.recommender.ontology.json.band.Band;
import upc.bdam.recommender.ontology.json.genre.Genre;

/**
 * Observer de las acciones realizadas en la BBDD ontológica. Sirve para
 * insertar en el Mongo de persistencia la misma información que en la BBDD
 * gráfica, a modo de backup
 * 
 * @author Grupo 9: - Antolín Barrena Rico - Carles Castillejo - Raffaele
 *         Ghermandi - David Pérez Rodríguez
 *
 */
public class ReplicaDDBBGraphObserver implements GraphDDBBObserver {

	/**
	 * Se inserta en la BBBDD de persistencia los valores de persona y obra
	 */
	public void insert(IBinding[] values) {
		try {
			if (values[0] instanceof Author)
				ReplicaDataAccessManager.getInstance().insert(ReplicaDataAccessManager.DOCUMENT_PERSON_INSERT,
						values);
			else if (values[0] instanceof Band)
				ReplicaDataAccessManager.getInstance().insert(ReplicaDataAccessManager.DOCUMENT_BAND_INSERT, values);
			else if (values[0] instanceof Genre)
				ReplicaDataAccessManager.getInstance().insert(ReplicaDataAccessManager.DOCUMENT_GENRE_INSERT, values);
			else
				ReplicaDataAccessManager.getInstance().insert(ReplicaDataAccessManager.DOCUMENT_ARTWORK_INSERT,
						values);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Se inserta en la BBBDD de persistencia los valores de persona y obra
	 */
	public void insertUser(KafkaBean values) {
		try {
			ReplicaDataAccessManager.getInstance().updateModel(values);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void insertBig2Schema(Big2SGraphGuardSchema big2Schema){
		ReplicaDataAccessManager.getInstance().updateModel(big2Schema);
	}

	
	
	public void update(IBinding[] values) {
		// TODO Auto-generated method stub

	}

	public void delete(IBinding[] values) {
		// TODO Auto-generated method stub

	}
}

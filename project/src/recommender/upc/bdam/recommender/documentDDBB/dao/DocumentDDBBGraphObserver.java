package upc.bdam.recommender.documentDDBB.dao;

import upc.bdam.recommender.graph.dao.GraphDDBBObserver;
import upc.bdam.recommender.ontology.json.IBinding;
import upc.bdam.recommender.ontology.json.author.Author;

/**
 * Observer de las acciones realizadas en la BBDD ontológica. Sirve para insertar en el Mongo de persistencia la misma 
 * información que en la BBDD gráfica, a modo de backup
 * 
 * @author Grupo 9: 
 *           - Antolín Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David Pérez Rodríguez
 *
 */
public class DocumentDDBBGraphObserver extends GraphDDBBObserver{

	/**
	 * Se inserta en la BBBDD de persistencia los valores de persona y obra
	 */
	public void insert(IBinding[] values){
		try {
			if (values[0] instanceof Author)
				DocumentDataAccessManager.getInstance().insert(DocumentDataAccessManager.DOCUMENT_PERSON_INSERT, values);
			else
				DocumentDataAccessManager.getInstance().insert(DocumentDataAccessManager.DOCUMENT_ARTWORK_INSERT, values);
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(IBinding[] values) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(IBinding[] values) {
		// TODO Auto-generated method stub
		
	}
}

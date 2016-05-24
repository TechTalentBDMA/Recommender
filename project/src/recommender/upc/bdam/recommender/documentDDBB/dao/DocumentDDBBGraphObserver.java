package upc.bdam.recommender.documentDDBB.dao;

import upc.bdam.recommender.graph.dao.GraphDDBBObserver;
import upc.bdam.recommender.ontology.json.IBinding;
import upc.bdam.recommender.ontology.json.author.Author;

public class DocumentDDBBGraphObserver extends GraphDDBBObserver{

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

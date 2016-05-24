package upc.bdam.recommender.documentDDBB.dao;

import java.util.ArrayList;
import java.util.List;

import javax.management.relation.RelationType;

import org.neo4j.graphdb.Label;

import upc.bdam.recommender.graph.dao.GraphDDBBObserver;
import upc.bdam.recommender.graph.dao.GraphDataAccessManager;
import upc.bdam.recommender.graph.dao.GraphDataAccessObject;
import upc.bdam.recommender.ontology.json.IBinding;

public class DocumentDataAccessManager {

	private List<GraphDDBBObserver> observers = new ArrayList<GraphDDBBObserver>();

	public static final byte DOCUMENT_PERSON_INSERT = 1;
	public static final byte DOCUMENT_ARTWORK_INSERT = 2;

	public static final byte DOCUMENT_PERSON_UPDATE = 3;
	public static final byte DOCUMENT_ARTWORK_UPDATE = 4;

	public static final byte DOCUMENT_PERSON_DELETE = 5;
	public static final byte DOCUMENT_ARTWORK_DELETE = 6;

	private static DocumentDataAccessManager instance = null;
	private DocumentDataAccessObject accessObject = new DocumentDataAccessObject();

	private DocumentDataAccessManager() {

	}

	public static final DocumentDataAccessManager getInstance() {
		if (instance != null)
			return instance;
		else {
			instance = new DocumentDataAccessManager();
			return instance;
		}
	}

	public void insert(byte query, IBinding[] values) throws Exception {
		switch (query) {
		case DOCUMENT_PERSON_INSERT:
			insertPersonDocument(values);
			break;
		case DOCUMENT_ARTWORK_INSERT:
			insertArtWorkDocument(values);
			break;

		default:
			break;
		}
	}

	private void insertPersonDocument(IBinding[] values) {

		accessObject.insertPersonDocument(values);

	}

	private void insertArtWorkDocument(IBinding[] values) {
		accessObject.insertArtWorkDocument(values);
	}
}

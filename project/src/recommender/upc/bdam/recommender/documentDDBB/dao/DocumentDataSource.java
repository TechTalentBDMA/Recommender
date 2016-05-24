package upc.bdam.recommender.documentDDBB.dao;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import upc.bdam.recommender.documentDDBB.dao.json.ArtWork;
import upc.bdam.recommender.documentDDBB.dao.json.Person;

public class DocumentDataSource {

	public static final String PERSON_ID = "id";
	public static final String PERSON_BORN = "born";
	public static final String PERSON_NAME = "name";

	public static final String ARTWORK_ID = "id";
	public static final String ARTWORK_GENRE = "genre";
	public static final String ARTWORK_RELEASED = "released";
	public static final String ARTWORK_TITLE = "title";

	private MongoCollection<Document> personCollection;
	private MongoCollection<Document> artworkCollection;

	private MongoClient client;
	private MongoDatabase database;

	public DocumentDataSource() {
		client = new MongoClient();

		// se crea la base de datos del ejercicio y sus colecciones
		database = client.getDatabase("wikiDataCollection");

		personCollection = database.getCollection(DocumentDataAccessObject.PERSON);
		artworkCollection = database.getCollection(DocumentDataAccessObject.ARTWORK);
	}

	public void insertPersonDocument(Person value) {
		Document person = new Document();
		person.put(PERSON_ID, value.getId());
		person.put(PERSON_BORN, value.getBorn());
		person.put(PERSON_NAME, value.getName());
		personCollection.insertOne(person);
	}

	public void insertCollectionArtWork(ArtWork value) {
		Document artwork = new Document();
		artwork.put(ARTWORK_ID, value.getId());
		artwork.put(ARTWORK_GENRE, value.getGenre());
		artwork.put(ARTWORK_RELEASED, value.getReleased());
		artwork.put(ARTWORK_TITLE, value.getTitle());
		artworkCollection.insertOne(artwork);
	}

}

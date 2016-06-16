package upc.bdam.recommender.documentDDBB.dao;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import upc.bdam.recommender.documentDDBB.dao.json.ArtWork;
import upc.bdam.recommender.documentDDBB.dao.json.Person;

/**
 * Clase para inserci�n en la BBDD de persistencia, de los datos recabados en la BBDD ontol�gica
 * @author Grupo 9: 
 *           - Antol�n Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David P�rez Rodr�guez
 *
 */
public class DocumentDataSource {

	//Declaraci�n de los atributos de los documentos persona y artwork
	public static final String PERSON_ID = "id";
	public static final String PERSON_BORN = "born";
	public static final String PERSON_NAME = "name";

	public static final String ARTWORK_ID = "id";
	public static final String ARTWORK_GENRE = "genre";
	public static final String ARTWORK_RELEASED = "released";
	public static final String ARTWORK_TITLE = "title";

	//bloque de declaraci�n de variables.
	private MongoCollection<Document> personCollection;
	private MongoCollection<Document> artworkCollection;
	private MongoClient client;
	private MongoDatabase database;

	/**
	 * Construc
	 */
	public DocumentDataSource() {
		init();
	}
	
	/**
	 * Inicializaci�n del acceso a la BBDD
	 */
	private void init(){
		client = new MongoClient();

		// se crea la base de datos del ejercicio y sus colecciones
		database = client.getDatabase("wikiDataCollection");

		personCollection = database.getCollection(DocumentDataAccessObject.PERSON);
		artworkCollection = database.getCollection(DocumentDataAccessObject.ARTWORK);
	
	}

	
	/**
	 * Inserci�n del documento persona
	 * @param value
	 */
	public void insertPersonDocument(Person value) {
		Document person = new Document();
		person.put(PERSON_ID, value.getId());
		person.put(PERSON_BORN, value.getBorn());
		person.put(PERSON_NAME, value.getName());
		personCollection.insertOne(person);
	}

	/**
	 * Inserci�n del docuemnto "obra"
	 * @param value
	 */
	public void insertCollectionArtWork(ArtWork value) {
		Document artwork = new Document();
		artwork.put(ARTWORK_ID, value.getId());
		artwork.put(ARTWORK_GENRE, value.getGenre());
		artwork.put(ARTWORK_RELEASED, value.getReleased());
		artwork.put(ARTWORK_TITLE, value.getTitle());
		artworkCollection.insertOne(artwork);
	}
}

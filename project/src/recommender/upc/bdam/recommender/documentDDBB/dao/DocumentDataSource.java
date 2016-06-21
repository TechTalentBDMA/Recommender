package upc.bdam.recommender.documentDDBB.dao;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import upc.bdam.recommender.documentDDBB.dao.json.ArtWork;
import upc.bdam.recommender.documentDDBB.dao.json.Band;
import upc.bdam.recommender.documentDDBB.dao.json.Genre;
import upc.bdam.recommender.documentDDBB.dao.json.Person;
import upc.bdam.recommender.kafka.KafkaBean;

/**
 * Clase para inserción en la BBDD de persistencia, de los datos recabados en la BBDD ontológica
 * @author Grupo 9: 
 *           - Antolín Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David Pérez Rodríguez
 *
 */
public class DocumentDataSource {

	//Declaración de los atributos de los documentos persona, artwork, generos y grupos musicales
	public static final String PERSON_ID = "id";
	public static final String PERSON_BORN = "born";
	public static final String PERSON_NAME = "name";

	public static final String ARTWORK_ID = "id";
	public static final String ARTWORK_GENRE = "genre";
	public static final String ARTWORK_RELEASED = "released";
	public static final String ARTWORK_TITLE = "title";
	
	public static final String BAND_ID = "id";
	public static final String BAND_RELEASED = "released";
	public static final String BAND_NAME = "name";
	
	public static final String GENRE_ID = "id";
	public static final String GENRE_NAME = "name";
	
	public static final String USER_NICK_NAME="nickName";
	public static final String USER_NICK="name";
	public static final String USER_SURNAME="surname";
	public static final String USER_BORN="born";	
	public static final String USER_SEX="sex";
	public static final String USER_PROFESSION="profession";
	public static final String USER_HOBBY="hobby";
	public static final String USER_INTERESTED_IN="interestedIn";

	//bloque de declaración de variables.
	private MongoCollection<Document> personCollection;
	private MongoCollection<Document> artworkCollection;
	private MongoCollection<Document> bandCollection;
	private MongoCollection<Document> genreCollection;
	private MongoCollection<Document> userCollection;
	private MongoClient client;
	private MongoDatabase database;

	/**
	 * Construc
	 */
	public DocumentDataSource() {
		init();
	}
	
	/**
	 * Inicialización del acceso a la BBDD
	 */
	private void init(){
		client = new MongoClient();

		// se crea la base de datos del ejercicio y sus colecciones
		database = client.getDatabase("wikiDataCollection");

		personCollection = database.getCollection(DocumentDataAccessObject.PERSON);
		artworkCollection = database.getCollection(DocumentDataAccessObject.ARTWORK);
		bandCollection = database.getCollection(DocumentDataAccessObject.BAND);
		genreCollection = database.getCollection(DocumentDataAccessObject.GENRE);
		userCollection = database.getCollection(DocumentDataAccessObject.USER);
	
	}

	
	/**
	 * Inserción del documento persona
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
	 * Inserción del docuemnto "obra"
	 * @param value
	 */
	public void insertArtWorkDocument(ArtWork value) {
		Document artwork = new Document();
		artwork.put(ARTWORK_ID, value.getId());
		artwork.put(ARTWORK_GENRE, value.getGenre());
		artwork.put(ARTWORK_RELEASED, value.getReleased());
		artwork.put(ARTWORK_TITLE, value.getTitle());
		artworkCollection.insertOne(artwork);
	}
	
	/**
	 * Inserción del documento band
	 * @param value
	 */
	public void insertBandDocument(Band value) {
		Document band = new Document();
		band.put(BAND_ID, value.getId());
		band.put(BAND_RELEASED, value.getReleased());
		band.put(BAND_NAME, value.getName());
		bandCollection.insertOne(band);
	}
	
	/**
	 * Inserción del documento género
	 * @param value
	 */
	public void insertGenreDocument(Genre value) {
		Document genre = new Document();
		genre.put(GENRE_ID, value.getId());
		genre.put(GENRE_NAME, value.getName());
		genreCollection.insertOne(genre);
	}
	
	/**
	 * Inserción del documento género
	 * @param value
	 */
	public void insertUserDocument(KafkaBean value) {
		Document user = new Document();
		user.put(USER_NICK_NAME, value.getNickName());
		user.put(USER_NICK, value.getName());
		user.put(USER_SURNAME, value.getSurname());
		user.put(USER_BORN, value.getBorn());
		user.put(USER_SEX, value.getSex());
		user.put(USER_PROFESSION, value.getProfession());
		user.put(USER_HOBBY, value.getHobby());
		user.put(USER_INTERESTED_IN, value.getInterestedIn());
		userCollection.insertOne(user);
	}

}

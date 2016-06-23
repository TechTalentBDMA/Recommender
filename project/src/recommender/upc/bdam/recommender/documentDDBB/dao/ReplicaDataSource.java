package upc.bdam.recommender.documentDDBB.dao;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import upc.bdam.recommender.documentDDBB.dao.json.ArtWork;
import upc.bdam.recommender.documentDDBB.dao.json.Band;
import upc.bdam.recommender.documentDDBB.dao.json.Genre;
import upc.bdam.recommender.documentDDBB.dao.json.Person;
import upc.bdam.recommender.graphupdater.schema.Big2AudioSchemaBean;
import upc.bdam.recommender.graphupdater.schema.Big2TextSchemaBean;
import upc.bdam.recommender.graphupdater.schema.Big2VideoSchemaBean;
import upc.bdam.recommender.graphupdater.schema.Big2WebSchemaBean;
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
public class ReplicaDataSource {

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
	
	//atributos del nodo usuario
	public static final String USER_NICK_NAME="nickName";
	public static final String USER_NICK="name";
	public static final String USER_SURNAME="surname";
	public static final String USER_BORN="born";	
	public static final String USER_SEX="sex";
	public static final String USER_PROFESSION="profession";
	public static final String USER_HOBBY="hobby";
	public static final String USER_INTERESTED_IN="interestedIn";
	
	// declaración de constantes para la inserción de los campos procedentes de
	// Big2
	private static String TEXT_ANALYTICS_TITULO = "titulo";
	private static String TEXT_ANALYTICS_ALBUM = "album";
	private static String TEXT_ANALYTICS_CLASIFICACION = "clasificación";
	private static String TEXT_ANALYTICS_INTERPRETE = "interprete";
	private static String TEXT_ANALYTICS_COLABORADORES = "colaboradores";
	private static String TEXT_ANALYTICS_YEAR = "year";
	private static String TEXT_ANALYTICS_GENEROS = "generos";
	private static String TEXT_ANALYTICS_COMPOSITORES = "compositores";
	private static String TEXT_ANALYTICS_AUTOR = "autor";
	private static String TEXT_ANALYTICS_IDIOMA = "idioma";
	private static String TEXT_ANALYTICS_ISBN = "isbn";
	private static String TEXT_ANALYTICS_CREATOR = "creator";
	private static String TEXT_ANALYTICS_SUBJECT = "subject";
	private static String TEXT_ANALYTICS_TYPE = "type";
	private static String TEXT_ANALYTICS_ACTORES = "ACTORES";
	private static String TEXT_ANALYTICS_DIRECTOR = "DIRECTOR";
	private static String TEXT_ANALYTICS_PRODUCTOR = "productor";
	private static String TEXT_ANALYTICS_GUIONISTA = "guionista";
	private static String TEXT_ANALYTICS_NOMBRES = "nombres";
	private static String TEXT_ANALYTICS_TEMAS = "temas";
	private static String TEXT_ANALYTICS_CLAVES = "claves";
	private static String TEXT_ANALYTICS_URI = "uri";
	private static String TEXT_ANALYTICS_DESCRIPCION = "descripcion";
	private static String TEXT_ANALYTICS_TIMESTAMP_ID="id";
	private static String TEXT_ANALYTICS_USER_ID="userId";

	//bloque de declaración de variables.
	private MongoCollection<Document> personCollection;
	private MongoCollection<Document> artworkCollection;
	private MongoCollection<Document> bandCollection;
	private MongoCollection<Document> genreCollection;
	private MongoCollection<Document> userCollection;
	private MongoCollection<Document> textCollection;
	private MongoCollection<Document> webCollection;
	private MongoCollection<Document> audioCollection;
	private MongoCollection<Document> videoCollection;
	private MongoClient client;
	private MongoDatabase database;

	/**
	 * Construc
	 */
	public ReplicaDataSource() {
		init();
	}
	
	/**
	 * Inicialización del acceso a la BBDD
	 */
	private void init(){
		client = new MongoClient();

		// se crea la base de datos del ejercicio y sus colecciones
		database = client.getDatabase("wikiDataCollection");

		personCollection = database.getCollection(ReplicaDataAccessObject.PERSON);
		artworkCollection = database.getCollection(ReplicaDataAccessObject.ARTWORK);
		bandCollection = database.getCollection(ReplicaDataAccessObject.BAND);
		genreCollection = database.getCollection(ReplicaDataAccessObject.GENRE);
		userCollection = database.getCollection(ReplicaDataAccessObject.USER);
		
		textCollection = database.getCollection(ReplicaDataAccessObject.PERSON);
		webCollection = database.getCollection(ReplicaDataAccessObject.ARTWORK);
		audioCollection = database.getCollection(ReplicaDataAccessObject.BAND);
		videoCollection = database.getCollection(ReplicaDataAccessObject.GENRE);
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
	
	/**
	 * Replica el contenido de audio insertado en la BBDD de conocimiento desde
	 * Big2
	 * 
	 * @param web
	 */
	public void updateAudio(Big2AudioSchemaBean value) {
		Document audio = new Document();

		audio.put(TEXT_ANALYTICS_ALBUM, value.getAlbum());
		audio.put(TEXT_ANALYTICS_CLASIFICACION, value.getClasificacion());
		audio.put(TEXT_ANALYTICS_COLABORADORES, value.getColaboradores());
		audio.put(TEXT_ANALYTICS_COMPOSITORES, value.getCompositores());
		audio.put(TEXT_ANALYTICS_GENEROS, value.getGeneros());
		audio.put(TEXT_ANALYTICS_TIMESTAMP_ID, value.getId());
		audio.put(TEXT_ANALYTICS_INTERPRETE, value.getInterprete());
		audio.put(TEXT_ANALYTICS_TITULO, value.getTitulo());
		audio.put(TEXT_ANALYTICS_USER_ID, value.getUserId());
		audio.put(TEXT_ANALYTICS_YEAR, value.getYear());

		audioCollection.insertOne(audio);
	}

	/**
	 * Replica el contenido de texto insertado en la BBDD de conocimiento desde
	 * Big2
	 * 
	 * @param web
	 */
	public void updateText(Big2TextSchemaBean value) {
		Document text = new Document();

		text.put(TEXT_ANALYTICS_AUTOR, value.getAutor());
		text.put(TEXT_ANALYTICS_CREATOR, value.getCreator());
		text.put(TEXT_ANALYTICS_TIMESTAMP_ID, value.getId());
		text.put(TEXT_ANALYTICS_IDIOMA, value.getIdioma());
		text.put(TEXT_ANALYTICS_ISBN, value.getIsbn());
		text.put(TEXT_ANALYTICS_SUBJECT, value.getSubject());
		text.put(TEXT_ANALYTICS_TITULO, value.getTitulo());
		text.put(TEXT_ANALYTICS_TYPE, value.getType());
		text.put(TEXT_ANALYTICS_USER_ID, value.getUserId());
		text.put(TEXT_ANALYTICS_YEAR, value.getYear());

		textCollection.insertOne(text);
	}

	/**
	 * Replica el contenido de video insertado en la BBDD de conocimiento desde
	 * Big2
	 * 
	 * @param web
	 */
	public void updateVideo(Big2VideoSchemaBean value) {
		Document video = new Document();

		video.put(TEXT_ANALYTICS_ACTORES, value.getActores());
		video.put(TEXT_ANALYTICS_CLASIFICACION, value.getClasificacion());
		video.put(TEXT_ANALYTICS_DIRECTOR, value.getDirector());
		video.put(TEXT_ANALYTICS_GENEROS, value.getGeneros());
		video.put(TEXT_ANALYTICS_GUIONISTA, value.getGuionista());
		video.put(TEXT_ANALYTICS_USER_ID, value.getId());
		video.put(TEXT_ANALYTICS_PRODUCTOR, value.getProductor());
		video.put(TEXT_ANALYTICS_TITULO, value.getTitulo());
		video.put(TEXT_ANALYTICS_USER_ID, value.getUserId());
		video.put(TEXT_ANALYTICS_YEAR, value.getYear());

		videoCollection.insertOne(video);
	}
	/**
	 * Replica el contenido web insertado en la BBDD de conocimiento desde Big2
	 * 
	 * @param web
	 */
	public void updateWeb(Big2WebSchemaBean value) {
		Document web = new Document();

		web.put(TEXT_ANALYTICS_TIMESTAMP_ID, value.getId());
		web.put(TEXT_ANALYTICS_CLAVES, value.getClaves());
		web.put(TEXT_ANALYTICS_DESCRIPCION, value.getDescripcion());
		web.put(TEXT_ANALYTICS_GENEROS, value.getGeneros());
		web.put(TEXT_ANALYTICS_NOMBRES, value.getNombres());
		web.put(TEXT_ANALYTICS_TEMAS, value.getTemas());
		web.put(TEXT_ANALYTICS_URI, value.getUri());
		web.put(TEXT_ANALYTICS_TITULO, value.getTitulo());
		web.put(TEXT_ANALYTICS_USER_ID, value.getUserId());
		web.put(TEXT_ANALYTICS_URI, value.getUri());

		webCollection.insertOne(web);
	}

}

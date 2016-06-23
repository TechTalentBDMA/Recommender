package upc.bdam.recommender.graph.dao;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.graphdb.RelationshipType;

import upc.bdam.recommender.graph.dao.GraphDataAccessObject.ArtWorkNodeSubType;
import upc.bdam.recommender.graph.dao.GraphDataAccessObject.GenreRelationType;
import upc.bdam.recommender.graph.dao.GraphDataAccessObject.NodeType;
import upc.bdam.recommender.graph.dao.GraphDataAccessObject.PersonNodeSubType;
import upc.bdam.recommender.graph.dao.GraphDataAccessObject.PersonRelationType;
import upc.bdam.recommender.graphupdater.schema.Big2AudioSchemaBean;
import upc.bdam.recommender.graphupdater.schema.Big2SGraphGuardSchema;
import upc.bdam.recommender.graphupdater.schema.Big2TextSchemaBean;
import upc.bdam.recommender.graphupdater.schema.Big2VideoSchemaBean;
import upc.bdam.recommender.graphupdater.schema.Big2WebSchemaBean;
import upc.bdam.recommender.kafka.KafkaBean;
import upc.bdam.recommender.ontology.json.IBinding;
import upc.bdam.recommender.wikiData.OntologyDataAccessObject;

/**
 * Gestor de acceso a la BBDD de conocimiento (grafos en Neo4j)
 * @author Grupo 9: 
 *           - Antolín Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David Pérez Rodríguez
 *
 */
public class GraphDataAccessManager {
	//declaración del observer que dispara la inserción en la BBDD répclica MongoDB
	private List<GraphDDBBObserver> observers = new ArrayList<GraphDDBBObserver>();

	//declarción de las constantes de identificación de los mecanismos de acceso
	public static final byte GRAPH_WRITER_INSERT = 1;
	public static final byte GRAPH_BOOK_INSERT = 2;
	public static final byte GRAPH_WRITER_BOOK_RELATION_INSERT = 3;

	public static final byte GRAPH_ACTOR_INSERT = 4;
	public static final byte GRAPH_DIRECTOR_INSERT = 5;
	public static final byte GRAPH_FILM_INSERT = 6;
	public static final byte GRAPH_ACTOR_FILM_RELATION_INSERT = 7;
	public static final byte GRAPH_DIRECTOR_FILM_RELATION_INSERT = 8;

	public static final byte GRAPH_BAND_INSERT = 9;
	public static final byte GRAPH_MUSICIAN_INSERT = 10;
	public static final byte GRAPH_SONG_INSERT = 11;
	public static final byte GRAPH_BAND_MEMBER_RELATION_INSERT = 12;
	public static final byte GRAPH_SONG_BAND_RELATION_INSERT = 13;
	public static final byte GRAPH_SONG_PERFORMER_RELATION_INSERT = 14;
	
	public static final byte GRAPH_BOOK_GENRE_RELATION_INSERT = 15;
	public static final byte GRAPH_FILM_GENRE_RELATION_INSERT = 16;
	public static final byte GRAPH_BAND_GENRE_RELATION_INSERT = 17;
	public static final byte GRAPH_SONG_GENRE_RELATION_INSERT = 18;

	public static final byte GRAPH_GENRE_INSERT = 19;

	//declaración de las variables de clase.
	private static GraphDataAccessManager instance = null;
	private GraphDataAccessObject accessObject = new GraphDataAccessObject();

	/**
	 * constructor privado para la implementación del patrón observer.
	 */
	private GraphDataAccessManager() {

	}

	/**
	 * Método estático de acceso a la instancia de clase
	 * @return
	 */
	public static final GraphDataAccessManager getInstance() {
		if (instance != null)
			return instance;
		else {
			instance = new GraphDataAccessManager();
			return instance;
		}
	}

	/**
	 * Interfaz de inserción genérica que recibe el tipo de inserció que se va a hacer.
	 * @param query
	 * @param values
	 * @throws Exception
	 */
	public void insert(byte query, IBinding[] values) throws Exception {
		switch (query) {
		case GRAPH_WRITER_INSERT:
			insertPersonNode(values, NodeType.Person, PersonNodeSubType.Writer);
			break;
		case GRAPH_BOOK_INSERT:
			insertArtWorkNode(values, NodeType.ArtWork, ArtWorkNodeSubType.Book);
			break;

		case GRAPH_WRITER_BOOK_RELATION_INSERT:
			insertRelation(values, PersonRelationType.WriterWroteBook, NodeType.Person, NodeType.ArtWork);
			break;

		case GRAPH_ACTOR_INSERT:
			insertPersonNode(values, NodeType.Person, PersonNodeSubType.Actor);
			break;

		case GRAPH_DIRECTOR_INSERT:
			insertPersonNode(values, NodeType.Person, PersonNodeSubType.Director);
			break;

		case GRAPH_FILM_INSERT:
			insertArtWorkNode(values, NodeType.ArtWork, ArtWorkNodeSubType.Film);
			break;

		case GRAPH_ACTOR_FILM_RELATION_INSERT:
			insertRelation(values, PersonRelationType.ActorActedFilm, NodeType.Person,NodeType.ArtWork);
			break;

		case GRAPH_DIRECTOR_FILM_RELATION_INSERT:
			insertRelation(values, PersonRelationType.DirectorDirectedFilm, NodeType.Person, NodeType.ArtWork);
			break;

		case GRAPH_MUSICIAN_INSERT:
			insertPersonNode(values, NodeType.Person, PersonNodeSubType.Musician);
			break;

		case GRAPH_BAND_INSERT:
			insertBandNode(values, NodeType.Band);
			break;

		case GRAPH_SONG_INSERT:
			insertArtWorkNode(values, NodeType.ArtWork, ArtWorkNodeSubType.Song);
			break;

		case GRAPH_BAND_MEMBER_RELATION_INSERT:
			insertRelation(values, PersonRelationType.MusicianMemberOfBand, NodeType.Person, NodeType.Band);
			break;

		case GRAPH_SONG_BAND_RELATION_INSERT:
			insertRelation(values, PersonRelationType.BandPlayedSong,NodeType.Band, NodeType.ArtWork);
			break;

		case GRAPH_SONG_PERFORMER_RELATION_INSERT:
			insertRelation(values, PersonRelationType.MusicianPlayedSong,NodeType.Person, NodeType.ArtWork);
			break;

		case GRAPH_BOOK_GENRE_RELATION_INSERT:
			insertRelation(values, GenreRelationType.BookGenre,NodeType.ArtWork, NodeType.Genre);
			break;

		case GRAPH_FILM_GENRE_RELATION_INSERT:
			insertRelation(values, GenreRelationType.FilmGenre,NodeType.ArtWork, NodeType.Genre);
			break;

		case GRAPH_BAND_GENRE_RELATION_INSERT:
			insertRelation(values, GenreRelationType.BandGenre,NodeType.Band, NodeType.Genre);
			break;

		case GRAPH_SONG_GENRE_RELATION_INSERT:
			insertRelation(values, GenreRelationType.SongGenre,NodeType.ArtWork, NodeType.Genre);
			break;
		case GRAPH_GENRE_INSERT:
			insertGenreNode(values, NodeType.Genre);
			break;

		default:
			break;
		}
	}

	/**
	 * Método especializado en la inserción de nodos de género
	 * @param values
	 * @param type
	 */
	private void insertGenreNode(IBinding[] values, NodeType type) {
		accessObject.insertGenreNode(values, type);
	}

	/**
	 * Método especializado en la inserción de nodos persona
	 * @param values
	 * @param type
	 */
	private void insertPersonNode(IBinding[] values, NodeType type, PersonNodeSubType subtype) {
		accessObject.insertPersonNode(values, type, subtype);
	}

	/**
	 * Método especializado en la inserción de nodos de grupos musicales
	 * @param values
	 * @param type
	 */
	private void insertBandNode(IBinding[] values, NodeType type) {
		accessObject.insertBandNode(values, type);
	}

	/**
	 * Método especializado en la inserción de nodos de trabajos artísticos
	 * @param values
	 * @param type
	 */
	private void insertArtWorkNode(IBinding[] values, NodeType type, ArtWorkNodeSubType subtype) {
		accessObject.insertArtWorkNode(values, type, subtype);
	}

	/**
	 * Método especializado en la inserción de relaciones
	 * @param values
	 * @param type
	 */
	private void insertRelation(IBinding[] values, RelationshipType relationType, NodeType personType, NodeType BandType) {
		accessObject.insertRelation(values, relationType, personType, BandType);
	}

	/**
	 * Método que permite asociar la clase observer
	 * @param observer
	 */
	public void attach(GraphDDBBObserver observer) {
		observers.add(observer);
	}

	/**
	 * Metodo mediante el cual se notifica a los observers de que se produce el evento que observan
	 * @param values
	 */
	public void notifyAllObservers(IBinding[] values) {
		for (GraphDDBBObserver observer : observers) {
			observer.insert(values);
		}
	}
	
	/**
	 * Metodo mediante el cual se notifica a los observers de que se produce el evento que observan
	 * @param values
	 */
	public void notifyAllObservers(KafkaBean values) {
		for (GraphDDBBObserver observer : observers) {
			observer.insertUser(values);
		}
	}
	
	/**
	 * Metodo mediante el cual se notifica a los observers de que se produce el evento que observan
	 * @param values
	 */
	public void notifyAllObservers(Big2SGraphGuardSchema values) {
		for (GraphDDBBObserver observer : observers) {
			observer.insertBig2Schema(values);
		}
	}
	
	/**
	 * Obtienen los datos de canciones de wikidata y los inserta en la BBDD de
	 * grafos.
	 * 
	 * @throws Exception
	 */
	public void getSongs() throws Exception{
		OntologyDataAccessObject dao=new OntologyDataAccessObject();
		IBinding[] value;
		value=dao.getBands();
		insert(GraphDataAccessManager.GRAPH_BAND_INSERT, value);
		notifyAllObservers(value);
		value=dao.getMusician();
		insert(GraphDataAccessManager.GRAPH_MUSICIAN_INSERT, value);
		notifyAllObservers(value);
		value=dao.getSongs();
		insert(GraphDataAccessManager.GRAPH_SONG_INSERT, value);
		notifyAllObservers(value);
	}
	
	/**
	 * Inserta las relaciones del ámbito musical
	 */
	public void getMusicRelations()throws Exception{
		OntologyDataAccessObject dao=new OntologyDataAccessObject();
		IBinding[] value;
		value=dao.getBandMemberRelation();
		insert(GraphDataAccessManager.GRAPH_BAND_MEMBER_RELATION_INSERT, value);
		value=dao.getSongBandRelation();
		insert(GraphDataAccessManager.GRAPH_SONG_BAND_RELATION_INSERT, value);
		value=dao.getSongPerformerRelation();
		insert(GraphDataAccessManager.GRAPH_SONG_PERFORMER_RELATION_INSERT, value);		
		value=dao.getBandGenreRelation();
		insert(GraphDataAccessManager.GRAPH_BAND_GENRE_RELATION_INSERT, value);
		value=dao.getSongGenreRelation();
		insert(GraphDataAccessManager.GRAPH_SONG_GENRE_RELATION_INSERT, value);
	}
	
	/**
	 * Obtienen los datos de libros de wikidata y los inserta en la BBDD de
	 * grafos.
	 * 
	 * @throws Exception
	 */
	public void getBooks() throws Exception{
		OntologyDataAccessObject dao=new OntologyDataAccessObject();
		IBinding[] value;
		value=dao.getBooks();
		insert(GraphDataAccessManager.GRAPH_BOOK_INSERT, value);
		notifyAllObservers(value);
		value=dao.getWriters();
		insert(GraphDataAccessManager.GRAPH_WRITER_INSERT, value);
		notifyAllObservers(value);

	}
	
	/**
	 * Inserta en BBDD las relaciones correspondientes a los books
	 * @throws Exception
	 */
	public void getBookRelations() throws Exception{
		OntologyDataAccessObject dao=new OntologyDataAccessObject();
		IBinding[] value;
		value=dao.getWriterBookRelation();
		insert(GraphDataAccessManager.GRAPH_WRITER_BOOK_RELATION_INSERT, value);
		value=dao.getBookGenreRelation();
		insert(GraphDataAccessManager.GRAPH_BOOK_GENRE_RELATION_INSERT, value);
	}
	/**
	 * Obtienen los datos de películas de wikidata y los inserta en la BBDD de
	 * grafos.
	 * 
	 * @throws Exception
	 */
	public void getFilms() throws Exception{
		OntologyDataAccessObject dao=new OntologyDataAccessObject();
		IBinding[] value;
		value=dao.getActors();
		insert(GraphDataAccessManager.GRAPH_ACTOR_INSERT, value);
		notifyAllObservers(value);
		value=dao.getDirector();
		insert(GraphDataAccessManager.GRAPH_DIRECTOR_INSERT, value);
		notifyAllObservers(value);
		value=dao.getFilms();
		insert(GraphDataAccessManager.GRAPH_FILM_INSERT, value);
		notifyAllObservers(value);
	}
	
	/**
	 * Se inserta en BBDD las relaciones correspondientes a los films
	 */
	public void getFilmsRelations()throws Exception{
		OntologyDataAccessObject dao=new OntologyDataAccessObject();
		IBinding[] value;
		value=dao.getActorFilmRelation();
		insert(GraphDataAccessManager.GRAPH_ACTOR_FILM_RELATION_INSERT, value);
		value=dao.getDirectorFilmRelation();
		insert(GraphDataAccessManager.GRAPH_DIRECTOR_FILM_RELATION_INSERT, value);
	}
	
	/**
	 * Obtienen los géneros de los medios analizados
	 * @throws Exception
	 */
	public void getGenres() throws Exception{
		OntologyDataAccessObject dao=new OntologyDataAccessObject();
		IBinding[] value;
		value=dao.getGenres();
		insert(GraphDataAccessManager.GRAPH_GENRE_INSERT, value);
		notifyAllObservers(value);
	}
	
	/**
	 * Se inserta el usuario procedente del agente
	 * @param value
	 */
	public void insertUserNode(KafkaBean value){
		accessObject.insertUserNode(value);
		notifyAllObservers(value);
	}
	
	/**
	 * Identific el tipo de documento procedente de Big2 que hay que insertar en la BBDD de conocimiento
	 * @param value
	 */
	public void insertBig2Node(Big2SGraphGuardSchema value){
		if (value instanceof Big2TextSchemaBean)
			accessObject.insertBig2TextNode((Big2TextSchemaBean)value);
		else if (value instanceof Big2VideoSchemaBean)
			accessObject.insertBig2VideoNode((Big2VideoSchemaBean)value);
		else if (value instanceof Big2AudioSchemaBean)
			accessObject.insertBig2AudioNode((Big2AudioSchemaBean)value);
		else if (value instanceof Big2WebSchemaBean)
			accessObject.insertBig2WebNode((Big2WebSchemaBean)value);

		notifyAllObservers(value);		
	}
}

package upc.bdam.recommender.graph.dao;

import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.RelationshipType;

import upc.bdam.recommender.graphupdater.schema.Big2AudioSchemaBean;
import upc.bdam.recommender.graphupdater.schema.Big2TextSchemaBean;
import upc.bdam.recommender.graphupdater.schema.Big2VideoSchemaBean;
import upc.bdam.recommender.graphupdater.schema.Big2WebSchemaBean;
import upc.bdam.recommender.kafka.KafkaBean;
import upc.bdam.recommender.ontology.json.IBinding;
import upc.bdam.recommender.ontology.json.artwork.ArtWork;
import upc.bdam.recommender.ontology.json.author.Author;
import upc.bdam.recommender.ontology.json.band.Band;
import upc.bdam.recommender.ontology.json.genre.Genre;
import upc.bdam.recommender.ontology.json.relation.LeftRightRelation;

/**
 * Clase que encapsula el acceso al data source de la BBDD de conocimiento
 * @author Grupo 9: 
 *           - Antolín Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David Pérez Rodríguez
 *
 */
public class GraphDataAccessObject {

	//declaración de las constantes que definen los campos de los nodos que se van a insertar
	public static final String NODE_TYPE= "type";
	public static final String NODE_SUB_TYPE= "subtype";
	
	public static final String PERSON_NODE_ID = "id";
	public static final String PERSON_NODE_BORN = "born";
	public static final String PERSON_NODE_NAME = "name";

	public static final String ARTWORK_NODE_ID = "id";
	public static final String ARTWORK_NODE_RELEASED = "released";
	public static final String ARTWORK_NODE_TITLE = "title";

	public static final String GENRE_NODE_ID = "id";
	public static final String GENRE_NODE_NAME = "name";

	public static final String BAND_NODE_ID = "id";
	public static final String BAND_NODE_RELEASED = "released";
	public static final String BAND_NODE_NAME = "name";
	
	public static final String USER_DATA_NICK_NAME="nickName";
	public static final String USER_DATA_NICK="name";
	public static final String USER_DATA_SURNAME="surname";
	public static final String USER_DATA_BORN="born";	
	public static final String USER_DATA_SEX="sex";
	public static final String USER_DATA_PROFESSION="profession";
	public static final String USER_DATA_HOBBY="hobby";
	public static final String USER_DATA_INTERESTED_IN="interestedIn";
	
	public static final String BIG2_TIMESTAMP = "timestamp";
	public static final String BIG2_USER_ID = "userId";
	public static final String BIG2_NOMBRES = "nombres";
	public static final String BIG2_TEMAS = "temas";
	public static final String BIG2_CLAVES = "claves";
	public static final String BIG2_GENEROS = "generos";
	public static final String BIG2_PALABRAS = "palabras";
	public static final String BIG2_URL = "url";
	public static final String BIG2_TITULO = "titulo";
	public static final String BIG2_AUTOR = "autor";
	public static final String BIG2_IDIOMA = "idioma";
	public static final String BIG2_ISBN = "ISBN";
	public static final String BIG2_CREATOR = "creator";
	public static final String BIG2_YEAR = "autor";
	public static final String BIG2_SUBJECT = "subject";
	public static final String BIG2_TYPE = "type";
	public static final String BIG2_ACTORES = "actores";
	public static final String BIG2_CLASIFICACION = "clasificacion";
	public static final String BIG2_DIRECCION = "direccion";
	public static final String BIG2_DESCRIPCION = "direccion";
	public static final String BIG2_PRODUCTOR = "productor";
	public static final String BIG2_GUIONISTA = "guionista";
	public static final String BIG2_ALBUM = "guionista";
	public static final String BIG2_INTERPRETE = "guionista";
	public static final String BIG2_COLABORADORES = "guionista";
	public static final String BIG2_COMPOSITORES = "guionista";
	
	
	//declaración de la variable de acceso a BBDD
	GraphDataSource dbDataAccess = new GraphDataSource();
	
	//declaración de los tipos de nodos
	enum TextAnalytics implements Label{
		Text, Web, Audio, Video
	}
	//declaración de los tipos de nodos
	enum NodeType implements Label{
		Genre, Person, ArtWork, Band, User
	}

	//declaracion de los subtipos existentes para los nodos persona
	enum PersonNodeSubType implements Label {
		Writer, Actor, Director, Band, Musician
	}
	
	//declaración de los subtipos para los nodos de tipo art work
	enum ArtWorkNodeSubType implements Label {
		Book, Film, Song
	}
	
	//declaración de los tipos de relaciones existentes entre personas y roles que pueden desempeñar
	enum PersonRelationType implements RelationshipType {
		WriterWroteBook, MusicianMemberOfBand, DirectorDirectedFilm, MusicianPlayedSong, BandPlayedSong, ActorActedFilm
	}
	
	//declaración de las relaciones de artworks y géneros
	enum GenreRelationType implements RelationshipType{
		BookGenre, FilmGenre, BandGenre, SongGenre
	}

	/**
	 * Método para la inserción de nodos de género
	 * @param values
	 * @param type
	 */
	public void insertGenreNode(IBinding[] values, NodeType type) {
		dbDataAccess.initTransaction();
		for (IBinding genre : values) {
			dbDataAccess.insertGenreNode((Genre) genre, type);
		}
		dbDataAccess.commit();
		dbDataAccess.closeTransaction();
	}

	/**
	 * Método para la inserción de nodos persona
	 * @param values
	 * @param type
	 * @param subtype
	 */
	public void insertPersonNode(IBinding[] values, NodeType type, PersonNodeSubType subtype) {
		dbDataAccess.initTransaction();
		for (IBinding person : values) {
			dbDataAccess.insertPersonNode((Author) person, type, subtype);
		}
		dbDataAccess.commit();
		dbDataAccess.closeTransaction();
	}

	/**
	 * Método para la inserción de nodos band
	 * @param values
	 * @param type
	 */
	public void insertBandNode(IBinding[] values, NodeType type) {
		dbDataAccess.initTransaction();
		for (IBinding band : values) {
			dbDataAccess.insertBandNode((Band) band, type);
		}
		dbDataAccess.commit();
		dbDataAccess.closeTransaction();
	}

	/**
	 * Método para la inserción de nodos artWork
	 * @param values
	 * @param type
	 * @param subtype
	 */
	public void insertArtWorkNode(IBinding[] values, NodeType type, ArtWorkNodeSubType subtype){
		dbDataAccess.initTransaction();
		for (IBinding person : values) {
			dbDataAccess.insertArtWorkNode((ArtWork) person, type, subtype);
		}
		dbDataAccess.commit();
		dbDataAccess.closeTransaction();
	}

	/**
	 * Inserción de relaciones entre nodos
	 * @param values
	 * @param relationType
	 * @param personType
	 * @param label
	 */
	public void insertRelation(IBinding[] values, RelationshipType relationType, NodeType personType, Label label) {
		dbDataAccess.initTransaction();
		for (IBinding relation : values) {
			dbDataAccess.insertRelation((LeftRightRelation) relation, relationType, personType, label);
		}
		dbDataAccess.commit();
		dbDataAccess.closeTransaction();
	}

	/**
	 * Inserción de los nodos de usuario
	 * @param value
	 */
	public void insertUserNode(KafkaBean value) {
		dbDataAccess.initTransaction();

		dbDataAccess.insertUserNode(value, NodeType.User);

		dbDataAccess.commit();
		dbDataAccess.closeTransaction();
	}


	/**
	 * Se insertan los nodos de texto en la BBDD de conocimiento
	 * @param value
	 */
	public void insertBig2TextNode(Big2TextSchemaBean value){
		dbDataAccess.initTransaction();

		dbDataAccess.insertBig2Text(value, TextAnalytics.Text);

		dbDataAccess.commit();
		dbDataAccess.closeTransaction();
	}
	
	/**
	 * Se insertan los nodos de audio en la BBDD de conocimiento
	 * @param value
	 */
	public void insertBig2AudioNode(Big2AudioSchemaBean value){
		dbDataAccess.initTransaction();

		dbDataAccess.insertBig2Audio(value, TextAnalytics.Text);

		dbDataAccess.commit();
		dbDataAccess.closeTransaction();
	}

	/**
	 * Se insertan los nodos de video en la BBDD de conocimiento
	 * @param value
	 */
	public void insertBig2VideoNode(Big2VideoSchemaBean value){
		dbDataAccess.initTransaction();

		dbDataAccess.insertBig2Video(value, TextAnalytics.Text);

		dbDataAccess.commit();
		dbDataAccess.closeTransaction();
	}
	
	/**
	 * Se insertan los nodos web en la BBDD de conocimiento
	 * @param value
	 */
	public void insertBig2WebNode(Big2WebSchemaBean value){
		dbDataAccess.initTransaction();

		dbDataAccess.insertBig2Web(value, TextAnalytics.Text);

		dbDataAccess.commit();
		dbDataAccess.closeTransaction();
	}
	
	
	/**
	 * Se insertan los nodos web en la BBDD de conocimiento
	 * @param value
	 */
	public String recomendar(){
		return dbDataAccess.recomendar();

	}
}

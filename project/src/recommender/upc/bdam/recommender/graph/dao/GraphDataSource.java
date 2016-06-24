package upc.bdam.recommender.graph.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.kernel.impl.core.NodeProxy;

import upc.bdam.recommender.config.PropertiesLoader;
import upc.bdam.recommender.graph.dao.GraphDataAccessObject.ArtWorkNodeSubType;
import upc.bdam.recommender.graph.dao.GraphDataAccessObject.NodeType;
import upc.bdam.recommender.graph.dao.GraphDataAccessObject.PersonNodeSubType;
import upc.bdam.recommender.graph.dao.GraphDataAccessObject.TextAnalytics;
import upc.bdam.recommender.graphupdater.schema.Big2AudioSchemaBean;
import upc.bdam.recommender.graphupdater.schema.Big2TextSchemaBean;
import upc.bdam.recommender.graphupdater.schema.Big2VideoSchemaBean;
import upc.bdam.recommender.graphupdater.schema.Big2WebSchemaBean;
import upc.bdam.recommender.kafka.KafkaBean;
import upc.bdam.recommender.ontology.json.artwork.ArtWork;
import upc.bdam.recommender.ontology.json.author.Author;
import upc.bdam.recommender.ontology.json.band.Band;
import upc.bdam.recommender.ontology.json.genre.Genre;
import upc.bdam.recommender.ontology.json.relation.LeftRightRelation;

/**
 * Clase de acceso a la BBDD de conocmiento
 * @author Grupo 9: 
 *           - Antolín Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David Pérez Rodríguez
 *
 */
public class GraphDataSource {

	//declaración del nombre de la BBDD
	private static final String NEO4J_DDBB = "neo4JDB";
	private static final String NEO4J_RECOMENDACION="recomendacion";

	//acceso al fichero en el que se almacena la BBDD
	File DB = new File(PropertiesLoader.getInstance().getProperty(NEO4J_DDBB));
	GraphDatabaseService graph = null;
	Transaction tx = null;

	public GraphDataSource() {
		init();
	}

	/**
	 * Se abre la BBDD
	 */
	private void init() {
		DB = new File(PropertiesLoader.getInstance().getProperty(NEO4J_DDBB));
		graph=new GraphDatabaseFactory().newEmbeddedDatabase(DB);
	}

	/**
	 * Inicio de transacción sobre la BBDD
	 */
	public void initTransaction() {
		tx = graph.beginTx();
	}

	/**
	 * Commit de la última acción realizada
	 */
	public void commit() {
		tx.success();
	}

	/**
	 * Cierre de la operación en curso
	 */
	public void closeTransaction() {
		tx.close();
		tx = null;
		graph.shutdown();
	}
	
	/**
	 * Método para leer queries sobre la BBDD 
	 * @param query
	 * @return
	 * @throws IOException
	 */
	public String readQuery(String query) throws IOException {
		BufferedReader br = null;
		String everything = new String();
		br = new BufferedReader(new FileReader(query));
		StringBuilder sb = new StringBuilder();
		String line = br.readLine();

		while (line != null) {
			sb.append(line);
			sb.append(System.lineSeparator());
			line = br.readLine();
		}
		everything = sb.toString();
		br.close();

		return everything;
	}

	/**
	 * Inserción de nodos de tipo género
	 * @param genre
	 * @param type
	 */
	public void insertGenreNode(Genre genre, NodeType type) {

		Node node = graph.createNode(type);

		node.setProperty(GraphDataAccessObject.GENRE_NODE_ID, genre.getId().getValue());
		node.setProperty(GraphDataAccessObject.GENRE_NODE_NAME, genre.getName().getValue());
	}

	/**
	 * Inserción de nodo tipo persona
	 * @param author
	 * @param type
	 * @param subtype
	 */
	public void insertPersonNode(Author author, NodeType type, PersonNodeSubType subtype) {

		Node node = graph.createNode(type);

		node.setProperty(GraphDataAccessObject.PERSON_NODE_ID, author.getId().getValue());
		node.setProperty(GraphDataAccessObject.PERSON_NODE_BORN, author.getBorn().getValue());
		node.setProperty(GraphDataAccessObject.PERSON_NODE_NAME, author.getName().getValue());
		node.setProperty(GraphDataAccessObject.NODE_TYPE, type.name());
		node.setProperty(GraphDataAccessObject.NODE_SUB_TYPE,subtype.name());
	}

	/**
	 * Inserción de nodos de tipo nodo
	 * @param band
	 * @param type
	 */
	public void insertBandNode(Band band, NodeType type) {

		Node node = graph.createNode(type);

		node.setProperty(GraphDataAccessObject.BAND_NODE_ID, band.getId().getValue());
		node.setProperty(GraphDataAccessObject.BAND_NODE_RELEASED, band.getReleased().getValue());
		node.setProperty(GraphDataAccessObject.BAND_NODE_NAME, band.getName().getValue());
		node.setProperty(GraphDataAccessObject.NODE_TYPE, type);
	}

	/**
	 * Inserción de nodos de tipo artWork
	 * @param artWork
	 * @param type
	 * @param subtype
	 */
	public void insertArtWorkNode(ArtWork artWork, NodeType type, ArtWorkNodeSubType subtype ) {

		Node node = graph.createNode(type);

		node.setProperty(GraphDataAccessObject.ARTWORK_NODE_ID, artWork.getId().getValue());
		node.setProperty(GraphDataAccessObject.ARTWORK_NODE_RELEASED, artWork.getReleased().getValue());
		node.setProperty(GraphDataAccessObject.ARTWORK_NODE_TITLE, artWork.getTitle().getValue());
		node.setProperty(GraphDataAccessObject.NODE_TYPE, type.name());
		node.setProperty(GraphDataAccessObject.NODE_SUB_TYPE, subtype.name());
	}

	/**
	 * Inserción de nodos de tipo usuario
	 * @param user
	 * @param type
	 */
	public void insertUserNode(KafkaBean user, NodeType type) {

		Node node = graph.createNode(type);
		
		node.setProperty(GraphDataAccessObject.USER_DATA_NICK_NAME, user.getNickName());
		node.setProperty(GraphDataAccessObject.USER_DATA_SURNAME, user.getSurname());
		node.setProperty(GraphDataAccessObject.USER_DATA_NICK, user.getName());
		node.setProperty(GraphDataAccessObject.USER_DATA_BORN, user.getBorn());
		node.setProperty(GraphDataAccessObject.USER_DATA_SEX, user.getSex());
		node.setProperty(GraphDataAccessObject.USER_DATA_PROFESSION, user.getProfession());
		node.setProperty(GraphDataAccessObject.USER_DATA_HOBBY, user.getHobby());
		node.setProperty(GraphDataAccessObject.USER_DATA_INTERESTED_IN, user.getInterestedIn());
	}
	
	/**
	 * Método para la inserción de relaciones entre nodos
	 * @param relation
	 * @param relationType
	 * @param personType
	 * @param label
	 */
	public void insertRelation(LeftRightRelation relation, RelationshipType relationType, NodeType personType, Label label) {

		String authorQuery = "MATCH (c1:"+personType+") WHERE c1.id='" + relation.getLeftId().getValue()
				+ "' RETURN DISTINCT c1;";
		String artWorkQuery = "MATCH (c1:"+label+") WHERE c1.id='" + relation.getRightId().getValue()
				+ "' RETURN DISTINCT c1;";

		NodeProxy authorNode = executeNeo4jQuery(authorQuery);
		NodeProxy artWorkNode = executeNeo4jQuery(artWorkQuery);

		if (authorNode != null && artWorkNode != null)
			authorNode.createRelationshipTo(artWorkNode, relationType);
	}

	/**
	 * Ejecución de consultas sobre la BBDD
	 * @param query
	 * @return
	 */
	private NodeProxy executeNeo4jQuery(String query) {
		NodeProxy resultado = null;

		org.neo4j.graphdb.Result result = graph.execute(query);
		while (result.hasNext()) {
			Map<String, Object> aux = result.next();
			resultado = (NodeProxy) aux.get("c1");
		}
		return resultado;

	}
	
	
	public void insertBig2Text(Big2TextSchemaBean value, TextAnalytics type){
		Node node = graph.createNode(type);
		
		node.setProperty(GraphDataAccessObject.BIG2_AUTOR, value.getAutor());
		node.setProperty(GraphDataAccessObject.BIG2_TIMESTAMP, value.getId());
		node.setProperty(GraphDataAccessObject.BIG2_CLAVES, value.getClaves());
		node.setProperty(GraphDataAccessObject.BIG2_CREATOR, value.getCreator());
		node.setProperty(GraphDataAccessObject.BIG2_GENEROS, value.getGeneros());
		node.setProperty(GraphDataAccessObject.BIG2_IDIOMA, value.getIdioma());
		node.setProperty(GraphDataAccessObject.BIG2_PALABRAS, value.getPalabras());
		node.setProperty(GraphDataAccessObject.BIG2_SUBJECT, value.getSubject());		
		node.setProperty(GraphDataAccessObject.BIG2_ISBN, value.getIsbn());
		node.setProperty(GraphDataAccessObject.BIG2_TEMAS, value.getTemas());
		node.setProperty(GraphDataAccessObject.BIG2_NOMBRES, value.getNombres());
		node.setProperty(GraphDataAccessObject.BIG2_TITULO, value.getTitulo());
		node.setProperty(GraphDataAccessObject.BIG2_TYPE, value.getType());
		node.setProperty(GraphDataAccessObject.BIG2_USER_ID, value.getUserId());
		node.setProperty(GraphDataAccessObject.BIG2_YEAR, value.getYear());
	}
	
	/**
	 * Se insertan los nodos de audio en la BBDD de conocimiento
	 * @param value
	 */
	public void insertBig2Audio(Big2AudioSchemaBean value, TextAnalytics type){

		Node node = graph.createNode(type);
		
		node.setProperty(GraphDataAccessObject.BIG2_ALBUM, value.getAlbum());
		node.setProperty(GraphDataAccessObject.BIG2_CLASIFICACION, value.getClasificacion());
		node.setProperty(GraphDataAccessObject.BIG2_COLABORADORES, value.getColaboradores());
		node.setProperty(GraphDataAccessObject.BIG2_COMPOSITORES, value.getCompositores());
		node.setProperty(GraphDataAccessObject.BIG2_GENEROS, value.getGeneros());
		node.setProperty(GraphDataAccessObject.BIG2_TIMESTAMP, value.getId());
		node.setProperty(GraphDataAccessObject.BIG2_INTERPRETE, value.getInterprete());
		node.setProperty(GraphDataAccessObject.BIG2_TITULO, value.getTitulo());		
		node.setProperty(GraphDataAccessObject.BIG2_USER_ID, value.getUserId());
		node.setProperty(GraphDataAccessObject.BIG2_YEAR, value.getYear());
	}

	/**
	 * Se insertan los nodos de video en la BBDD de conocimiento
	 * @param value
	 */
	public void insertBig2Video(Big2VideoSchemaBean value, TextAnalytics type){

		Node node = graph.createNode(type);
		
		node.setProperty(GraphDataAccessObject.BIG2_ACTORES, value.getActores());
		node.setProperty(GraphDataAccessObject.BIG2_TIMESTAMP, value.getId());
		node.setProperty(GraphDataAccessObject.BIG2_CLASIFICACION, value.getClasificacion());
		node.setProperty(GraphDataAccessObject.BIG2_DIRECCION, value.getDirector());
		node.setProperty(GraphDataAccessObject.BIG2_GENEROS, value.getGeneros());
		node.setProperty(GraphDataAccessObject.BIG2_GUIONISTA, value.getGuionista());
		node.setProperty(GraphDataAccessObject.BIG2_PRODUCTOR, value.getProductor());
		node.setProperty(GraphDataAccessObject.BIG2_TITULO, value.getTitulo());		
		node.setProperty(GraphDataAccessObject.BIG2_USER_ID, value.getUserId());
		node.setProperty(GraphDataAccessObject.BIG2_YEAR, value.getYear());

	
	}
	
	/**
	 * Se insertan los nodos web en la BBDD de conocimiento
	 * @param value
	 */
	public void insertBig2Web(Big2WebSchemaBean value, TextAnalytics type){

		Node node = graph.createNode(type);
		
		node.setProperty(GraphDataAccessObject.BIG2_CLAVES, value.getClaves());
		node.setProperty(GraphDataAccessObject.BIG2_DESCRIPCION, value.getDescripcion());
		node.setProperty(GraphDataAccessObject.BIG2_GENEROS, value.getGeneros());
		node.setProperty(GraphDataAccessObject.BIG2_TIMESTAMP, value.getId());
		node.setProperty(GraphDataAccessObject.BIG2_NOMBRES, value.getNombres());
		node.setProperty(GraphDataAccessObject.BIG2_TEMAS, value.getTemas());
		node.setProperty(GraphDataAccessObject.BIG2_TITULO, value.getTitulo());
		node.setProperty(GraphDataAccessObject.BIG2_URL, value.getUri());		
		node.setProperty(GraphDataAccessObject.BIG2_USER_ID, value.getUserId());
	}
	

	/**
	 * 
	 * @return
	 */
	public String recomendar() {
		StringBuffer resultado=new StringBuffer();
		String query=PropertiesLoader.getInstance().getProperty(NEO4J_RECOMENDACION);


		NodeProxy response = executeNeo4jQuery(query);
		
		if (response != null){
			Iterable<String> keys=response.getPropertyKeys();
			Iterator<String> iter=keys.iterator();
			while (iter.hasNext())
			{
				String key=iter.next();
				resultado.append(key+": "+response.getProperty(key)+"\n");
			}

		}	
		return resultado.toString();
	}
}

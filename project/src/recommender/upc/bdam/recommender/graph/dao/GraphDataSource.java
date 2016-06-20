package upc.bdam.recommender.graph.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
import upc.bdam.recommender.ontology.json.artwork.ArtWork;
import upc.bdam.recommender.ontology.json.author.Author;
import upc.bdam.recommender.ontology.json.band.Band;
import upc.bdam.recommender.ontology.json.genre.Genre;
import upc.bdam.recommender.ontology.json.relation.ArtWorkAuthorRelation;
import upc.bdam.recommender.ontology.json.relation.LeftRightRelation;

/**
 * 
 * @author Grupo 9: 
 *           - Antolín Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David Pérez Rodríguez
 *
 */
public class GraphDataSource {

	static final String NEO4J_DDBB = "neo4JDB";

	File DB = new File(PropertiesLoader.getInstance().getProperty(NEO4J_DDBB));
	GraphDatabaseService graph = null;
	Transaction tx = null;

	public GraphDataSource() {
		init();
	}

	private void init() {
			//FileUtils.deleteDirectory(DB);
			//graph = new GraphDatabaseFactory().newEmbeddedDatabase(DB);
	}

	public void initTransaction() {
		graph=new GraphDatabaseFactory().newEmbeddedDatabase(DB);
		tx = graph.beginTx();
	}

	public void commit() {
		tx.success();
	}

	public void closeTransaction() {
		tx.close();
		tx = null;
		graph.shutdown();
	}
	

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

	public void insertGenreNode(Genre genre, NodeType type) {

		Node node = graph.createNode(type);

		node.setProperty(GraphDataAccessObject.GENRE_NODE_ID, genre.getId().getValue());
		node.setProperty(GraphDataAccessObject.GENRE_NODE_NAME, genre.getName().getValue());
		//node.setProperty(Neo4JDataAccessObject.GENRE_NODE_NAME_NORM, genre.getNameNormalized().getValue());
	}

	public void insertPersonNode(Author author, NodeType type, PersonNodeSubType subtype) {

		Node node = graph.createNode(type);

		node.setProperty(GraphDataAccessObject.PERSON_NODE_ID, author.getId().getValue());
		node.setProperty(GraphDataAccessObject.PERSON_NODE_BORN, author.getBorn().getValue());
		node.setProperty(GraphDataAccessObject.PERSON_NODE_NAME, author.getName().getValue());
		node.setProperty(GraphDataAccessObject.NODE_TYPE, type.name());
		node.setProperty(GraphDataAccessObject.NODE_SUB_TYPE,subtype.name());
	}

	public void insertBandNode(Band band, NodeType type) {

		Node node = graph.createNode(type);

		node.setProperty(GraphDataAccessObject.BAND_NODE_ID, band.getId().getValue());
		node.setProperty(GraphDataAccessObject.BAND_NODE_RELEASED, band.getReleased().getValue());
		node.setProperty(GraphDataAccessObject.BAND_NODE_NAME, band.getName().getValue());
		node.setProperty(GraphDataAccessObject.NODE_TYPE, type);
	}

	public void insertArtWorkNode(ArtWork artWork, NodeType type, ArtWorkNodeSubType subtype ) {

		Node node = graph.createNode(type);

		node.setProperty(GraphDataAccessObject.ARTWORK_NODE_ID, artWork.getId().getValue());
		node.setProperty(GraphDataAccessObject.ARTWORK_NODE_RELEASED, artWork.getReleased().getValue());
		node.setProperty(GraphDataAccessObject.ARTWORK_NODE_TITLE, artWork.getTitle().getValue());
		node.setProperty(GraphDataAccessObject.NODE_TYPE, type.name());
		node.setProperty(GraphDataAccessObject.NODE_SUB_TYPE, subtype.name());
	}

/*	public void insertRelationAuthorBand(AuthorBandRelation relation, PersonRelationType relationType, NodeType personType, Label label ) {

		String authorQuery = "MATCH (c1:"+personType+") WHERE c1.id='" + relation.getAuthorId().getValue()
				+ "' RETURN DISTINCT c1;";
		String bandQuery = "MATCH (c1:"+label+") WHERE c1.id='" + relation.getBandId().getValue()
				+ "' RETURN DISTINCT c1;";

		NodeProxy authorNode = executeNeo4jQuery(authorQuery);
		NodeProxy bandNode = executeNeo4jQuery(bandQuery);

		if (authorNode != null && bandNode != null)
			authorNode.createRelationshipTo(bandNode, relationType);
	}*/

/*	public void insertRelationBandArtWork(BandArtWorkRelation relation, PersonRelationType relationType, NodeType bandType, Label label ) {

		String bandQuery = "MATCH (c1:"+bandType+") WHERE c1.id='" + relation.getBandId().getValue()
				+ "' RETURN DISTINCT c1;";
		String artWorkQuery = "MATCH (c1:"+label+") WHERE c1.id='" + relation.getArtWorkId().getValue()
				+ "' RETURN DISTINCT c1;";

		NodeProxy bandNode = executeNeo4jQuery(bandQuery);
		NodeProxy artWorkNode = executeNeo4jQuery(artWorkQuery);

		if (bandNode != null && artWorkNode != null)
			bandNode.createRelationshipTo(artWorkNode, relationType);
	}*/
	
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

/*	public void insertRelationArtWorkGenre(ArtWorkGenreRelation relation, GenreRelationType relationType, NodeType artWorkType, Label label ) {

		String artWorkQuery = "MATCH (c1:"+artWorkType+") WHERE c1.id='" + relation.getArtWorkId().getValue()
				+ "' RETURN DISTINCT c1;";
		String genreQuery = "MATCH (c1:"+label+") WHERE c1.id='" + relation.getGenreId().getValue()
				+ "' RETURN DISTINCT c1;";

		NodeProxy artWorkNode = executeNeo4jQuery(artWorkQuery);
		NodeProxy genreNode = executeNeo4jQuery(genreQuery);

		if (artWorkNode != null && genreNode != null)
			artWorkNode.createRelationshipTo(genreNode, relationType);
	}*/

	private NodeProxy executeNeo4jQuery(String query) {
		NodeProxy resultado = null;

		org.neo4j.graphdb.Result result = graph.execute(query);
		while (result.hasNext()) {
			Map<String, Object> aux = result.next();
			resultado = (NodeProxy) aux.get("c1");
		}
		return resultado;

	}
}

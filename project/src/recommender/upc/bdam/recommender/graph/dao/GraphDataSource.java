package upc.bdam.recommender.graph.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.kernel.impl.core.NodeProxy;

import upc.bdam.recommender.config.PropertiesLoader;
import upc.bdam.recommender.graph.dao.GraphDataAccessObject.ArtWorkNodeType;
import upc.bdam.recommender.graph.dao.GraphDataAccessObject.PersonNodeType;
import upc.bdam.recommender.graph.dao.GraphDataAccessObject.RelationType;
import upc.bdam.recommender.ontology.json.artwork.ArtWork;
import upc.bdam.recommender.ontology.json.author.Author;
import upc.bdam.recommender.ontology.json.relation.ArtWorkAuthorRelation;

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

	public void insertPersonNode(Author author, PersonNodeType type) {

		Node node = graph.createNode(type);

		node.setProperty(GraphDataAccessObject.PERSON_NODE_ID, author.getId().getValue());
		node.setProperty(GraphDataAccessObject.PERSON_NODE_BORN, author.getBorn().getValue());
		node.setProperty(GraphDataAccessObject.PERSON_NODE_NAME, author.getName().getValue());
	}

	public void insertArtWorkNode(ArtWork artWork, ArtWorkNodeType type) {

		Node node = graph.createNode(type);

		node.setProperty(GraphDataAccessObject.ARTWORK_NODE_ID, artWork.getId().getValue());
		node.setProperty(GraphDataAccessObject.ARTWORK_NODE_RELEASED, artWork.getReleased().getValue());
		node.setProperty(GraphDataAccessObject.ARTWORK_NODE_TITLE, artWork.getTitle().getValue());
		node.setProperty(GraphDataAccessObject.ARTWORK_NODE_GENRE, artWork.getGenre().getValue());
	}

	public void insertRelation(ArtWorkAuthorRelation relation, RelationType relationType, PersonNodeType personType, Label label ) {

		String authorQuery = "MATCH (c1:"+personType+") WHERE c1.id='" + relation.getAuthorId().getValue()
				+ "' RETURN DISTINCT c1;";
		String artWorkQuery = "MATCH (c1:"+label+") WHERE c1.id='" + relation.getArtWorkId().getValue()
				+ "' RETURN DISTINCT c1;";

		NodeProxy authorNode = executeNeo4jQuery(authorQuery);
		NodeProxy artWorkNode = executeNeo4jQuery(artWorkQuery);

		if (authorNode != null && artWorkNode != null)
			authorNode.createRelationshipTo(artWorkNode, relationType);
	}

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

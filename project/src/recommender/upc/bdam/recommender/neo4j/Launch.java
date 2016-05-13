package upc.bdam.recommender.neo4j;

import java.io.File;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import upc.bdam.recommender.config.PropertiesLoader;

public class Launch {

	public static void generate(int nodes) {
		File db = new File(
				PropertiesLoader.getInstance().getProperty(PropertiesLoader.PROPERTIES_LOADER_NEO4J_LOCATION));
		GraphDatabaseService graph = null;
		graph = new GraphDatabaseFactory().newEmbeddedDatabase(db);
	}
	
}

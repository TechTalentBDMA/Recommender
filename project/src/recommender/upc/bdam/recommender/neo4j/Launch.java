package upc.bdam.recommender.neo4j;

import java.io.File;


import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

public class Launch {

	private final static File DB = new File("C:/Users/Antolín/Documents/Neo4j/graphsr_01.db");

	public static void generate(int nodes) {
		GraphDatabaseService graph = null;
		graph = new GraphDatabaseFactory().newEmbeddedDatabase(DB);
	}

	public static void main(String[] args) {
		generate(Integer.parseInt(args[0]));
	}

}

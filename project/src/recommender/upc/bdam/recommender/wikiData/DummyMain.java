package upc.bdam.recommender.wikiData;

import upc.bdam.recommender.neo4j.dao.Neo4JDataAccessManager;
import upc.bdam.recommender.ontology.json.IBinding;

public class DummyMain {
	
	

	public static void main(String[] args) {
		

		try {
			getBooks();
			getFilms();
			getSongs();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void getBooks() throws Exception{
		OntologyDataAccessObject dao=new OntologyDataAccessObject();
		IBinding[] value=dao.getBooks();
	//	Neo4JDataAccessManager.getInstance().insert(Neo4JDataAccessManager.GRAPH_BOOK_INSERT, value);
		value=dao.getWriters();
		//Neo4JDataAccessManager.getInstance().insert(Neo4JDataAccessManager.GRAPH_WRITER_INSERT, value);
		value=dao.getWriterBookRelation();
		//Neo4JDataAccessManager.getInstance().insert(Neo4JDataAccessManager.GRAPH_WRITER_BOOK_RELATION_INSERT, value);
		System.out.println(value.length);
	}
	
	private static void getFilms() throws Exception{
		OntologyDataAccessObject dao=new OntologyDataAccessObject();
		IBinding[] value=dao.getActors();
//		Neo4JDataAccessManager.getInstance().insert(Neo4JDataAccessManager.GRAPH_ACTOR_INSERT, value);
		value=dao.getDirector();
	//	Neo4JDataAccessManager.getInstance().insert(Neo4JDataAccessManager.GRAPH_DIRECTOR_INSERT, value);
		value=dao.getFilms();
		//Neo4JDataAccessManager.getInstance().insert(Neo4JDataAccessManager.GRAPH_FILM_INSERT, value);
		value=dao.getActorFilmRelation();
		//Neo4JDataAccessManager.getInstance().insert(Neo4JDataAccessManager.GRAPH_ACTOR_FILM_RELATION_INSERT, value);
		value=dao.getDirectorFilmRelation();
		//Neo4JDataAccessManager.getInstance().insert(Neo4JDataAccessManager.GRAPH_DIRECTOR_FILM_RELATION_INSERT, value);
	}
	
	private static void getSongs() throws Exception{
		OntologyDataAccessObject dao=new OntologyDataAccessObject();
		IBinding[] value=dao.getBand();
		//Neo4JDataAccessManager.getInstance().insert(Neo4JDataAccessManager.GRAPH_BAND_INSERT, value);
		value=dao.getMusician();
		//Neo4JDataAccessManager.getInstance().insert(Neo4JDataAccessManager.GRAPH_MUSICIAN_INSERT, value);
		value=dao.getSongs();
		//Neo4JDataAccessManager.getInstance().insert(Neo4JDataAccessManager.GRAPH_SONG_INSERT, value);
		value=dao.getBandMemberRelation();
		//Neo4JDataAccessManager.getInstance().insert(Neo4JDataAccessManager.GRAPH_BAND_MEMBER_RELATION_INSERT, value);
		value=dao.getSongPerformerRelation();
		//Neo4JDataAccessManager.getInstance().insert(Neo4JDataAccessManager.GRAPH_SONG_PERFORMER_RELATION_INSERT, value);

	}
}

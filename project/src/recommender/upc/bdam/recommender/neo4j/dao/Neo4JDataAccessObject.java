package upc.bdam.recommender.neo4j.dao;

import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.RelationshipType;

import upc.bdam.recommender.ontology.json.IBinding;
import upc.bdam.recommender.ontology.json.artwork.ArtWork;
import upc.bdam.recommender.ontology.json.author.Author;
import upc.bdam.recommender.ontology.json.relation.ArtWorkAuthorRelation;

public class Neo4JDataAccessObject {

	public static final String PERSON_NODE_ID = "id";
	public static final String PERSON_NODE_BORN = "born";
	public static final String PERSON_NODE_NAME = "name";

	public static final String ARTWORK_NODE_ID = "id";
	public static final String ARTWORK_NODE_GENRE = "genre";
	public static final String ARTWORK_NODE_RELEASED = "released";
	public static final String ARTWORK_NODE_TITLE = "title";

	enum PersonNodeType implements Label {
		Writer, Actor, Director, Band, Musician
	}

	enum ArtWorkNodeType implements Label {
		Book, Film, Song
	}

	enum RelationType implements RelationshipType {
		WriterWroteBook, MusicianMemberOfBand, DirectorDirectedFilm, BandPlayedSong, ActorActedFilm
	}

	Neo4JDataSource dbDataAccess = new Neo4JDataSource();

	public void insertPersonNode(IBinding[] values, PersonNodeType type) {
		dbDataAccess.initTransaction();
		for (IBinding person : values) {
			dbDataAccess.insertPersonNode((Author) person, type);
		}
		dbDataAccess.commit();
		dbDataAccess.closeTransaction();
	}

	public void insertArtWorkNode(IBinding[] values, ArtWorkNodeType type){
		dbDataAccess.initTransaction();
		for (IBinding person : values) {
			dbDataAccess.insertArtWorkNode((ArtWork) person, type);
		}
		dbDataAccess.commit();
		dbDataAccess.closeTransaction();
	}

	public void insertRelation(IBinding[] values, RelationType relationType, PersonNodeType personType, Label label) {
		dbDataAccess.initTransaction();
		for (IBinding relation : values) {
			dbDataAccess.insertRelation((ArtWorkAuthorRelation) relation, relationType, personType, label);
		}
		dbDataAccess.commit();
		dbDataAccess.closeTransaction();
	}
	

}

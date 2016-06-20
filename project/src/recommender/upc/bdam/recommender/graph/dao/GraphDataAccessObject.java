package upc.bdam.recommender.graph.dao;

import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.RelationshipType;

import upc.bdam.recommender.ontology.json.IBinding;
import upc.bdam.recommender.ontology.json.artwork.ArtWork;
import upc.bdam.recommender.ontology.json.author.Author;
import upc.bdam.recommender.ontology.json.relation.ArtWorkAuthorRelation;
import upc.bdam.recommender.ontology.json.band.Band;
import upc.bdam.recommender.ontology.json.genre.Genre;
import upc.bdam.recommender.ontology.json.relation.LeftRightRelation;

public class GraphDataAccessObject {

	public static final String NODE_TYPE= "type";
	public static final String NODE_SUB_TYPE= "subtype";
	
	public static final String PERSON_NODE_ID = "id";
	public static final String PERSON_NODE_BORN = "born";
	public static final String PERSON_NODE_NAME = "name";

	public static final String ARTWORK_NODE_ID = "id";
	//public static final String ARTWORK_NODE_GENRE = "genre";
	public static final String ARTWORK_NODE_RELEASED = "released";
	public static final String ARTWORK_NODE_TITLE = "title";

	public static final String GENRE_NODE_ID = "id";
	public static final String GENRE_NODE_NAME = "name";
	//public static final String GENRE_NODE_NAME_NORM = "nameNorm";

	public static final String BAND_NODE_ID = "id";
	public static final String BAND_NODE_RELEASED = "released";
	public static final String BAND_NODE_NAME = "name";
	
	enum NodeType implements Label{
		Genre, Person, ArtWork, Band
	}

	enum PersonNodeSubType implements Label {
		Writer, Actor, Director, Band, Musician
	}
	
	enum ArtWorkNodeSubType implements Label {
		Book, Film, Song
	}
	

/*	enum GenreNodeType implements Label {
		Genre
	}

	enum PersonNodeType implements Label {
		//Writer, Actor, Director, Band, Musician
		Person
	}

	enum ArtWorkNodeType implements Label {
		//Book, Film, Song
		ArtWork
	}

	enum BandNodeType implements Label {
		//Book, Film, Song
		Band
	}*/

	enum PersonRelationType implements RelationshipType {
		WriterWroteBook, MusicianMemberOfBand, DirectorDirectedFilm, MusicianPlayedSong, BandPlayedSong, ActorActedFilm
	}
	
	enum GenreRelationType implements RelationshipType{
		BookGenre, FilmGenre, BandGenre, SongGenre
	}

	GraphDataSource dbDataAccess = new GraphDataSource();

	public void insertGenreNode(IBinding[] values, NodeType type) {
		dbDataAccess.initTransaction();
		for (IBinding genre : values) {
			dbDataAccess.insertGenreNode((Genre) genre, type);
		}
		dbDataAccess.commit();
		dbDataAccess.closeTransaction();
	}

	public void insertPersonNode(IBinding[] values, NodeType type, PersonNodeSubType subtype) {
		dbDataAccess.initTransaction();
		for (IBinding person : values) {
			dbDataAccess.insertPersonNode((Author) person, type, subtype);
		}
		dbDataAccess.commit();
		dbDataAccess.closeTransaction();
	}

	public void insertBandNode(IBinding[] values, NodeType type) {
		dbDataAccess.initTransaction();
		for (IBinding band : values) {
			dbDataAccess.insertBandNode((Band) band, type);
		}
		dbDataAccess.commit();
		dbDataAccess.closeTransaction();
	}

	public void insertArtWorkNode(IBinding[] values, NodeType type, ArtWorkNodeSubType subtype){
		dbDataAccess.initTransaction();
		for (IBinding person : values) {
			dbDataAccess.insertArtWorkNode((ArtWork) person, type, subtype);
		}
		dbDataAccess.commit();
		dbDataAccess.closeTransaction();
	}

/*	public void insertRelationAuthorBand(IBinding[] values, PersonRelationType relationType, NodeType personType, Label label) {
		dbDataAccess.initTransaction();
		for (IBinding relation : values) {
			dbDataAccess.insertRelationAuthorBand((AuthorBandRelation) relation, relationType, personType, label);
		}
		dbDataAccess.commit();
		dbDataAccess.closeTransaction();
	}
	
	public void insertRelationBandArtWork(IBinding[] values, PersonRelationType relationType, NodeType bandType, Label label) {
		dbDataAccess.initTransaction();
		for (IBinding relation : values) {
			dbDataAccess.insertRelationBandArtWork((BandArtWorkRelation) relation, relationType, bandType, label);
		}
		dbDataAccess.commit();
		dbDataAccess.closeTransaction();
	}*/
	
	public void insertRelation(IBinding[] values, RelationshipType relationType, NodeType personType, Label label) {
		dbDataAccess.initTransaction();
		for (IBinding relation : values) {
			dbDataAccess.insertRelation((LeftRightRelation) relation, relationType, personType, label);
		}
		dbDataAccess.commit();
		dbDataAccess.closeTransaction();
	}

/*	public void insertRelationArtWorkGenre(IBinding[] values, GenreRelationType relationType, NodeType artWorkType, Label label) {
		dbDataAccess.initTransaction();
		for (IBinding relation : values) {
			dbDataAccess.insertRelationArtWorkGenre((ArtWorkGenreRelation) relation, relationType, artWorkType, label);
		}
		dbDataAccess.commit();
		dbDataAccess.closeTransaction();
	}*/
	

}

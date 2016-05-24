package upc.bdam.recommender.graph.dao;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.graphdb.Label;

import upc.bdam.recommender.graph.dao.GraphDataAccessObject.ArtWorkNodeType;
import upc.bdam.recommender.graph.dao.GraphDataAccessObject.PersonNodeType;
import upc.bdam.recommender.graph.dao.GraphDataAccessObject.RelationType;
import upc.bdam.recommender.ontology.json.IBinding;

public class GraphDataAccessManager {

	private List<GraphDDBBObserver> observers = new ArrayList<GraphDDBBObserver>();

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
	public static final byte GRAPH_SONG_PERFORMER_RELATION_INSERT = 13;

	private static GraphDataAccessManager instance = null;
	private GraphDataAccessObject accessObject = new GraphDataAccessObject();

	private GraphDataAccessManager() {

	}

	public static final GraphDataAccessManager getInstance() {
		if (instance != null)
			return instance;
		else {
			instance = new GraphDataAccessManager();
			return instance;
		}
	}

	public void insert(byte query, IBinding[] values) throws Exception {
		switch (query) {
		case GRAPH_WRITER_INSERT:
			insertPersonNode(values, PersonNodeType.Writer);
			break;
		case GRAPH_BOOK_INSERT:
			insertArtWorkNode(values, ArtWorkNodeType.Book);
			break;

		case GRAPH_WRITER_BOOK_RELATION_INSERT:
			insertRelation(values, RelationType.WriterWroteBook, PersonNodeType.Writer, ArtWorkNodeType.Book);
			break;

		case GRAPH_ACTOR_INSERT:
			insertPersonNode(values, PersonNodeType.Actor);
			break;

		case GRAPH_DIRECTOR_INSERT:
			insertPersonNode(values, PersonNodeType.Director);
			break;

		case GRAPH_FILM_INSERT:
			insertArtWorkNode(values, ArtWorkNodeType.Film);
			break;

		case GRAPH_ACTOR_FILM_RELATION_INSERT:
			insertRelation(values, RelationType.ActorActedFilm, PersonNodeType.Actor, ArtWorkNodeType.Film);
			break;

		case GRAPH_DIRECTOR_FILM_RELATION_INSERT:
			insertRelation(values, RelationType.DirectorDirectedFilm, PersonNodeType.Director, ArtWorkNodeType.Film);
			break;

		case GRAPH_MUSICIAN_INSERT:
			insertPersonNode(values, PersonNodeType.Musician);
			break;

		case GRAPH_BAND_INSERT:
			insertPersonNode(values, PersonNodeType.Band);
			break;

		case GRAPH_SONG_INSERT:
			insertArtWorkNode(values, ArtWorkNodeType.Song);
			break;

		case GRAPH_BAND_MEMBER_RELATION_INSERT:
			insertRelation(values, RelationType.MusicianMemberOfBand, PersonNodeType.Musician, PersonNodeType.Band);
			break;

		case GRAPH_SONG_PERFORMER_RELATION_INSERT:
			insertRelation(values, RelationType.BandPlayedSong, PersonNodeType.Band, ArtWorkNodeType.Song);
			break;

		default:
			break;
		}
	}

	private void insertPersonNode(IBinding[] values, PersonNodeType type) {

		accessObject.insertPersonNode(values, type);
		notifyAllObservers(values);

	}

	private void insertArtWorkNode(IBinding[] values, ArtWorkNodeType type) {
		accessObject.insertArtWorkNode(values, type);
		notifyAllObservers(values);
	}

	private void insertRelation(IBinding[] values, RelationType relationType, PersonNodeType personType,
			ArtWorkNodeType artWorkType) {
		accessObject.insertRelation(values, relationType, personType, artWorkType);
	}

	private void insertRelation(IBinding[] values, RelationType relationType, PersonNodeType personType, Label label) {
		accessObject.insertRelation(values, relationType, personType, label);
	}

	public void attach(GraphDDBBObserver observer) {
		observers.add(observer);
	}

	public void notifyAllObservers(IBinding[] values) {
		for (GraphDDBBObserver observer : observers) {
			observer.insert(values);
		}
	}
}

package upc.bdam.recommender.wikiData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import upc.bdam.recommender.config.PropertiesLoader;

public class OntologyQueryManager {

	private static OntologyQueryManager instance = null;

	static final String ONTOLOGY_WRITER = "writer";
	static final String ONTOLOGY_BOOK = "book";
	static final String ONTOLOGY_WRITER_BOOK_RELATION = "bookWriterRelation";

	static final String ONTOLOGY_ACTOR = "actor";
	static final String ONTOLOGY_DIRECTOR = "director";
	static final String ONTOLOGY_FILM = "film";
	static final String ONTOLOGY_ACTOR_FILM_RELATION = "actorRole";
	static final String ONTOLOGY_DIRECTOR_FILM_RELATION = "filmDirector";
	
	static final String ONTOLOGY_BAND = "band";
	static final String ONTOLOGY_MUSICIAN = "musician";
	static final String ONTOLOGY_SONG = "song";
	static final String ONTOLOGY_BAND_MEMBER_RELATION = "bandMemberRelation";
	static final String ONTOLOGY_SONG_PERFORMER_RELATION = "songPerformerRelation";

	public static final byte ONTOLOGY_WRITER_QUERY = 1;
	public static final byte ONTOLOGY_BOOK_QUERY = 2;
	public static final byte ONTOLOGY_WRITER_BOOK_RELATION_QUERY = 3;
	
	public static final byte ONTOLOGY_ACTOR_QUERY = 4;
	public static final byte ONTOLOGY_DIRECTOR_QUERY = 5;
	public static final byte ONTOLOGY_FILM_QUERY = 6;
	public static final byte ONTOLOGY_ACTOR_FILM_RELATION_QUERY = 7;
	public static final byte ONTOLOGY_DIRECTOR_FILM_RELATION_QUERY = 8;
	
	public static final byte ONTOLOGY_BAND_QUERY = 9;
	public static final byte ONTOLOGY_MUSICIAN_QUERY = 10;
	public static final byte ONTOLOGY_SONG_QUERY = 11;
	public static final byte ONTOLOGY_BAND_MEMBER_RELATION_QUERY = 12;
	public static final byte ONTOLOGY_SONG_PERFORMER_RELATION_QUERY = 13;


	private OntologyQueryManager() {

	}

	public static OntologyQueryManager getInstance() {
		if (instance == null)
			instance = new OntologyQueryManager();

		return instance;
	}

	public String getQuery(byte query) throws Exception {
		switch (query) {
		case ONTOLOGY_WRITER_QUERY:
			return readQuery(PropertiesLoader.getInstance().getProperty(ONTOLOGY_WRITER));
		case ONTOLOGY_BOOK_QUERY:
			return readQuery(PropertiesLoader.getInstance().getProperty(ONTOLOGY_BOOK));
		case ONTOLOGY_WRITER_BOOK_RELATION_QUERY:
			return readQuery(PropertiesLoader.getInstance().getProperty(ONTOLOGY_WRITER_BOOK_RELATION));
		case ONTOLOGY_ACTOR_QUERY:
			return readQuery(PropertiesLoader.getInstance().getProperty(ONTOLOGY_ACTOR));
		case ONTOLOGY_DIRECTOR_QUERY:
			return readQuery(PropertiesLoader.getInstance().getProperty(ONTOLOGY_DIRECTOR));
		case ONTOLOGY_FILM_QUERY:
			return readQuery(PropertiesLoader.getInstance().getProperty(ONTOLOGY_FILM));
		case ONTOLOGY_ACTOR_FILM_RELATION_QUERY:
			return readQuery(PropertiesLoader.getInstance().getProperty(ONTOLOGY_ACTOR_FILM_RELATION));			
		case ONTOLOGY_DIRECTOR_FILM_RELATION_QUERY:
			return readQuery(PropertiesLoader.getInstance().getProperty(ONTOLOGY_DIRECTOR_FILM_RELATION));	
		case ONTOLOGY_MUSICIAN_QUERY:
			return readQuery(PropertiesLoader.getInstance().getProperty(ONTOLOGY_MUSICIAN));
		case ONTOLOGY_BAND_QUERY:
			return readQuery(PropertiesLoader.getInstance().getProperty(ONTOLOGY_BAND));	
		case ONTOLOGY_SONG_QUERY:
			return readQuery(PropertiesLoader.getInstance().getProperty(ONTOLOGY_SONG));
		case ONTOLOGY_BAND_MEMBER_RELATION_QUERY:
			return readQuery(PropertiesLoader.getInstance().getProperty(ONTOLOGY_BAND_MEMBER_RELATION));
		case ONTOLOGY_SONG_PERFORMER_RELATION_QUERY:
			return readQuery(PropertiesLoader.getInstance().getProperty(ONTOLOGY_SONG_PERFORMER_RELATION));	
		default:
			return new String();
		}
	}

	private String readQuery(String query) throws IOException {
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
}

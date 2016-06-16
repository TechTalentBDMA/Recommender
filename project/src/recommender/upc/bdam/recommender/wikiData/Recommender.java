package upc.bdam.recommender.wikiData;

import upc.bdam.recommender.documentDDBB.dao.TextAnalyticsDataSource;
import upc.bdam.recommender.graph.dao.GraphDataAccessManager;
import upc.bdam.recommender.graphupdater.schema.TextAnalyticsGraphGuard;
import upc.bdam.recommender.ontology.json.IBinding;

/**
 * Clase principal de ejecuci�n del recomendador.
 * 
 * @author Grupo 9: 
 *           - Antol�n Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David P�rez Rodr�guez
 *
 */
public class Recommender {	

	public static void main(String[] args) {
		
		try {
			Thread mongoGuard =new Thread(new TextAnalyticsGraphGuard());
			mongoGuard.start();
			TextAnalyticsDataSource dataSource=new TextAnalyticsDataSource();
			dataSource.hayNuevosDatos(123456);
//			Runtime.getRuntime().exec("C:\\Program Files\\R\\R-3.2.5\\bin\\Rscript.exe C:\\dummy.R");
//			Runtime.getRuntime().exec("C:\\Program Files\\R\\R-3.2.5\\bin\\Rscript.exe C:\\TextMining\\CodigoR\\TADocsV05.R");
//			GraphDataAccessManager.getInstance().attach(new DocumentDDBBGraphObserver());
			//getBooks();
			//getFilms();
			//getSongs();
			while (true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Obtienen los datos de libros de wikidata y los inserta en la BBDD de grafos.
	 * @throws Exception
	 */
	private static void getBooks() throws Exception{
		OntologyDataAccessObject dao=new OntologyDataAccessObject();
		
		IBinding[] value=dao.getBooks();
		GraphDataAccessManager.getInstance().insert(GraphDataAccessManager.GRAPH_BOOK_INSERT, value);
		
		value=dao.getWriters();
		GraphDataAccessManager.getInstance().insert(GraphDataAccessManager.GRAPH_WRITER_INSERT, value);
		
		value=dao.getWriterBookRelation();
		GraphDataAccessManager.getInstance().insert(GraphDataAccessManager.GRAPH_WRITER_BOOK_RELATION_INSERT, value);
		
		System.out.println(value.length);
	}
	
	/**
	 * Obtienen los datos de pel�culas de wikidata y los inserta en la BBDD de grafos.
	 * @throws Exception
	 */
	private static void getFilms() throws Exception{
		OntologyDataAccessObject dao=new OntologyDataAccessObject();
		IBinding[] value=dao.getActors();
		GraphDataAccessManager.getInstance().insert(GraphDataAccessManager.GRAPH_ACTOR_INSERT, value);
		
		value=dao.getDirector();
		GraphDataAccessManager.getInstance().insert(GraphDataAccessManager.GRAPH_DIRECTOR_INSERT, value);
		
		value=dao.getFilms();
		GraphDataAccessManager.getInstance().insert(GraphDataAccessManager.GRAPH_FILM_INSERT, value);
		
		value=dao.getActorFilmRelation();
		GraphDataAccessManager.getInstance().insert(GraphDataAccessManager.GRAPH_ACTOR_FILM_RELATION_INSERT, value);
		
		value=dao.getDirectorFilmRelation();
		GraphDataAccessManager.getInstance().insert(GraphDataAccessManager.GRAPH_DIRECTOR_FILM_RELATION_INSERT, value);
	}
	
	/**
	 * Obtienen los datos de canciones de wikidata y los inserta en la BBDD de grafos.
	 * @throws Exception
	 */
	private static void getSongs() throws Exception{
		OntologyDataAccessObject dao=new OntologyDataAccessObject();
		IBinding[] value=dao.getBand();
		GraphDataAccessManager.getInstance().insert(GraphDataAccessManager.GRAPH_BAND_INSERT, value);
		
		value=dao.getMusician();
		GraphDataAccessManager.getInstance().insert(GraphDataAccessManager.GRAPH_MUSICIAN_INSERT, value);
		
		value=dao.getSongs();
		GraphDataAccessManager.getInstance().insert(GraphDataAccessManager.GRAPH_SONG_INSERT, value);
		
		value=dao.getBandMemberRelation();
		GraphDataAccessManager.getInstance().insert(GraphDataAccessManager.GRAPH_BAND_MEMBER_RELATION_INSERT, value);
		
		value=dao.getSongPerformerRelation();
		GraphDataAccessManager.getInstance().insert(GraphDataAccessManager.GRAPH_SONG_PERFORMER_RELATION_INSERT, value);

	}
}

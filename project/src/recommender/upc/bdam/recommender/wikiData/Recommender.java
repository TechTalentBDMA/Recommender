package upc.bdam.recommender.wikiData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import upc.bdam.recommender.config.PropertiesLoader;
import upc.bdam.recommender.graph.dao.GraphDataAccessManager;
import upc.bdam.recommender.graphupdater.schema.TextAnalyticsGraphGuard;
import upc.bdam.recommender.kafka.ConsumerLoop;

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
		
		
		menu();

	}


//	private static void getBooks() throws Exception {
//		OntologyDataAccessObject dao = new OntologyDataAccessObject();
//
//		IBinding[] value = dao.getBooks();
//		GraphDataAccessManager.getInstance().insert(GraphDataAccessManager.GRAPH_BOOK_INSERT, value);
//
//		value = dao.getWriters();
//		GraphDataAccessManager.getInstance().insert(GraphDataAccessManager.GRAPH_WRITER_INSERT, value);
//
//		value = dao.getWriterBookRelation();
//		GraphDataAccessManager.getInstance().insert(GraphDataAccessManager.GRAPH_WRITER_BOOK_RELATION_INSERT, value);
//
//		System.out.println(value.length);
//	}


//	private static void getFilms() throws Exception {
//		OntologyDataAccessObject dao = new OntologyDataAccessObject();
//		IBinding[] value = dao.getActors();
//		GraphDataAccessManager.getInstance().insert(GraphDataAccessManager.GRAPH_ACTOR_INSERT, value);
//
//		value = dao.getDirector();
//		GraphDataAccessManager.getInstance().insert(GraphDataAccessManager.GRAPH_DIRECTOR_INSERT, value);
//
//		value = dao.getFilms();
//		GraphDataAccessManager.getInstance().insert(GraphDataAccessManager.GRAPH_FILM_INSERT, value);
//
//		value = dao.getActorFilmRelation();
//		GraphDataAccessManager.getInstance().insert(GraphDataAccessManager.GRAPH_ACTOR_FILM_RELATION_INSERT, value);
//
//		value = dao.getDirectorFilmRelation();
//		GraphDataAccessManager.getInstance().insert(GraphDataAccessManager.GRAPH_DIRECTOR_FILM_RELATION_INSERT, value);
//	}


//	private static void getSongs() throws Exception {
//		OntologyDataAccessObject dao = new OntologyDataAccessObject();
//		IBinding[] value = dao.getBand();
//		GraphDataAccessManager.getInstance().insert(GraphDataAccessManager.GRAPH_BAND_INSERT, value);
//
//		value = dao.getMusician();
//		GraphDataAccessManager.getInstance().insert(GraphDataAccessManager.GRAPH_MUSICIAN_INSERT, value);
//
//		value = dao.getSongs();
//		GraphDataAccessManager.getInstance().insert(GraphDataAccessManager.GRAPH_SONG_INSERT, value);
//
//		value = dao.getBandMemberRelation();
//		GraphDataAccessManager.getInstance().insert(GraphDataAccessManager.GRAPH_BAND_MEMBER_RELATION_INSERT, value);
//
//		value = dao.getSongPerformerRelation();
//		GraphDataAccessManager.getInstance().insert(GraphDataAccessManager.GRAPH_SONG_PERFORMER_RELATION_INSERT, value);
//
//	}

	/**
	 * Muestra un peque�o men� para poder observar cada una de las cargas de
	 * forma independiente
	 */
	private static void menu() {

		int opcion = 0;

		do {
			String salida = new String();
			salida += "\n***********************************************\n";
			salida += "* Que acci�n de las siguientes desea realizar:  *\n";
			salida += "* 1- Carga Inicial de grafos                    *\n";
			salida += "* 2- Consumir kafka                             *\n";
			salida += "* 3- analytics                                  *\n";
			salida += "* 4- Actualizar graph database                  *\n";
			salida += "* 5- Salir                                      *\n";
			salida += "*************************************************\n";

			System.out.println(salida);

			try {
				BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
				String aux = bufferRead.readLine();
				opcion = Integer.parseInt(aux);
			} catch (Exception ex) {
				System.out.println("Se produjo un error leyendo la opci�n. Elija de nuevo\n");
				continue;
			}

			if (opcion < 0 || opcion > 5) {
				System.out.println("Opcion incorrecta. Elija de nuevo\n");
				continue;

			} else if (opcion == 1)
				initialLoad();
			else if (opcion == 2)
				kafkaConsume();
			else if (opcion == 3)
				analytics();
			else if (opcion == 4)
				updateGraphFromBig2();
			else if (opcion == 5)
				System.exit(0);

		} while (true);
	}

	/**
	 * Se cargan todos los datos iniciales tanto en neo4j como en mongoDB. El
	 * repositorio utilizado para obtener los datos iniciales es wikidataa
	 */
	private static void initialLoad() {
		try {
			GraphDataAccessManager.getInstance().getGenres();
			GraphDataAccessManager.getInstance().getBooks();
			GraphDataAccessManager.getInstance().getFilms();
			GraphDataAccessManager.getInstance().getSongs();
			GraphDataAccessManager.getInstance().getBookRelations();;
			GraphDataAccessManager.getInstance().getFilmsRelations();
			GraphDataAccessManager.getInstance().getMusicRelations();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Lanza un hilo que se queda escuchando de forma permanente en la cola
	 * kafka.
	 */
	private static void kafkaConsume() {
		int numConsumers = 1;

		PropertiesLoader properties = PropertiesLoader.getInstance();
		String groupId = properties.getProperty(ConsumerLoop.CONSUMER_GROUP_TOPIC);
		List<String> topics = Arrays.asList(properties.getProperty(ConsumerLoop.CONSUMER_TOPIC));
		final ExecutorService executor = Executors.newFixedThreadPool(numConsumers);

		final List<ConsumerLoop> consumers = new ArrayList<ConsumerLoop>();
		for (int i = 0; i < numConsumers; i++) {
			ConsumerLoop consumer = new ConsumerLoop(groupId, topics);
			consumers.add(consumer);
			executor.submit(consumer);
		}

		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				for (ConsumerLoop consumer : consumers) {
					consumer.shutdown();
				}
				executor.shutdown();
				try {
					executor.awaitTermination(5000, TimeUnit.MILLISECONDS);
				} catch (InterruptedException e) {
				}
			}
		});

	}

	/**
	 * Se realiza el textAnalytics del contenido almacenado en Big1, y se almacena en Big2 
	 * @return
	 */
	private static void analytics(){
		try {
			Runtime.getRuntime().exec("C:\\Program Files\\R\\R-3.2.5\\bin\\Rscript.exe C:\\TextMining\\CodigoR\\TADocsV05.R");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Se revisa Big2 en busca de nuevos datos con los que actualizar la BBDD de conocimiento
	 */
	private static void updateGraphFromBig2(){		
		try {
			Thread mongoGuard =new Thread(new TextAnalyticsGraphGuard());
			mongoGuard.start();
			while (true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
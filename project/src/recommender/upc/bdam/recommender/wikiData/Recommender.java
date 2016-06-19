package upc.bdam.recommender.wikiData;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import upc.bdam.recommender.config.PropertiesLoader;
import upc.bdam.recommender.documentDDBB.dao.TextAnalyticsDataSource;
import upc.bdam.recommender.graph.dao.GraphDataAccessManager;
import upc.bdam.recommender.graphupdater.schema.TextAnalyticsGraphGuard;
import upc.bdam.recommender.kafka.ConsumerLoop;
import upc.bdam.recommender.ontology.json.IBinding;

/**
 * Clase principal de ejecución del recomendador.
 * 
 * @author Grupo 9: 
 *           - Antolín Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David Pérez Rodríguez
 *
 */
public class Recommender {	

	public static void main(String[] args) {
		
		
		menu();
		
		try {
			Thread mongoGuard =new Thread(new TextAnalyticsGraphGuard());
			mongoGuard.start();
			TextAnalyticsDataSource dataSource=new TextAnalyticsDataSource();
			dataSource.hayNuevosDatos(123456);
//			Runtime.getRuntime().exec("C:\\Program Files\\R\\R-3.2.5\\bin\\Rscript.exe C:\\dummy.R");
//			Runtime.getRuntime().exec("C:\\Program Files\\R\\R-3.2.5\\bin\\Rscript.exe C:\\TextMining\\CodigoR\\TADocsV05.R");
//			GraphDataAccessManager.getInstance().attach(new DocumentDDBBGraphObserver());

			while (true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Obtienen los datos de libros de wikidata y los inserta en la BBDD de
	 * grafos.
	 * 
	 * @throws Exception
	 */
	private static void getBooks() throws Exception {
		OntologyDataAccessObject dao = new OntologyDataAccessObject();

		IBinding[] value = dao.getBooks();
		GraphDataAccessManager.getInstance().insert(GraphDataAccessManager.GRAPH_BOOK_INSERT, value);

		value = dao.getWriters();
		GraphDataAccessManager.getInstance().insert(GraphDataAccessManager.GRAPH_WRITER_INSERT, value);

		value = dao.getWriterBookRelation();
		GraphDataAccessManager.getInstance().insert(GraphDataAccessManager.GRAPH_WRITER_BOOK_RELATION_INSERT, value);

		System.out.println(value.length);
	}

	/**
	 * Obtienen los datos de películas de wikidata y los inserta en la BBDD de
	 * grafos.
	 * 
	 * @throws Exception
	 */
	private static void getFilms() throws Exception {
		OntologyDataAccessObject dao = new OntologyDataAccessObject();
		IBinding[] value = dao.getActors();
		GraphDataAccessManager.getInstance().insert(GraphDataAccessManager.GRAPH_ACTOR_INSERT, value);

		value = dao.getDirector();
		GraphDataAccessManager.getInstance().insert(GraphDataAccessManager.GRAPH_DIRECTOR_INSERT, value);

		value = dao.getFilms();
		GraphDataAccessManager.getInstance().insert(GraphDataAccessManager.GRAPH_FILM_INSERT, value);

		value = dao.getActorFilmRelation();
		GraphDataAccessManager.getInstance().insert(GraphDataAccessManager.GRAPH_ACTOR_FILM_RELATION_INSERT, value);

		value = dao.getDirectorFilmRelation();
		GraphDataAccessManager.getInstance().insert(GraphDataAccessManager.GRAPH_DIRECTOR_FILM_RELATION_INSERT, value);
	}

	/**
	 * Obtienen los datos de canciones de wikidata y los inserta en la BBDD de
	 * grafos.
	 * 
	 * @throws Exception
	 */
	private static void getSongs() throws Exception {
		OntologyDataAccessObject dao = new OntologyDataAccessObject();
		IBinding[] value = dao.getBand();
		GraphDataAccessManager.getInstance().insert(GraphDataAccessManager.GRAPH_BAND_INSERT, value);

		value = dao.getMusician();
		GraphDataAccessManager.getInstance().insert(GraphDataAccessManager.GRAPH_MUSICIAN_INSERT, value);

		value = dao.getSongs();
		GraphDataAccessManager.getInstance().insert(GraphDataAccessManager.GRAPH_SONG_INSERT, value);

		value = dao.getBandMemberRelation();
		GraphDataAccessManager.getInstance().insert(GraphDataAccessManager.GRAPH_BAND_MEMBER_RELATION_INSERT, value);

		value = dao.getSongPerformerRelation();
		GraphDataAccessManager.getInstance().insert(GraphDataAccessManager.GRAPH_SONG_PERFORMER_RELATION_INSERT, value);

	}

	/**
	 * Muestra un pequeño menú para poder observar cada una de las cargas de
	 * forma independiente
	 */
	private static void menu() {

		int opcion = 0;

		do {
			String salida = new String();
			salida += "\n***********************************************\n";
			salida += "* Que acción de las siguientes desea realizar:  *\n";
			salida += "* 1- Carga Inicial de grafos                    *\n";
			salida += "* 2- Consumir kafka                             *\n";
			salida += "* 3- Salir                                      *\n";
			salida += "*************************************************\n";

			System.out.println(salida);

			try {
				BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
				String aux = bufferRead.readLine();
				opcion = Integer.parseInt(aux);
			} catch (Exception ex) {
				System.out.println("Se produjo un error leyendo la opción. Elija de nuevo\n");
				continue;
			}

			if (opcion < 0 || opcion > 3) {
				System.out.println("Opcion incorrecta. Elija de nuevo\n");
				continue;

			} else if (opcion == 1)
				cargaInicial();
			else if (opcion == 3)
				System.exit(0);
			else if (opcion == 2)
				kafkaConsume();
		} while (true);
	}

	/**
	 * Se cargan todos los datos iniciales tanto en neo4j como en mongoDB. El
	 * repositorio utilizado para obtener los datos iniciales es wikidataa
	 */
	private static void cargaInicial() {
		try {
			getBooks();
			getFilms();
			getSongs();
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

}

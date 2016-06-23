package upc.bdam.recommender.documentDDBB.dao;

import upc.bdam.recommender.documentDDBB.dao.json.ArtWork;
import upc.bdam.recommender.documentDDBB.dao.json.Band;
import upc.bdam.recommender.documentDDBB.dao.json.Genre;
import upc.bdam.recommender.documentDDBB.dao.json.Person;
import upc.bdam.recommender.graphupdater.schema.Big2AudioSchemaBean;
import upc.bdam.recommender.graphupdater.schema.Big2TextSchemaBean;
import upc.bdam.recommender.graphupdater.schema.Big2VideoSchemaBean;
import upc.bdam.recommender.graphupdater.schema.Big2WebSchemaBean;
import upc.bdam.recommender.kafka.KafkaBean;
import upc.bdam.recommender.ontology.json.IBinding;
import upc.bdam.recommender.ontology.json.author.Author;

/**
 * Clase de interacción entre la BBDD ontológica y el Mongo de persistencia de
 * datos
 * 
 * @author Grupo 9: - Antolín Barrena Rico - Carles Castillejo - Raffaele
 *         Ghermandi - David Pérez Rodríguez
 *
 */
public class ReplicaDataAccessObject {

	ReplicaDataSource dataSource = new ReplicaDataSource();

	// Nombre de las entidades de los modelos
	public static final String PERSON = "person";
	public static final String ARTWORK = "artwork";
	public static final String BAND = "band";
	public static final String GENRE = "genre";
	public static final String USER = "user";

	/**
	 * Inserción de los datos del usuario
	 * 
	 * @param values
	 */
	public void insertPersonDocument(IBinding[] values) {
		Person person;

		for (IBinding value : values) {
			Author graphPerson = (Author) value;
			person = new Person();
			person.setBorn(graphPerson.getBorn().getValue());
			person.setId(graphPerson.getId().getValue());
			person.setName(graphPerson.getName().getValue());

			dataSource.insertPersonDocument(person);

		}
	}

	/**
	 * Inserción de los datos de una obra
	 * 
	 * @param values
	 */
	public void insertArtWorkDocument(IBinding[] values) {
		ArtWork artwork;

		for (IBinding value : values) {
			upc.bdam.recommender.ontology.json.artwork.ArtWork graphArtWork = (upc.bdam.recommender.ontology.json.artwork.ArtWork) value;
			artwork = new ArtWork();
			artwork.setGenre(graphArtWork.getGenre().getValue());
			artwork.setId(graphArtWork.getId().getValue());
			artwork.setReleased(graphArtWork.getReleased().getValue());
			artwork.setTitle(graphArtWork.getTitle().getValue());

			dataSource.insertArtWorkDocument(artwork);
		}
	}

	/**
	 * Inserción de los datos de una obra
	 * 
	 * @param values
	 */
	public void insertBandDocument(IBinding[] values) {
		Band band;

		for (IBinding value : values) {
			upc.bdam.recommender.ontology.json.band.Band group = (upc.bdam.recommender.ontology.json.band.Band) value;
			band = new Band();
			band.setReleased(group.getReleased().getValue());
			band.setId(group.getId().getValue());
			band.setName(group.getName().getValue());

			dataSource.insertBandDocument(band);
		}
	}

	/**
	 * Inserción de los datos de una obra
	 * 
	 * @param values
	 */
	public void insertGenreDocument(IBinding[] values) {
		Genre genre;

		for (IBinding value : values) {
			upc.bdam.recommender.ontology.json.genre.Genre oGenre = (upc.bdam.recommender.ontology.json.genre.Genre) value;
			genre = new Genre();
			genre.setId(oGenre.getId().getValue());
			genre.setName(oGenre.getName().getValue());

			dataSource.insertGenreDocument(genre);
		}
	}

	public void insertUser(KafkaBean value) {
		dataSource.insertUserDocument(value);
	}

	/**
	 * Replica el contenido de audio insertado en la BBDD de conocimiento desde
	 * Big2
	 * 
	 * @param web
	 */
	public void updateAudio(Big2AudioSchemaBean value) {
		dataSource.updateAudio(value);
	}

	/**
	 * Replica el contenido de texto insertado en la BBDD de conocimiento desde
	 * Big2
	 * 
	 * @param web
	 */
	public void updateText(Big2TextSchemaBean value) {
		dataSource.updateText(value);
	}

	/**
	 * Replica el contenido de video insertado en la BBDD de conocimiento desde
	 * Big2
	 * 
	 * @param web
	 */
	public void updateVideo(Big2VideoSchemaBean value) {
		dataSource.updateVideo(value);
	}

	/**
	 * Replica el contenido web insertado en la BBDD de conocimiento desde Big2
	 * 
	 * @param web
	 */
	public void updateWeb(Big2WebSchemaBean value) {
		dataSource.updateWeb(value);
	}
}

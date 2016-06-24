package upc.bdam.recommender.wikiData;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import upc.bdam.recommender.ontology.json.IBinding;
import upc.bdam.recommender.ontology.json.artwork.ArtWorkResult;
import upc.bdam.recommender.ontology.json.artwork.RdfArtWorkResult;
import upc.bdam.recommender.ontology.json.author.AuthorResult;
import upc.bdam.recommender.ontology.json.author.RdfAuthorResult;
import upc.bdam.recommender.ontology.json.band.BandResult;
import upc.bdam.recommender.ontology.json.band.RdfBandResult;
import upc.bdam.recommender.ontology.json.genre.GenreResult;
import upc.bdam.recommender.ontology.json.genre.RdfGenreResult;
import upc.bdam.recommender.ontology.json.relation.RdfRelationResult;
import upc.bdam.recommender.ontology.json.relation.RelationResult;

/***
 * Gestor de acceso a wikidata
 * @author Grupo 9: 
 *           - Antolín Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David Pérez Rodríguez
 *
 */
public class OntologyDataAccessObject {
	//declaración del datasource a Neo4j
	OntologyDataSource dbDataAccess=new OntologyDataSource();
	
	//mapper con el que se decodifica la respuesta recibida 
	ObjectMapper mapper = new ObjectMapper();
	
	/**
	 * Inicialización de clase. La única acción realizada es configurar el mapper para que ignore los campos recibidos a null
	 */
	public OntologyDataAccessObject(){
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	/**
	 * Interfaz de consulta de géneros
	 * @return
	 * @throws Exception
	 */
	public IBinding[] getGenres() throws Exception  {
		return getGenre(OntologyQueryManager.ONTOLOGY_GENRE_QUERY);
	}

	/**
	 * Interfaz de consulta de libros
	 * @return
	 * @throws Exception
	 */
	public IBinding[] getBooks() throws Exception  {
		return getArtWork(OntologyQueryManager.ONTOLOGY_BOOK_QUERY);
	}

	/**
	 * Interfaz de consulta de escritores
	 * @return
	 * @throws Exception
	 */
	public IBinding[] getWriters() throws Exception {
		return getPerson(OntologyQueryManager.ONTOLOGY_WRITER_QUERY);
	}
	
	/**
	 * Interfaz de consulta de relaciones escritor-libro
	 * @return
	 * @throws Exception
	 */
	public IBinding[] getWriterBookRelation() throws Exception {
		return getRelation(OntologyQueryManager.ONTOLOGY_WRITER_BOOK_RELATION_QUERY);
	}
	
	/**
	 * Interfaz de consulta de actores
	 * @return
	 * @throws Exception
	 */
	public IBinding[] getActors() throws Exception{
		return getPerson(OntologyQueryManager.ONTOLOGY_ACTOR_QUERY);
	}
	
	/**
	 * Interfaz de consulta de directores
	 * @return
	 * @throws Exception
	 */
	public IBinding[] getDirector() throws Exception{
		return getPerson(OntologyQueryManager.ONTOLOGY_DIRECTOR_QUERY);
	}
	
	/**
	 * Interfaz de consulta de películas
	 * @return
	 * @throws Exception
	 */
	public IBinding[] getFilms() throws Exception{
		return getArtWork(OntologyQueryManager.ONTOLOGY_FILM_QUERY);
	}
	
	/**
	 * Interfaz de consulta de relaciones actor-película
	 * @return
	 * @throws Exception
	 */
	public IBinding[] getActorFilmRelation() throws Exception{
		return getRelation(OntologyQueryManager.ONTOLOGY_ACTOR_FILM_RELATION_QUERY);
	}
	
	/**
	 * Interfaz de consulta de relaciones director-película
	 * @return
	 * @throws Exception
	 */
	public IBinding[] getDirectorFilmRelation() throws Exception{
		return getRelation(OntologyQueryManager.ONTOLOGY_DIRECTOR_FILM_RELATION_QUERY);
	}
	
	/**
	 * Interfaz de consulta de grupos musicales
	 * @return
	 * @throws Exception
	 */
	public IBinding[] getBands() throws Exception{
		return getBand(OntologyQueryManager.ONTOLOGY_BAND_QUERY);
	}
	
	/**
	 * Interfaz de consulta de músicos
	 * @return
	 * @throws Exception
	 */
	public IBinding[] getMusician() throws Exception{
		return getPerson(OntologyQueryManager.ONTOLOGY_MUSICIAN_QUERY);
	}
	
	/**
	 * Interfaz de consulta de canciones
	 * @return
	 * @throws Exception
	 */
	public IBinding[] getSongs() throws Exception{
		return getArtWork(OntologyQueryManager.ONTOLOGY_SONG_QUERY);
	}	
	
	/**
	 * Interfaz de consulta de relaciones grupo musical - miembro
	 * @return
	 * @throws Exception
	 */
	public IBinding[] getBandMemberRelation() throws Exception{
		return getRelation(OntologyQueryManager.ONTOLOGY_BAND_MEMBER_RELATION_QUERY);
	}
	
	/**
	 * Interfaz de consulta de relaciones canción-grupo musical
	 * @return
	 * @throws Exception
	 */
	public IBinding[] getSongBandRelation() throws Exception{
		return getRelation(OntologyQueryManager.ONTOLOGY_SONG_BAND_RELATION_QUERY);
	}

	/**
	 * Interfaz de consulta de relaciones canción - intérprete
	 * @return
	 * @throws Exception
	 */
	public IBinding[] getSongPerformerRelation() throws Exception{
		return getRelation(OntologyQueryManager.ONTOLOGY_SONG_PERFORMER_RELATION_QUERY);
	}
	
	/**
	 * Interfaz de consulta de relaciones grupo musical - género
	 * @return
	 * @throws Exception
	 */
	public IBinding[] getBandGenreRelation() throws Exception{
		return getRelation(OntologyQueryManager.ONTOLOGY_BAND_GENRE_RELATION_QUERY);
	}

	/**
	 * Interfaz de consulta de relaciones canción-género
	 * @return
	 * @throws Exception
	 */
	public IBinding[] getSongGenreRelation() throws Exception{
		return getRelation(OntologyQueryManager.ONTOLOGY_SONG_GENRE_RELATION_QUERY);
	}

	/**
	 * Interfaz de consulta de relaciones libro-género
	 * @return
	 * @throws Exception
	 */
	public IBinding[] getBookGenreRelation() throws Exception{
		return getRelation(OntologyQueryManager.ONTOLOGY_BOOK_GENRE_RELATION_QUERY);
	}

	/**
	 * Interfaz de consulta de obras artísticas
	 * @return
	 * @throws Exception
	 */
	private IBinding[] getArtWork(byte query) throws Exception  {
		
		String consulta=OntologyQueryManager.getInstance().getQuery(query);
		String resultado=dbDataAccess.query(consulta);
		System.out.println(resultado);
		RdfArtWorkResult user = mapper.readValue(resultado.toString(), RdfArtWorkResult.class);
		ArtWorkResult nodeBooks = user.getResult();
		IBinding[] results = nodeBooks.getBindings();
		return results;
	}

	/**
	 * Interfaz de consulta de personas
	 * @return
	 * @throws Exception
	 */
	private IBinding[] getPerson(byte query) throws Exception {
		
		String consulta=OntologyQueryManager.getInstance().getQuery(query);
		String resultado=dbDataAccess.query(consulta);
		System.out.println(resultado);
		RdfAuthorResult user = mapper.readValue(resultado.toString(), RdfAuthorResult.class);
		AuthorResult nodeBooks = user.getResult();
		IBinding[] results = nodeBooks.getBindings();
		return results;
	}
	
	/**
	 * Interfaz de consulta de grupos musicales
	 * @return
	 * @throws Exception
	 */
	private IBinding[] getBand(byte query) throws Exception {
		
		String consulta=OntologyQueryManager.getInstance().getQuery(query);
		String resultado=dbDataAccess.query(consulta);

		RdfBandResult band = mapper.readValue(resultado.toString(), RdfBandResult.class);
		BandResult nodeBand = band.getResult();
		IBinding[] results = nodeBand.getBindings();
		return results;
	}

	/**
	 * Interfaz de consulta de géneros
	 * @return
	 * @throws Exception
	 */
	private IBinding[] getGenre(byte query) throws Exception {
		
		String consulta=OntologyQueryManager.getInstance().getQuery(query);
		String resultado=dbDataAccess.query(consulta);

		RdfGenreResult user = mapper.readValue(resultado.toString(), RdfGenreResult.class);
		GenreResult nodeGenre = user.getResult();
		IBinding[] results = nodeGenre.getBindings();
		return results;
	}

	/**
	 * Interfaz de consulta de relaciones
	 * @return
	 * @throws Exception
	 */
	private IBinding[] getRelation(byte query)throws Exception{
		
		String consulta=OntologyQueryManager.getInstance().getQuery(query);
		String resultado=dbDataAccess.query(consulta);

		RdfRelationResult user = mapper.readValue(resultado.toString(), RdfRelationResult.class);
		RelationResult nodeBooks = user.getResult();
		IBinding[] results = nodeBooks.getBindings();
		return results;
	}
}

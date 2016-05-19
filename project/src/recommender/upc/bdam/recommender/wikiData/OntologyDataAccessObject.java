package upc.bdam.recommender.wikiData;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import upc.bdam.recommender.ontology.json.IBinding;
import upc.bdam.recommender.ontology.json.artwork.ArtWorkResult;
import upc.bdam.recommender.ontology.json.artwork.RdfArtWorkResult;
import upc.bdam.recommender.ontology.json.author.AuthorResult;
import upc.bdam.recommender.ontology.json.author.RdfAuthorResult;
import upc.bdam.recommender.ontology.json.relation.RdfRelationResult;
import upc.bdam.recommender.ontology.json.relation.RelationResult;

public class OntologyDataAccessObject {
	OntologyDataSource dbDataAccess=new OntologyDataSource();
	ObjectMapper mapper = new ObjectMapper();
	
	public OntologyDataAccessObject(){
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}


	public IBinding[] getBooks() throws Exception  {
		return getArtWork(OntologyQueryManager.ONTOLOGY_BOOK_QUERY);
	}

	public IBinding[] getWriters() throws Exception {
		return getPerson(OntologyQueryManager.ONTOLOGY_WRITER_QUERY);
	}
	
	public IBinding[] getWriterBookRelation() throws Exception {
		return getRelation(OntologyQueryManager.ONTOLOGY_WRITER_BOOK_RELATION_QUERY);
	}
	
	public IBinding[] getActors() throws Exception{
		return getPerson(OntologyQueryManager.ONTOLOGY_ACTOR_QUERY);
	}
	
	public IBinding[] getDirector() throws Exception{
		return getPerson(OntologyQueryManager.ONTOLOGY_DIRECTOR_QUERY);
	}
	
	public IBinding[] getFilms() throws Exception{
		return getArtWork(OntologyQueryManager.ONTOLOGY_FILM_QUERY);
	}
	public IBinding[] getActorFilmRelation() throws Exception{
		return getRelation(OntologyQueryManager.ONTOLOGY_ACTOR_FILM_RELATION_QUERY);
	}
	
	public IBinding[] getDirectorFilmRelation() throws Exception{
		return getRelation(OntologyQueryManager.ONTOLOGY_DIRECTOR_FILM_RELATION_QUERY);
	}
	
	public IBinding[] getBand() throws Exception{
		return getPerson(OntologyQueryManager.ONTOLOGY_BAND_QUERY);
	}
	
	public IBinding[] getMusician() throws Exception{
		return getPerson(OntologyQueryManager.ONTOLOGY_MUSICIAN_QUERY);
	}
	
	public IBinding[] getSongs() throws Exception{
		return getArtWork(OntologyQueryManager.ONTOLOGY_SONG_QUERY);
	}	
	
	public IBinding[] getBandMemberRelation() throws Exception{
		return getRelation(OntologyQueryManager.ONTOLOGY_BAND_MEMBER_RELATION_QUERY);
	}
	
	public IBinding[] getSongPerformerRelation() throws Exception{
		return getRelation(OntologyQueryManager.ONTOLOGY_SONG_PERFORMER_RELATION_QUERY);
	}
	
	private IBinding[] getArtWork(byte query) throws Exception  {
		
		String consulta=OntologyQueryManager.getInstance().getQuery(query);
		String resultado=dbDataAccess.query(consulta);

		RdfArtWorkResult user = mapper.readValue(resultado.toString(), RdfArtWorkResult.class);
		ArtWorkResult nodeBooks = user.getResult();
		IBinding[] results = nodeBooks.getBindings();
		return results;
	}

	public IBinding[] getPerson(byte query) throws Exception {
		
		String consulta=OntologyQueryManager.getInstance().getQuery(query);
		String resultado=dbDataAccess.query(consulta);

		RdfAuthorResult user = mapper.readValue(resultado.toString(), RdfAuthorResult.class);
		AuthorResult nodeBooks = user.getResult();
		IBinding[] results = nodeBooks.getBindings();
		return results;
	}
	
	public IBinding[] getRelation(byte query)throws Exception{
		
		String consulta=OntologyQueryManager.getInstance().getQuery(query);
		String resultado=dbDataAccess.query(consulta);

		RdfRelationResult user = mapper.readValue(resultado.toString(), RdfRelationResult.class);
		RelationResult nodeBooks = user.getResult();
		IBinding[] results = nodeBooks.getBindings();
		return results;
	}
}

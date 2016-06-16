package upc.bdam.recommender.documentDDBB.dao;

import upc.bdam.recommender.documentDDBB.dao.json.ArtWork;
import upc.bdam.recommender.documentDDBB.dao.json.Person;
import upc.bdam.recommender.ontology.json.IBinding;
import upc.bdam.recommender.ontology.json.author.Author;

/**
 * Clase de interacci�n entre la BBDD ontol�gica y el Mongo de persistencia de datos
 * 
 * @author Grupo 9: 
 *           - Antol�n Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David P�rez Rodr�guez
 *
 */
public class DocumentDataAccessObject {
	
	DocumentDataSource dataSource=new DocumentDataSource();
	
		//Nombre de las entidades de los modelos
		public static final String PERSON="person";
		public static final String ARTWORK="artwork";

		/**
		 * Inserci�n de los datos del usuario
		 * @param values
		 */
		public void insertPersonDocument(IBinding[] values) {
			Person person;

			for (IBinding value: values){
				Author graphPerson= (Author) value;
				person=new Person();
				person.setBorn(graphPerson.getBorn().getValue());
				person.setId(graphPerson.getId().getValue());
				person.setName(graphPerson.getName().getValue());			
				
				dataSource.insertPersonDocument(person);
				
			}
		}

		/**
		 * Inserci�n de los datos de una obra
		 * @param values
		 */
		public void insertArtWorkDocument(IBinding[] values) {
			ArtWork artwork;

			for (IBinding value: values){
				upc.bdam.recommender.ontology.json.artwork.ArtWork graphArtWork= (upc.bdam.recommender.ontology.json.artwork.ArtWork) value;
				artwork=new ArtWork();
				artwork.setGenre(graphArtWork.getGenre().getValue());
				artwork.setId(graphArtWork.getId().getValue());
				artwork.setReleased(graphArtWork.getReleased().getValue());			
				artwork.setTitle(graphArtWork.getTitle().getValue());
				
				dataSource.insertCollectionArtWork(artwork);				
			}
		}
}

package upc.bdam.recommender.documentDDBB.dao.json;

/**
 * Clase que encapsula los datos relativos a un trabajo art�stico
 * @author Grupo 9: 
 *           - Antol�n Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David P�rez Rodr�guez
 *
 */
public class ArtWork {
	//declaraci�n de variables
	public String id = new String();
	public String title = new String();
	public String genre = new String();
	public String released = new String();

	//declaraci�n de m�todos getter
	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getGenre() {
		return genre;
	}

	public String getReleased() {
		return released;
	}

	//declaraci�n de m�todos setter
	public void setId(String id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setReleased(String released) {
		this.released = released;
	}

}

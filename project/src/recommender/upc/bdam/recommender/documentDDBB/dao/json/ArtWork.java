package upc.bdam.recommender.documentDDBB.dao.json;

public class ArtWork {
	public String id = new String();
	public String title = new String();
	public String genre = new String();
	public String released = new String();

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

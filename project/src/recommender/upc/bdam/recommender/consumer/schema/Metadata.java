package upc.bdam.recommender.consumer.schema;

public class Metadata {

	private long created;
	private String creator;
	private long modified;
	private String title;
	private String description;

	//De los documetnos que he probado todos estos campos vienen
	//vacíos. Así que de momento los he dejado declarados como 
	//object y no he creado sus getters y setters
	private Object creatroTool;
	private Object format;
	private Object identifier;
	private Object keywords;
	private Object language;
	private Object latitude;
	private Object longitude;
	private Object metadataDate;
	private Object modifier;
	private Object printDate;
	private Object publisher;
	private Object rating;
	private Object relation;
	private Object rights;
	private Object source;

	public long getCreated() {
		return created;
	}

	public String getCreator() {
		return creator;
	}

	public long getModified() {
		return modified;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}


	public void setCreated(long created) {
		this.created = created;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public void setModified(long modified) {
		this.modified = modified;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}

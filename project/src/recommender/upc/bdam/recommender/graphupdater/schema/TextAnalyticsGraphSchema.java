package upc.bdam.recommender.graphupdater.schema;


public class TextAnalyticsGraphSchema {
	
	private long id;
	private long userId;

	public long getId() {
		return id;
	}

	public long getUserId() {
		return userId;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
}

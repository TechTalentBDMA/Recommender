package upc.bdam.recommender.consumer.schema;

public class SchemaWebBean extends TextAnalyticsSchema {

	private String url;
	private String content;

	public String getUrl() {
		return url;
	}

	public String getContent() {
		return content;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setContent(String content) {
		this.content = content;
	}
}

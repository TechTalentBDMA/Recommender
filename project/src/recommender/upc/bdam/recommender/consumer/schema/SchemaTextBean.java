package upc.bdam.recommender.consumer.schema;

public class SchemaTextBean extends TextAnalyticsSchema {

	private String fileType;
	private String content;
	private Metadata metadata;
	private int palabras;

	public String getFileType() {
		return fileType;
	}

	public String getContent() {
		return content;
	}

	public Metadata getMetadata() {
		return metadata;
	}

	public int getPalabras() {
		return palabras;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	public void setPalabras(int palabras) {
		this.palabras = palabras;
	}

}

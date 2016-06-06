package upc.bdam.recommender.consumer.schema;

public class SchemaVideoBean extends TextAnalyticsSchema {

	private String fileType;
	private Metadata metadata;

	public String getFileType() {
		return fileType;
	}

	public Metadata getMetadata() {
		return metadata;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

}

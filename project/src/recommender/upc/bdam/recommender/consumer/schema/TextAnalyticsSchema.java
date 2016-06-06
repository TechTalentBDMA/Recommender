package upc.bdam.recommender.consumer.schema;

public abstract class TextAnalyticsSchema {
	private long timestamp;
	private String userId;
	private byte status;

	public long getTimestamp() {
		return timestamp;
	}

	public String getUserId() {
		return userId;
	}

	public byte getStatus() {
		return status;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

}

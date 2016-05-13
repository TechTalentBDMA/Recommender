package upc.bdam.recommender.connection.http;

public class RdfResult {
	
	public Head head;
	public Result results;
	public Head getHead() {
		return head;
	}
	public Result getResult() {
		return results;
	}
	public void setHead(Head head) {
		this.head = head;
	}
	public void setResult(Result result) {
		this.results = result;
	}

}

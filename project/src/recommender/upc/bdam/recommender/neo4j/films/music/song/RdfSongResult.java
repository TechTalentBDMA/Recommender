package upc.bdam.recommender.neo4j.films.music.song;

import upc.bdam.recommender.neo4j.Head;

public class RdfSongResult {
	
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

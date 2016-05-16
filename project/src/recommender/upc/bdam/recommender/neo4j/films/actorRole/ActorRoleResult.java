package upc.bdam.recommender.neo4j.films.actorRole;

import upc.bdam.recommender.neo4j.GenericResult;
import upc.bdam.recommender.neo4j.IBinding;

public class ActorRoleResult implements IBinding{
	
	public GenericResult actor;
	public GenericResult film;
	public GenericResult getActor() {
		return actor;
	}
	public GenericResult getFilm() {
		return film;
	}
	public void setActor(GenericResult actor) {
		this.actor = actor;
	}
	public void setFilm(GenericResult film) {
		this.film = film;
	}
}

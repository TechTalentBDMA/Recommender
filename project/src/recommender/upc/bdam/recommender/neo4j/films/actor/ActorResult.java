package upc.bdam.recommender.neo4j.films.actor;

import upc.bdam.recommender.neo4j.GenericResult;
import upc.bdam.recommender.neo4j.IBinding;

public class ActorResult implements IBinding{
	
	public GenericResult actor;
	public GenericResult actorLabel;
	public GenericResult dateOfBirth;
	public GenericResult getActor() {
		return actor;
	}
	public GenericResult getActorLabel() {
		return actorLabel;
	}
	public GenericResult getDateOfBirth() {
		return dateOfBirth;
	}
	public void setActor(GenericResult actor) {
		this.actor = actor;
	}
	public void setActorLabel(GenericResult actorLabel) {
		this.actorLabel = actorLabel;
	}
	public void setDateOfBirth(GenericResult dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	
}

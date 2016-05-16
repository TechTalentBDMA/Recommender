package upc.bdam.recommender.neo4j.films.director;

import upc.bdam.recommender.neo4j.GenericResult;
import upc.bdam.recommender.neo4j.IBinding;

public class DirectorResult implements IBinding{
	
	public GenericResult id;
	public GenericResult directorLabel;
	public GenericResult born; 
	public GenericResult dateOfBirth;
	public GenericResult getId() {
		return id;
	}
	public GenericResult getDirectorLabel() {
		return directorLabel;
	}
	public GenericResult getBorn() {
		return born;
	}
	public GenericResult getDateOfBirth() {
		return dateOfBirth;
	}
	public void setId(GenericResult id) {
		this.id = id;
	}
	public void setDirectorLabel(GenericResult directorLabel) {
		this.directorLabel = directorLabel;
	}
	public void setBorn(GenericResult born) {
		this.born = born;
	}
	public void setDateOfBirth(GenericResult dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	
}

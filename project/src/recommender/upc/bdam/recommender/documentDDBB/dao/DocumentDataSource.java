package upc.bdam.recommender.documentDDBB.dao;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DocumentDataSource {

	private MongoCollection<Document> films;
	private MongoCollection<Document> books;
	private MongoCollection<Document> songs;

	private MongoClient client;
	private MongoDatabase database;


	public DocumentDataSource() {
		client = new MongoClient();

		// se crea la base de datos del ejercicio y sus colecciones
		database = client.getDatabase("exercise_2_model_1");

		films = database.getCollection(DocumentDataAccessObject.FILM);
		books = database.getCollection(DocumentDataAccessObject.BOOK);
		songs = database.getCollection(DocumentDataAccessObject.SONG);
	}

}

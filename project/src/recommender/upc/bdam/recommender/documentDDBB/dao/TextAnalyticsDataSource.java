package upc.bdam.recommender.documentDDBB.dao;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import upc.bdam.recommender.consumer.schema.SchemaAudioBean;
import upc.bdam.recommender.consumer.schema.SchemaTextBean;
import upc.bdam.recommender.consumer.schema.SchemaVideoBean;
import upc.bdam.recommender.consumer.schema.SchemaWebBean;

public class TextAnalyticsDataSource {

	private static String TEXT_ANALYTICS_TIMESTAMP = "timestamp";
	private static String TEXT_ANALYTICS_FILE_TYPE = "fileType";
	private static String TEXT_ANALYTICS_USER_ID = "userId";
	private static String TEXT_ANALYTICS_STATUS = "status";
	private static String TEXT_ANALYTICS_CONTENT = "content";
	private static String TEXT_ANALYTICS_PALABRAS = "palabras";
	private static String TEXT_ANALYTICS_URL = "url";

	private static String TEXT_ANALYTICS_AUDIO_COLLECTION = "audioSchema";
	private static String TEXT_ANALYTICS_TEXT_COLLECTION = "textSchema";
	private static String TEXT_ANALYTICS_VIDEO_COLLECTION = "videoSchema";
	private static String TEXT_ANALYTICS_WEB_COLLECTION = "webSchema";

	private MongoCollection<Document> videoCollection;
	private MongoCollection<Document> audioCollection;
	private MongoCollection<Document> textCollection;
	private MongoCollection<Document> webCollection;

	private MongoClient client;
	private MongoDatabase database;

	public TextAnalyticsDataSource() {
		client = new MongoClient();

		// se crea la base de datos del ejercicio y sus colecciones
		database = client.getDatabase("textAnalyticsOutput");

		audioCollection = database.getCollection(TEXT_ANALYTICS_AUDIO_COLLECTION);
		textCollection = database.getCollection(TEXT_ANALYTICS_TEXT_COLLECTION);
		videoCollection = database.getCollection(TEXT_ANALYTICS_VIDEO_COLLECTION);
		webCollection = database.getCollection(TEXT_ANALYTICS_WEB_COLLECTION);
	}

	public void insertAudioSchema(SchemaAudioBean value) {

		Document audio = new Document();
		audio.put(TEXT_ANALYTICS_TIMESTAMP, value.getTimestamp());
		audio.put(TEXT_ANALYTICS_FILE_TYPE, value.getFileType());
		audio.put(TEXT_ANALYTICS_USER_ID, value.getUserId());
		audio.put(TEXT_ANALYTICS_STATUS, value.getStatus());

		audioCollection.insertOne(audio);
	}

	public void insertVideoSchema(SchemaVideoBean value) {

		Document video = new Document();
		video.put(TEXT_ANALYTICS_TIMESTAMP, value.getTimestamp());
		video.put(TEXT_ANALYTICS_FILE_TYPE, value.getFileType());
		video.put(TEXT_ANALYTICS_USER_ID, value.getUserId());
		video.put(TEXT_ANALYTICS_STATUS, value.getStatus());

		videoCollection.insertOne(video);
	}

	public void insertTextSchema(SchemaTextBean value) {

		Document text = new Document();
		text.put(TEXT_ANALYTICS_TIMESTAMP, value.getTimestamp());
		text.put(TEXT_ANALYTICS_FILE_TYPE, value.getFileType());
		text.put(TEXT_ANALYTICS_USER_ID, value.getUserId());
		text.put(TEXT_ANALYTICS_STATUS, value.getStatus());
		text.put(TEXT_ANALYTICS_CONTENT, value.getContent());
		text.put(TEXT_ANALYTICS_PALABRAS, value.getPalabras());
		textCollection.insertOne(text);
	}

	public void insertWebSchema(SchemaWebBean value) {

		Document web = new Document();
		web.put(TEXT_ANALYTICS_TIMESTAMP, value.getTimestamp());
		web.put(TEXT_ANALYTICS_USER_ID, value.getUserId());
		web.put(TEXT_ANALYTICS_STATUS, value.getStatus());
		web.put(TEXT_ANALYTICS_CONTENT, value.getContent());
		web.put(TEXT_ANALYTICS_URL, value.getUrl());

		webCollection.insertOne(web);
	}

}
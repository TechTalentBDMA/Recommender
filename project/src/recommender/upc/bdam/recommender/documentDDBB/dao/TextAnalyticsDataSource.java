package upc.bdam.recommender.documentDDBB.dao;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import upc.bdam.recommender.consumer.schema.Metadata;
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
	private static String TEXT_ANALYTICS_CREATED = "created";
	private static String TEXT_ANALYTICS_CREATOR = "creator";
	private static String TEXT_ANALYTICS_MODIFIED = "modified";
	private static String TEXT_ANALYTICS_TITLE = "title";
	private static String TEXT_ANALYTICS_DESCRIPTION = "description";

	private static String TEXT_ANALYTICS_AUDIO_COLLECTION = "audioSchema";
	private static String TEXT_ANALYTICS_TEXT_COLLECTION = "textSchema";
	private static String TEXT_ANALYTICS_VIDEO_COLLECTION = "videoSchema";
	private static String TEXT_ANALYTICS_WEB_COLLECTION = "webSchema";

	private MongoCollection<Document> videoCollection;
	private MongoCollection<Document> audioCollection;
	private MongoCollection<Document> textCollection;
	private MongoCollection<Document> webCollection;
	private MongoCollection<Document> texts;

	

	private MongoClient client;
	private MongoDatabase big1;
	private MongoDatabase big2;

	public TextAnalyticsDataSource() {
		client = new MongoClient();

		// se crea la base de datos del ejercicio y sus colecciones
		big1 = client.getDatabase("big1");
		big2 = client.getDatabase("big2");

		audioCollection = big1.getCollection(TEXT_ANALYTICS_AUDIO_COLLECTION);
		textCollection = big1.getCollection(TEXT_ANALYTICS_TEXT_COLLECTION);
		videoCollection = big1.getCollection(TEXT_ANALYTICS_VIDEO_COLLECTION);
		webCollection = big1.getCollection(TEXT_ANALYTICS_WEB_COLLECTION);
	}

	public void insertAudioSchema(SchemaAudioBean value) {

		Document audio = new Document();
		Metadata metadata = value.getMetadata();

		audio.put(TEXT_ANALYTICS_TIMESTAMP, value.getTimestamp());
		audio.put(TEXT_ANALYTICS_FILE_TYPE, value.getFileType());
		audio.put(TEXT_ANALYTICS_USER_ID, value.getUserId());
		audio.put(TEXT_ANALYTICS_STATUS, value.getStatus());
		audio.put(TEXT_ANALYTICS_CREATED, metadata.getCreated());
		audio.put(TEXT_ANALYTICS_CREATOR, metadata.getCreator());
		audio.put(TEXT_ANALYTICS_MODIFIED, metadata.getModified());
		audio.put(TEXT_ANALYTICS_TITLE, metadata.getTitle());
		audio.put(TEXT_ANALYTICS_DESCRIPTION, metadata.getDescription());

		audioCollection.insertOne(audio);
	}

	public void insertVideoSchema(SchemaVideoBean value) {

		Document video = new Document();
		Metadata metadata = value.getMetadata();

		video.put(TEXT_ANALYTICS_TIMESTAMP, value.getTimestamp());
		video.put(TEXT_ANALYTICS_FILE_TYPE, value.getFileType());
		video.put(TEXT_ANALYTICS_USER_ID, value.getUserId());
		video.put(TEXT_ANALYTICS_STATUS, value.getStatus());
		video.put(TEXT_ANALYTICS_CREATED, metadata.getCreated());
		video.put(TEXT_ANALYTICS_CREATOR, metadata.getCreator());
		video.put(TEXT_ANALYTICS_MODIFIED, metadata.getModified());
		video.put(TEXT_ANALYTICS_TITLE, metadata.getTitle());
		video.put(TEXT_ANALYTICS_DESCRIPTION, metadata.getDescription());

		videoCollection.insertOne(video);
	}

	public void insertTextSchema(SchemaTextBean value) {

		Document text = new Document();
		Metadata metadata = value.getMetadata();

		text.put(TEXT_ANALYTICS_TIMESTAMP, value.getTimestamp());
		text.put(TEXT_ANALYTICS_FILE_TYPE, value.getFileType());
		text.put(TEXT_ANALYTICS_USER_ID, value.getUserId());
		text.put(TEXT_ANALYTICS_STATUS, value.getStatus());
		text.put(TEXT_ANALYTICS_CONTENT, value.getContent());
		text.put(TEXT_ANALYTICS_PALABRAS, value.getPalabras());
		text.put(TEXT_ANALYTICS_CREATED, metadata.getCreated());
		text.put(TEXT_ANALYTICS_CREATOR, metadata.getCreator());
		text.put(TEXT_ANALYTICS_MODIFIED, metadata.getModified());
		text.put(TEXT_ANALYTICS_TITLE, metadata.getTitle());
		text.put(TEXT_ANALYTICS_DESCRIPTION, metadata.getDescription());
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

	public FindIterable<Document> hayNuevosDatos(long timestamp) {
		texts= big2.getCollection("texts");
		BasicDBObject gtQuery = new BasicDBObject();
		//gtQuery.put("id", new BasicDBObject("$gt", 7).append("$lt", 9));
		gtQuery.put("id", new BasicDBObject("$gt", timestamp));
		return texts.find(gtQuery);
	}

}
package upc.bdam.recommender.Big2DDBB.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import upc.bdam.recommender.graphupdater.schema.Big2AudioSchemaBean;
import upc.bdam.recommender.graphupdater.schema.Big2SGraphGuardSchema;
import upc.bdam.recommender.graphupdater.schema.Big2TextSchemaBean;
import upc.bdam.recommender.graphupdater.schema.Big2VideoSchemaBean;
import upc.bdam.recommender.graphupdater.schema.Big2WebSchemaBean;

/**
 * 
 * @author Grupo 9: - Antolín Barrena Rico - Carles Castillejo - Raffaele
 *         Ghermandi - David Pérez Rodríguez
 *
 */
public class Big2DataSource {

	// declaración de constantes de literales implicadas en la inserción.
	public static final String BIG2_TIMESTAMP_BIG2 = "big2Timestamp";
	public static final String BIG2_TIMESTAMP_ID = "id";

	private static String BIG2_TIMESTAMP = "timestamp";
	private static String BIG2_USER_ID = "userId";
	private static String BIG2_NOMBRES = "nombres";
	private static String BIG2_TEMAS = "temas";
	private static String BIG2_CLAVES = "claves";
	private static String BIG2_GENEROS = "generos";
	private static String BIG2_PALABRAS = "palabras";
	private static String BIG2_URL = "url";
	private static String BIG2_TITULO = "titulo";
	private static String BIG2_AUTOR = "autor";
	private static String BIG2_IDIOMA = "idioma";
	private static String BIG2_ISBN = "ISBN";
	private static String BIG2_CREATOR = "creator";
	private static String BIG2_YEAR = "autor";
	private static String BIG2_SUBJECT = "subject";
	private static String BIG2_TYPE = "type";
	private static String BIG2_ACTORES = "actores";
	private static String BIG2_CLASIFICACION = "clasificacion";
	private static String BIG2_DIRECCION = "direccion";
	private static String BIG2_DESCRIPCION = "direccion";
	private static String BIG2_PRODUCTOR = "productor";
	private static String BIG2_GUIONISTA = "guionista";
	private static String BIG2_ALBUM = "guionista";
	private static String BIG2_INTERPRETE = "guionista";
	private static String BIG2_COLABORADORES = "guionista";
	private static String BIG2_COMPOSITORES = "guionista";

	private static String BIG2_AUDIO_COLLECTION = "text";
	private static String BIG2_TEXT_COLLECTION = "audio";
	private static String BIG2_VIDEO_COLLECTION = "video";
	private static String BIG2_WEB_COLLECTION = "web";

	// declaraión de las listas de tipos de documentos
	private MongoCollection<Document> video;
	private MongoCollection<Document> audio;
	private MongoCollection<Document> web;
	private MongoCollection<Document> text;

	// conexiones a las instancias de MongoDB
	private MongoClient client;
	private MongoDatabase big2;

	/**
	 * Constructor de clase. Se establecen las conexiones a las instancias de
	 * MongoDB
	 */
	public Big2DataSource() {
		client = new MongoClient();

		// se crea la base de datos del ejercicio y sus colecciones
		big2 = client.getDatabase("big2");

		audio = big2.getCollection(BIG2_AUDIO_COLLECTION);
		text = big2.getCollection(BIG2_TEXT_COLLECTION);
		video = big2.getCollection(BIG2_VIDEO_COLLECTION);
		web = big2.getCollection(BIG2_WEB_COLLECTION);
	}

	/**
	 * Método con el que se consulta si existen más inserciones en Big2 desde la
	 * última lectura realizada
	 * 
	 * @param timestamp
	 * @return
	 */
	public List<Big2SGraphGuardSchema> checkBig2TextUpdate(long timestamp) {
		Big2TextSchemaBean textDocument = new Big2TextSchemaBean();
		List<Big2SGraphGuardSchema> list = new ArrayList<Big2SGraphGuardSchema>();
		text = big2.getCollection(BIG2_AUDIO_COLLECTION);
		BasicDBObject gtQuery = new BasicDBObject();
		gtQuery.put("id", new BasicDBObject("$gt", timestamp));

		FindIterable<Document> documents = text.find(gtQuery);
		for (Document document : documents) {
			textDocument.setId(document.getLong(BIG2_TIMESTAMP));
			textDocument.setAutor(document.getString(BIG2_AUTOR));
			textDocument.setClaves(document.getString(BIG2_CLAVES));
			textDocument.setCreator(document.getString(BIG2_CREATOR));
			textDocument.setGeneros(document.getString(BIG2_GENEROS));
			textDocument.setIdioma(document.getString(BIG2_IDIOMA));
			textDocument.setIsbn(document.getLong(BIG2_ISBN));
			textDocument.setNombres(document.getString(BIG2_NOMBRES));
			textDocument.setPalabras(document.getString(BIG2_PALABRAS));
			textDocument.setSubject(document.getString(BIG2_SUBJECT));
			textDocument.setTemas(document.getString(BIG2_TEMAS));
			textDocument.setTitulo(document.getString(BIG2_TITULO));
			textDocument.setType(document.getString(BIG2_TYPE));
			textDocument.setUserId(document.getLong(BIG2_USER_ID));
			textDocument.setYear(document.getInteger(BIG2_YEAR));
			list.add(textDocument);
		}

		return list;
	}

	/**
	 * Método con el que se consulta si existen más inserciones en Big2 desde la
	 * última lectura realizada
	 * 
	 * @param timestamp
	 * @return
	 */
	public List<Big2SGraphGuardSchema> checkBig2VideoUpdate(long timestamp) {
		Big2VideoSchemaBean vieoDocument = new Big2VideoSchemaBean();
		List<Big2SGraphGuardSchema> list = new ArrayList<Big2SGraphGuardSchema>();
		video = big2.getCollection(BIG2_VIDEO_COLLECTION);
		BasicDBObject gtQuery = new BasicDBObject();
		gtQuery.put("id", new BasicDBObject("$gt", timestamp));

		FindIterable<Document> documents = video.find(gtQuery);
		for (Document document : documents) {
			vieoDocument.setId(document.getLong(BIG2_TIMESTAMP));
			vieoDocument.setUserId(document.getLong(BIG2_USER_ID));
			vieoDocument.setTitulo(document.getString(BIG2_TITULO));
			vieoDocument.setActores(document.getString(BIG2_ACTORES));
			vieoDocument.setClasificacion(document.getInteger(BIG2_CLASIFICACION));

			vieoDocument.setDirector(document.getString(BIG2_DIRECCION));
			vieoDocument.setGeneros(document.getString(BIG2_GENEROS));
			vieoDocument.setGuionista(document.getString(BIG2_GUIONISTA));
			vieoDocument.setProductor(document.getString(BIG2_PRODUCTOR));
			vieoDocument.setTitulo(document.getString(BIG2_TITULO));
			vieoDocument.setYear(document.getInteger(BIG2_YEAR));
			list.add(vieoDocument);
		}

		return list;
	}

	/**
	 * Método con el que se consulta si existen más inserciones en Big2 desde la
	 * última lectura realizada
	 * 
	 * @param timestamp
	 * @return
	 */
	public List<Big2SGraphGuardSchema> checkBig2WebUpdate(long timestamp) {
		Big2WebSchemaBean webDocument = new Big2WebSchemaBean();
		List<Big2SGraphGuardSchema> list = new ArrayList<Big2SGraphGuardSchema>();
		web = big2.getCollection(BIG2_VIDEO_COLLECTION);
		BasicDBObject gtQuery = new BasicDBObject();
		gtQuery.put("id", new BasicDBObject("$gt", timestamp));

		FindIterable<Document> documents = web.find(gtQuery);
		for (Document document : documents) {
			webDocument.setId(document.getLong(BIG2_TIMESTAMP));
			webDocument.setUserId(document.getLong(BIG2_USER_ID));
			webDocument.setNombres(document.getString(BIG2_NOMBRES));
			webDocument.setTitulo(document.getString(BIG2_TITULO));
			webDocument.setTemas(document.getString(BIG2_TEMAS));
			webDocument.setTitulo(document.getString(BIG2_TITULO));
			webDocument.setUri(document.getString(BIG2_URL));
			webDocument.setDescripcion(document.getString(BIG2_DESCRIPCION));
			webDocument.setClaves(document.getString(BIG2_CLAVES));
			webDocument.setGeneros(document.getString(BIG2_GENEROS));
			list.add(webDocument);
		}

		return list;
	}

	/**
	 * Método con el que se consulta si existen más inserciones en Big2 desde la
	 * última lectura realizada
	 * 
	 * @param timestamp
	 * @return
	 */
	public List<Big2SGraphGuardSchema> checkBig2AudioUpdate(long timestamp) {
		Big2AudioSchemaBean audioDocument = new Big2AudioSchemaBean();
		List<Big2SGraphGuardSchema> list = new ArrayList<Big2SGraphGuardSchema>();
		audio = big2.getCollection(BIG2_VIDEO_COLLECTION);
		BasicDBObject gtQuery = new BasicDBObject();
		gtQuery.put("id", new BasicDBObject("$gt", timestamp));

		FindIterable<Document> documents = audio.find(gtQuery);
		for (Document document : documents) {
			audioDocument.setId(document.getLong(BIG2_TIMESTAMP));
			audioDocument.setUserId(document.getLong(BIG2_USER_ID));

			audioDocument.setAlbum(document.getString(BIG2_ALBUM));
			audioDocument.setClasificacion(document.getInteger(BIG2_CLASIFICACION));
			audioDocument.setColaboradores(document.getString(BIG2_COLABORADORES));
			audioDocument.setCompositores(document.getString(BIG2_COMPOSITORES));
			audioDocument.setGeneros(document.getString(BIG2_GENEROS));

			audioDocument.setInterprete(document.getString(BIG2_INTERPRETE));
			audioDocument.setTitulo(document.getString(BIG2_TITULO));
			audioDocument.setYear(document.getInteger(BIG2_YEAR));
			list.add(audioDocument);
		}

		return list;
	}

}
package upc.bdam.recommender.connection.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.kernel.impl.core.NodeProxy;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import upc.bdam.recommender.neo4j.IBinding;
import upc.bdam.recommender.neo4j.books.author.node2.AuthorResult;
import upc.bdam.recommender.neo4j.books.author.node2.RdfAuthorResult;
import upc.bdam.recommender.neo4j.books.book.node2.BookResult;
import upc.bdam.recommender.neo4j.books.book.node2.RdfBookResult;
import upc.bdam.recommender.neo4j.books.relation2.RdfRelationResult;
import upc.bdam.recommender.neo4j.books.relation2.RelationResult;

public class HttpURLConnector {

	File DB = new File("C:/Users/sorel/Documents/Neo4j/recommender2.db");
	GraphDatabaseService graph = null;

	enum NodeType implements Label {
		Author, Book
	}

	enum RelationTypes implements RelationshipType {
		AuthorWroteBook
	}

	public HttpURLConnector() {
		init();
	}

	private void init() {

		try {
			FileUtils.deleteDirectory(DB);
			graph = new GraphDatabaseFactory().newEmbeddedDatabase(DB);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void write() {
		Transaction tx = null;
		try {

			tx = graph.beginTx();
			
			IBinding[] resultado = obtenerAutores();
			insertarAutores(resultado, tx);

			

			resultado = obtenerLibros();
			insertarLibros(resultado, tx);

			
			resultado = obtenerRelacionEscritorAutor();
			this.insertarRelaciones(resultado, tx);
			tx.success();

		} catch (IOException ioex) {
			ioex.printStackTrace();
		} finally {
			tx.close();
		}
	}

	private IBinding[] obtenerAutores() throws IOException {
		String consulta = readQuery("C:\\Users\\sorel\\git\\upc\\Recommender\\project\\resources\\books\\nodeAuthor2");
		StringBuffer resultado = RDFQuery(consulta);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		RdfAuthorResult user = mapper.readValue(resultado.toString(), RdfAuthorResult.class);
		upc.bdam.recommender.neo4j.books.author.node2.Result nodeBooks = user.getResult();
		IBinding[] results = nodeBooks.getBindings();
		return results;
	}

	private IBinding[] obtenerLibros() throws IOException {
		String consulta = readQuery("C:\\Users\\sorel\\git\\upc\\Recommender\\project\\resources\\books\\nodeBook2");
		StringBuffer resultado = RDFQuery(consulta);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		RdfBookResult user = mapper.readValue(resultado.toString(), RdfBookResult.class);
		upc.bdam.recommender.neo4j.books.book.node2.Result nodeBooks = user.getResult();
		IBinding[] results = nodeBooks.getBindings();
		return results;
	}

	private IBinding[] obtenerRelacionEscritorAutor() throws IOException {
		String consulta = readQuery("C:\\Users\\sorel\\git\\upc\\Recommender\\project\\resources\\books\\relation2");
		StringBuffer resultado = RDFQuery(consulta);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		RdfRelationResult user = mapper.readValue(resultado.toString(), RdfRelationResult.class);
		upc.bdam.recommender.neo4j.books.relation2.Result nodeBooks = user.getResult();
		IBinding[] results = nodeBooks.getBindings();
		return results;
	}

	private StringBuffer RDFQuery(String query) throws IOException {
		String content = "https://query.wikidata.org/sparql?format=json&query=" + URLEncoder.encode(query, "UTF-8");
		URL url = new URL(content);

		URLConnection urlConnection = url.openConnection();
		urlConnection.setDoOutput(true);
		// OutputStream output = urlConnection.getOutputStream();
		InputStream input = urlConnection.getInputStream();

		StringBuffer resultado = new StringBuffer();
		int data = input.read();
		while (data != -1) {
			System.out.print((char) data);
			resultado.append((char) data);
			data = input.read();
		}
		input.close();
		return resultado;
	}

	public static void main(String arg[]) {
		HttpURLConnector con = new HttpURLConnector();
		con.write();
	}

	public String readQuery(String query) throws IOException {
		BufferedReader br = null;
		String everything = new String();
		br = new BufferedReader(new FileReader(query));
		StringBuilder sb = new StringBuilder();
		String line = br.readLine();

		while (line != null) {
			sb.append(line);
			sb.append(System.lineSeparator());
			line = br.readLine();
		}
		everything = sb.toString();
		br.close();

		return everything;
	}

	public void insertarAutores(IBinding nodeBooks[], Transaction tx) {

		for (IBinding result : nodeBooks) {
			AuthorResult author = (AuthorResult) result;

			Node node = graph.createNode(NodeType.Author);

			node.setProperty("id", author.getAuthor().getValue());
			node.setProperty("born", author.getBorn().getValue());
			node.setProperty("authorLabel", author.getAuthorLabel().getValue());
			node.setProperty("occupationLabel", author.getOccupationLabel().getValue());
		}
	}

	public void insertarLibros(IBinding nodeBooks[], Transaction tx) {

		for (IBinding result : nodeBooks) {
			BookResult book = (BookResult) result;

			Node node = graph.createNode(NodeType.Book);

			node.setProperty("id", book.getBook().getValue());
			node.setProperty("genreLabel", book.getGenreLabel().getValue());
			node.setProperty("released", book.getReleased().getValue());
			node.setProperty("titleLabel", book.getTitleLabel().getValue());
		}
	}

	public void insertarRelaciones(IBinding nodeBooks[], Transaction tx) {

		for (IBinding result : nodeBooks) {
			RelationResult relation = (RelationResult) result;

			String consultaAutor = "MATCH (c1:Author) WHERE c1.id='" + relation.getAuthor().getValue() + "' RETURN DISTINCT c1;";
			String consultaLibro = "MATCH (c1:Book) WHERE c1.id='" + relation.getBook().getValue() + "' RETURN DISTINCT c1;";
			
			NodeProxy autor=executeNeo4jQuery(consultaAutor);
			NodeProxy libro=executeNeo4jQuery(consultaLibro);
			
			if (autor!=null && libro!=null)
				autor.createRelationshipTo(libro, RelationTypes.AuthorWroteBook);		
		}
	}

	private NodeProxy executeNeo4jQuery(String query) {
		NodeProxy resultado = null;

		org.neo4j.graphdb.Result result = graph.execute(query);
		while (result.hasNext()){
			Map<String, Object> aux=result.next();
			resultado=(NodeProxy)aux.get("c1");
		}
		return resultado;

	}
}

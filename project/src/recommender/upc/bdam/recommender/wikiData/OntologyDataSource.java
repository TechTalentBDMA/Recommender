package upc.bdam.recommender.wikiData;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import upc.bdam.recommender.config.PropertiesLoader;
import upc.bdam.recommender.connection.http.HttpURLConnector;

/**
 * Clase de acceso a wikidata
 * @author Grupo 9: 
 *           - Antolín Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David Pérez Rodríguez
 *
 */
public class OntologyDataSource {

	String url = PropertiesLoader.getInstance().getProperty("wikiDataDB");

	/**
	 * Ejecución de queries mediante el servicio de acceso de wikidata
	 * @param query
	 * @return
	 */
	public String query(String query) {
		String respuesta = new String();
		try {
			HttpURLConnector conexion = new HttpURLConnector();
			String consulta = URLEncoder.encode(query, "UTF-8");
			conexion.openConnection(url+"?format=json&query="+consulta);
			//conexion.sendData();
			respuesta = conexion.receiveData();
			conexion.closeConnection();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return respuesta;
	}

/*	private IBinding[] obtenerLibros() throws IOException {
		String consulta = readQuery("C:\\Users\\sorel\\git\\upc\\Recommender\\project\\resources\\books\\nodeBook2");
		StringBuffer resultado = RDFQuery(consulta);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		RdfBookResult user = mapper.readValue(resultado.toString(), RdfBookResult.class);
		upc.bdam.recommender.neo4j.books.book.node.Result nodeBooks = user.getResult();
		IBinding[] results = nodeBooks.getBindings();
		return results;
	}

	private IBinding[] obtenerRelacionEscritorAutor() throws IOException {
		String consulta = readQuery("C:\\Users\\sorel\\git\\upc\\Recommender\\project\\resources\\books\\relation2");
		StringBuffer resultado = RDFQuery(consulta);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		RdfRelationResult user = mapper.readValue(resultado.toString(), RdfRelationResult.class);
		upc.bdam.recommender.neo4j.books.relation.Result nodeBooks = user.getResult();
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

			String consultaAutor = "MATCH (c1:Author) WHERE c1.id='" + relation.getAuthor().getValue()
					+ "' RETURN DISTINCT c1;";
			String consultaLibro = "MATCH (c1:Book) WHERE c1.id='" + relation.getBook().getValue()
					+ "' RETURN DISTINCT c1;";

			NodeProxy autor = executeNeo4jQuery(consultaAutor);
			NodeProxy libro = executeNeo4jQuery(consultaLibro);

			if (autor != null && libro != null)
				autor.createRelationshipTo(libro, RelationTypes.AuthorWroteBook);
		}
	}

	private NodeProxy executeNeo4jQuery(String query) {
		NodeProxy resultado = null;

		org.neo4j.graphdb.Result result = graph.execute(query);
		while (result.hasNext()) {
			Map<String, Object> aux = result.next();
			resultado = (NodeProxy) aux.get("c1");
		}
		return resultado;

	}*/
}

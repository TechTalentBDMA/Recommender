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
}

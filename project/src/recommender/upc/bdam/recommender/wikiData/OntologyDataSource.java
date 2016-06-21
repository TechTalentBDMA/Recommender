package upc.bdam.recommender.wikiData;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import upc.bdam.recommender.config.PropertiesLoader;
import upc.bdam.recommender.connection.http.HttpURLConnector;

/**
 * Clase de acceso a wikidata
 * @author Grupo 9: 
 *           - Antol�n Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David P�rez Rodr�guez
 *
 */
public class OntologyDataSource {

	String url = PropertiesLoader.getInstance().getProperty("wikiDataDB");

	/**
	 * Ejecuci�n de queries mediante el servicio de acceso de wikidata
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

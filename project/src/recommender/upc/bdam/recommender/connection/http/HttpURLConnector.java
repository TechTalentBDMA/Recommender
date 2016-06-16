package upc.bdam.recommender.connection.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


/**
 * Clase de acceso HTTP. Es utilizada para acceder a wikidata en busca de los datos de la carga inicial de la BBDD de 
 * grafos, y para consultas posteriores de actualización de contenidos.
 * 
 * @author Grupo 9: 
 *           - Antolín Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David Pérez Rodríguez
 *
 */
public class HttpURLConnector {

	//variable en la que se almacena la conexión.
	HttpsURLConnection connection;

	/**
	 * Abre la conexión a una URL concreta
	 * @param url
	 */
	public void openConnection(String url) {
		URL uri;
		try {
			uri = new URL(url);
			connection=(HttpsURLConnection)uri.openConnection();
			connection.setDoOutput(true);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	

	/**
	 * Envía datos al endpoint de la conexión abiert
	 * @param data
	 */
	public void sendData(String data){
		try {
			OutputStream out=connection.getOutputStream();
			out.write(data.getBytes());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Recibe datos desde el endpoint
	 * @return
	 */
	public String  receiveData(){
		InputStream input;
		StringBuffer resultado = new StringBuffer();

		try {
			input = connection.getInputStream();
			int data = input.read();
			while (data != -1) {
				//System.out.print((char) data);
				resultado.append((char) data);
				data = input.read();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return resultado.toString();
	}
	
	public void closeConnection(){
		connection.disconnect();
	}
	
/*	private StringBuffer RDFQuery(String query) throws IOException {
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
	}*/
}

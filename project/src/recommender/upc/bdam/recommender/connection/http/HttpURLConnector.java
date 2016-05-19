package upc.bdam.recommender.connection.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


public class HttpURLConnector {

	HttpsURLConnection connection;

	
	public void openConnection(String url) {
		URL uri;
		try {
			uri = new URL(url);
			connection=(HttpsURLConnection)uri.openConnection();
			connection.setDoOutput(true);
		//	connection.connect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void setPropertie(String key, String value){
		connection.setRequestProperty(key, value);
	}

	public void sendData(String data){
		try {
			OutputStream out=connection.getOutputStream();
			out.write(data.getBytes());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
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

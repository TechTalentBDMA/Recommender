package upc.bdam.recommender.connection.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpURLConnector {

	
	String query1="SELECT	* WHERE {"+
	"	?book			wdt:P31			wd:Q571 ."+
	" ?book			wdt:P50			?author ."+
	" ?author			rdfs:label		?authorLabel ."+
	" ?book			rdfs:label		?titleLabel ."+
	" ?book			wdt:P136		?genre ."+
	" ?genre			rdfs:label		?genreLabel ."+
	" ?book			wdt:P577		?publicationDate ."+
	" FILTER(lang(?authorLabel) = \"en\") ."+
	" FILTER(lang(?titleLabel) = \"en\") ."+
	" FILTER(lang(?genreLabel) = \"en\") ."+
	" FILTER(str(?authorLabel) = \"Paulo Coelho\") ."+
	" FILTER(str(?titleLabel) = \"The Alchemist\") ."+		
	" BIND(str(year(?publicationDate)) AS ?release) } limit 100";
	
	String query="SELECT"+
	" ?titleLabel"+
	" (concat('CREATE ('"+
	" ,REPLACE(str(?book), \"http://www.wikidata.org/entity/\", \"\")"+
	" ,\':Book {\'"+
	" ,\'id:\"\'"+
	" ,REPLACE(str(?book), \"http://www.wikidata.org/entity/\", \"\")"+
	" ,\'\", name:\"\'"+
	" ,?titleLabel"+
	" ,\'\", genre:\'"+
	" ,(concat(\'[\"\', group_concat(?genreLabel; separator=\'\",\"\'),\'\"]\'))"+
	" ,\', released:\"\'"+
    "	,?released"+
   	" ,\'\"})\') AS ?NodeBook)"+
" WHERE"+
" {"+

		" ?book			wdt:P31			wd:Q571 ."+
	" ?book			wdt:P50			?author ."+
	" ?author			rdfs:label		?authorLabel ."+
	"	?book			rdfs:label		?titleLabel ."+
	" ?book			wdt:P136		?genre ."+
	"	?genre			rdfs:label		?genreLabel ."+
	"	?book			wdt:P577		?publicationDate ."+
		
	" FILTER(lang(?authorLabel) = \"en\") ."+
	"	FILTER(lang(?titleLabel) = \"en\") ."+
	"	FILTER(lang(?genreLabel) = \"en\") ."+
	"	BIND(str(year(?publicationDate)) AS ?released)"+
" } "+
" GROUP BY"+
"	?book ?titleLabel ?released"+
" ORDER BY"+
"	?titleLabel";
	
	public void openConnection(){
		
		try{
			URL url = new URL("http://jenkov.com");

			URLConnection urlConnection = url.openConnection();
			InputStream input = urlConnection.getInputStream();

			int data = input.read();
			while(data != -1){
			    System.out.print((char) data);
			    data = input.read();
			}
			input.close();		
			
			
		}catch (IOException ioex){
			
		}
	}
	
	public void write(){
		try{
			String content = "https://query.wikidata.org/sparql?format=json&query=" + URLEncoder.encode(query, "UTF-8");
		URL url = new URL(content);

		URLConnection urlConnection = url.openConnection();
		urlConnection.setDoOutput(true);
		//OutputStream output = urlConnection.getOutputStream();	
		InputStream input = urlConnection.getInputStream();

		StringBuffer resultado=new StringBuffer();
		int data = input.read();
		while(data != -1){
		    System.out.print((char) data);
		    resultado.append((char) data);
		    data = input.read();
		}
		input.close();	
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		String jsonInString = "{'name' : 'mkyong'}";

		//JSON from file to Object
		//RdfResult user = mapper.readValue(new File("c:\\user.json"), ].class);

		//JSON from String to Object
		RdfResult user = mapper.readValue(resultado.toString(), RdfResult.class);
		Result nodeBooks=user.getResult();
		BookResult[] results=nodeBooks.getBindings();
	
		
	}catch (IOException ioex){
		ioex.printStackTrace();
	}
	}

	public static void main(String arg[]){
		HttpURLConnector con=new HttpURLConnector();
		con.write();
	}
}

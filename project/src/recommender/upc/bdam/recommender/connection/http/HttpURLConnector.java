package upc.bdam.recommender.connection.http;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpURLConnector {

	
	
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
			String nodeBook=this.readQuery();
			String content = "https://query.wikidata.org/sparql?format=json&query=" + URLEncoder.encode(nodeBook, "UTF-8");
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
	
	public String readQuery(){
		BufferedReader br=null;
		String everything=new String();
		try {
//			br = new BufferedReader(new FileReader("C:\\Users\\sorel\\git\\upc\\Recommender\\project\\resources\\books\\nodeAuthor"));
			br = new BufferedReader(new FileReader("C:\\Users\\sorel\\git\\upc\\Recommender\\project\\resources\\films\\ocupationRole"));
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    everything = sb.toString();
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
		}
		return everything;
	}
}

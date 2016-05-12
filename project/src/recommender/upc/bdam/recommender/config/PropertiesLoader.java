package upc.bdam.recommender.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
	
	public static final String PROPERTIES_LOADER_NEO4J_LOCATION= "neo4JDB";

	private static PropertiesLoader instance = null;
	Properties prop = new Properties();

	private PropertiesLoader() {
		init();
	}

	public static PropertiesLoader getInstance() {
		if (instance != null)
			return instance;
		else {
			instance = new PropertiesLoader();
			return instance;
		}
	}

	private void init() {

		String fileName = "localAgent.properties";
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
		try {
			if (inputStream != null)
				prop.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getProperty(String propertyName){
		return prop.getProperty(propertyName);
	}

}

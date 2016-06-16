package upc.bdam.recommender.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

/**
 * Procesa el fichero de propiedades del recomendador y proporciona interfaz de acceso a éstas.
 * @author Grupo 9: 
 *           - Antolín Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David Pérez Rodríguez
 *
 */
public class PropertiesLoader {

	//declaración de la variable de instancia.
	private static PropertiesLoader instance = null;
	Properties prop = new Properties();
	InputStream inputStream = null;

	/**
	 * Constructor privado de la clase
	 */
	private PropertiesLoader() {
		init();
	}

	/**
	 * Método estático qeu da acceso a la única instancia de la clase
	 * @return
	 */
	public static PropertiesLoader getInstance() {
		if (instance != null)
			return instance;
		else {
			instance = new PropertiesLoader();
			return instance;
		}
	}

	/**
	 * Inicialización de la clase. Se carga el fichero y se almacenan sus propiedades
	 */
	private void init() {

		String fileName = "recomender.properties";
		inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
		try {
			if (inputStream != null)
				prop.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Método de acceso a las propiedades del fichero
	 * @param propertyName
	 * @return
	 */
	public String getProperty(String propertyName) {
		return prop.getProperty(propertyName);
	}

	/**
	 * Método para almacenar en el fichero de propiedades. 
	 * 
	 * Se utiliza fundamentalmente para la guarda de actualización  de datos en Big1. Cada cierto tiempo se consultará 
	 * Big1 en búsqueda de actualizaciones. Entre lecturas se guarda en el fichero de propiedades el último timestamp
	 * utilizado.
	 * 
	 * @param propertyName
	 * @param value
	 */
	public void putProperty(String propertyName, Long value) {
		try {
			FileOutputStream output = null;
			URL uri = getClass().getClassLoader().getResource("recomender.properties");
			output = new FileOutputStream(new File(uri.toURI()));
			prop.put(propertyName, value.toString());

			prop.store(output, "comentario");
			output.flush();
			output.close();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

}

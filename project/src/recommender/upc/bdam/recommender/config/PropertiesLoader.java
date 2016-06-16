package upc.bdam.recommender.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

/**
 * Procesa el fichero de propiedades del recomendador y proporciona interfaz de acceso a �stas.
 * @author Grupo 9: 
 *           - Antol�n Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David P�rez Rodr�guez
 *
 */
public class PropertiesLoader {

	//declaraci�n de la variable de instancia.
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
	 * M�todo est�tico qeu da acceso a la �nica instancia de la clase
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
	 * Inicializaci�n de la clase. Se carga el fichero y se almacenan sus propiedades
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
	 * M�todo de acceso a las propiedades del fichero
	 * @param propertyName
	 * @return
	 */
	public String getProperty(String propertyName) {
		return prop.getProperty(propertyName);
	}

	/**
	 * M�todo para almacenar en el fichero de propiedades. 
	 * 
	 * Se utiliza fundamentalmente para la guarda de actualizaci�n  de datos en Big1. Cada cierto tiempo se consultar� 
	 * Big1 en b�squeda de actualizaciones. Entre lecturas se guarda en el fichero de propiedades el �ltimo timestamp
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

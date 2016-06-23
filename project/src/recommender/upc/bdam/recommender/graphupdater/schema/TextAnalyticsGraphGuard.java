package upc.bdam.recommender.graphupdater.schema;

import java.util.List;
import java.util.Random;

import upc.bdam.recommender.Big2DDBB.dao.Big2AccessManager;
import upc.bdam.recommender.Big2DDBB.dao.Big2DataSource;
import upc.bdam.recommender.config.PropertiesLoader;
import upc.bdam.recommender.graph.dao.GraphDataAccessManager;

/**
 * Clase encargada de velar por las actualizaciones que se realicen en Big2
 * @author Grupo 9: 
 *           - Antolín Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David Pérez Rodríguez
 *
 */
public class TextAnalyticsGraphGuard implements Runnable {

	public void run() {
		try {
			Random random = new Random();
			Long timestamp;
			String value = PropertiesLoader.getInstance().getProperty(Big2DataSource.BIG2_TIMESTAMP_BIG2);
			if (value == null)
				timestamp = new Long("1");
			else
				timestamp = new Long(value);

			while (true) {
				
				List<Big2SGraphGuardSchema> textUpdates =Big2AccessManager.getInstance().checkBig2TextUpdate(timestamp);
				List<Big2SGraphGuardSchema> videoUpdates =Big2AccessManager.getInstance().checkBig2VideoUpdate(timestamp);
				List<Big2SGraphGuardSchema> audio =Big2AccessManager.getInstance().checkBig2AudioUpdate(timestamp);
				List<Big2SGraphGuardSchema> web =Big2AccessManager.getInstance().checkBig2WebUpdate(timestamp);

				insertaNodo(textUpdates,timestamp);
				insertaNodo(audio,timestamp);
				insertaNodo(videoUpdates,timestamp);
				insertaNodo(web,timestamp);


				Thread.sleep(random.nextInt(6000));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void insertaNodo(List<Big2SGraphGuardSchema> actualizaciones, long timestamp){
		for (Big2SGraphGuardSchema document : actualizaciones) {
			//en este punto hay que insertar en la BBDD de conocimiento
			GraphDataAccessManager.getInstance().insertBig2Node((Big2TextSchemaBean)document);
			PropertiesLoader.getInstance().putProperty(Big2DataSource.BIG2_TIMESTAMP_BIG2, document.getId());
		}
	}	
}

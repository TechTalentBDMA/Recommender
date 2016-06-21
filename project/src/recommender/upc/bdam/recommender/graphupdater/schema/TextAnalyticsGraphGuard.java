package upc.bdam.recommender.graphupdater.schema;

import java.util.Random;

import org.bson.Document;

import com.mongodb.client.FindIterable;

import upc.bdam.recommender.config.PropertiesLoader;
import upc.bdam.recommender.documentDDBB.dao.TextAnalyticsDataSource;

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
			String value = PropertiesLoader.getInstance().getProperty(TextAnalyticsDataSource.TEXT_ANALYTICS_TIMESTAMP_BIG2);
			if (value == null)
				timestamp = new Long("1");
			else
				timestamp = new Long(value);

			while (true) {
				TextAnalyticsDataSource tadao = new TextAnalyticsDataSource();
				FindIterable<Document> actualizaciones = tadao.hayNuevosDatos(timestamp);

				for (Document document : actualizaciones) {
					System.out.println(document.get(TextAnalyticsDataSource.TEXT_ANALYTICS_TIMESTAMP_ID));
					timestamp = new Long((Integer)document.get(TextAnalyticsDataSource.TEXT_ANALYTICS_TIMESTAMP_ID));
					PropertiesLoader.getInstance().putProperty(TextAnalyticsDataSource.TEXT_ANALYTICS_TIMESTAMP_BIG2, timestamp);
				}
				Thread.sleep(random.nextInt(6000));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}

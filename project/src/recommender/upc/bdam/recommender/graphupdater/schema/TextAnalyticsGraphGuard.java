package upc.bdam.recommender.graphupdater.schema;

import java.util.Random;

import org.bson.Document;

import com.mongodb.client.FindIterable;

import upc.bdam.recommender.config.PropertiesLoader;
import upc.bdam.recommender.documentDDBB.dao.TextAnalyticsDataSource;

public class TextAnalyticsGraphGuard implements Runnable {

	public void run() {
		try {
			Random random = new Random();
			Long timestamp;
			String value = PropertiesLoader.getInstance().getProperty("big2Timestamp");
			if (value == null)
				timestamp = new Long("1");
			else
				timestamp = new Long(value);

			while (true) {
				TextAnalyticsDataSource tadao = new TextAnalyticsDataSource();
				FindIterable<Document> actualizaciones = tadao.hayNuevosDatos(timestamp);

				for (Document document : actualizaciones) {
					System.out.println(document.get("id"));
					timestamp = new Long((Integer)document.get("id"));
					PropertiesLoader.getInstance().putProperty("big2Timestamp", timestamp);
				}
				Thread.sleep(random.nextInt(6000));

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}

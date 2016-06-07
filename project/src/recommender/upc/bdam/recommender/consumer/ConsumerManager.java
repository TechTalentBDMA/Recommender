package upc.bdam.recommender.consumer;

import upc.bdam.recommender.consumer.schema.SchemaAudioBean;
import upc.bdam.recommender.consumer.schema.SchemaTextBean;
import upc.bdam.recommender.consumer.schema.SchemaVideoBean;
import upc.bdam.recommender.consumer.schema.SchemaWebBean;
import upc.bdam.recommender.documentDDBB.dao.DocumentDataAccessManager;

public class ConsumerManager {

	private static ConsumerManager instance = null;

	private ConsumerManager() {

	}

	public static final ConsumerManager getInstance() {
		if (instance == null) {
			instance = new ConsumerManager();
			return instance;
		} else
			return instance;
	}

	public void consumeText(SchemaTextBean text) {
		DocumentDataAccessManager.getInstance().consumeText(text);
	}

	public void consumeWeb(SchemaVideoBean web) {
		DocumentDataAccessManager.getInstance().consumeWeb(web);
	}

	public void consumeAudio(SchemaAudioBean audio) {
		DocumentDataAccessManager.getInstance().consumeAudio(audio);
	}

	public void consumeVideo(SchemaWebBean video) {
		DocumentDataAccessManager.getInstance().consumeVideo(video);
	}
}

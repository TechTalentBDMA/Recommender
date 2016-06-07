package upc.bdam.recommender.documentDDBB.dao;

import upc.bdam.recommender.consumer.schema.SchemaAudioBean;
import upc.bdam.recommender.consumer.schema.SchemaTextBean;
import upc.bdam.recommender.consumer.schema.SchemaVideoBean;
import upc.bdam.recommender.consumer.schema.SchemaWebBean;
import upc.bdam.recommender.consumer.schema.TextAnalyticsSchema;

public class TextAnalyticsDocumentAccesObject {

	private TextAnalyticsDataSource textAnalyticsDS=new TextAnalyticsDataSource();
	public void consumeText(TextAnalyticsSchema text) {
		SchemaTextBean textSchema = (SchemaTextBean) text;
		textAnalyticsDS.insertTextSchema(textSchema);
	}

	public void consumeWeb(TextAnalyticsSchema video) {
		SchemaWebBean webSchema = (SchemaWebBean) video;
		textAnalyticsDS.insertWebSchema(webSchema);
	}

	public void consumeAudio(TextAnalyticsSchema audio) {
		SchemaAudioBean audioSchema = (SchemaAudioBean) audio;
		textAnalyticsDS.insertAudioSchema(audioSchema);
	}

	public void consumeVideo(TextAnalyticsSchema web) {
		SchemaVideoBean audioSchema = (SchemaVideoBean) web;
		textAnalyticsDS.insertVideoSchema(audioSchema);
	}
}

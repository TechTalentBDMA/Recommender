package upc.bdam.recommender.documentDDBB.dao;

import upc.bdam.recommender.consumer.schema.SchemaAudioBean;
import upc.bdam.recommender.consumer.schema.SchemaTextBean;
import upc.bdam.recommender.consumer.schema.SchemaVideoBean;
import upc.bdam.recommender.consumer.schema.SchemaWebBean;
import upc.bdam.recommender.consumer.schema.TextAnalyticsSchema;

public class TextAnalyticsDocumentAccesObject {

	public void consumeText(TextAnalyticsSchema text) {
		SchemaTextBean textSchema = (SchemaTextBean) text;
	}

	public void consumeWeb(TextAnalyticsSchema video) {
		SchemaWebBean webSchema = (SchemaWebBean) video;
	}

	public void consumeAudio(TextAnalyticsSchema audio) {
		SchemaAudioBean audioSchema = (SchemaAudioBean) audio;
	}

	public void consumeVideo(TextAnalyticsSchema web) {
		SchemaVideoBean audioSchema = (SchemaVideoBean) web;
	}
}

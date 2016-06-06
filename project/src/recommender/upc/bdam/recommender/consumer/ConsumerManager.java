package upc.bdam.recommender.consumer;

import upc.bdam.recommender.consumer.schema.SchemaAudioBean;
import upc.bdam.recommender.consumer.schema.SchemaTextBean;
import upc.bdam.recommender.consumer.schema.SchemaVideoBean;
import upc.bdam.recommender.consumer.schema.SchemaWebBean;

public class ConsumerManager {
	
	private static ConsumerManager instance=null;
	
	private ConsumerManager (){
		
	}

	
	public static final ConsumerManager getInstance(){
		if (instance==null){
			instance=new ConsumerManager();
			return instance;
		}else
			return instance;
		
	}
	
	public void consumeText(SchemaTextBean text){
		
	}
	
	public void consumeWeb(SchemaVideoBean video){
		
	}
	
	public void consumeAudio(SchemaAudioBean audio){
		
	}
	
	public void consumeVideo(SchemaWebBean web){
		
	}
}

package upc.bdam.recommender.kafka;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.smile.SmileFactory;

/**
 * Clsae que serializa y deserializa a binario el contenido que se encolan y 
 * desencolan en kafka
 * @author Grupo 9: 
 *           - Antol�n Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David P�rez Rodr�guez
 *
 */
public class KafkaEncoder{
	
	ObjectMapper mapper = new ObjectMapper(new SmileFactory());
	
	/**
	 * Serializaci�n de contenidos
	 * @param bean
	 * @return
	 */
	public byte[] serialize(KafkaBean bean) {
		byte[]result=null;
	    try {
	        result= mapper.writeValueAsBytes(bean);
	    }
	    catch(JsonProcessingException e) {
	        e.printStackTrace();;
	    }

	    return result;
	}
	
	/**
	 * Deserializaci�n de contenidos
	 * @param bytes
	 * @return
	 */
	public KafkaBean deserialize(byte[] bytes) {
		KafkaBean aux=null;
	    try {
	        aux= mapper.readValue(bytes, KafkaBean.class);
	    }
	    catch(IOException e) {
	        e.printStackTrace();
	    }
	    return aux;
	}
}

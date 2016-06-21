package upc.bdam.recommender.kafka;

import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;

import com.fasterxml.jackson.databind.deser.std.StringDeserializer;

import upc.bdam.recommender.config.PropertiesLoader;
import upc.bdam.recommender.consumer.ConsumerManager;

/**
 * Clase encargada de extraer los mensajes encolados en kafka. Recibe los mensajes e invoca las clases encargadas de 
 * generar la BBDD Big1, en la que se almacenan todos los datos recibidos de los agentes. De esta forma evitamos tener
 * el cuello de botella a la entrada del recomendador.
 * 
 * @author Grupo 9: 
 *           - Antolín Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David Pérez Rodríguez
 *
 */
public class ConsumerLoop implements Runnable {
	//declaración de constantes
	private static final String CONSUMER_SERVERS_PROPERTY="bootstrap.servers";
	private static final String CONSUMER_ID_GROUP_PROPERTY="group.id";
	private static final String CONSUMER_KEY_DESERIALIZER_PROPERTY="key.deserializer";
	private static final String CONSUMER_VALUE_DESERIALIZER_PROPERTY="value.deserializer";
	private static final String CONSUMER_SERVER_CONNECTION="serverConnection";
	public static final String CONSUMER_GROUP_TOPIC="home-group";
	public static final String CONSUMER_TOPIC="home";
	
	//declaración de variables.
	private final KafkaConsumer<String, byte[]> consumer;
	private final List<String> topics;

	/**
	 * Método especializado en consumir la cola kafka. Se consumen los mensajes identificados en el groupId y de los 
	 * topics recibidos como parámetro.
	 * 
	 * @param groupId.
	 * @param topics
	 */
	public ConsumerLoop(String groupId, List<String> topics) {
		PropertiesLoader properties=PropertiesLoader.getInstance();
		this.topics = topics;
		Properties props = new Properties();
		props.put(properties.getProperty(CONSUMER_SERVERS_PROPERTY), properties.getProperty(CONSUMER_SERVER_CONNECTION));
		props.put(properties.getProperty(CONSUMER_ID_GROUP_PROPERTY), groupId);
		props.put(properties.getProperty(CONSUMER_KEY_DESERIALIZER_PROPERTY), StringDeserializer.class.getName());
		props.put(properties.getProperty(CONSUMER_VALUE_DESERIALIZER_PROPERTY), ByteArrayDeserializer.class.getName());
		this.consumer = new KafkaConsumer<String, byte[]>(props);
	}

	/**
	 * Desencadena el consumo de los mensajes en la cola kafka
	 */
	public void run() {
		try {
			KafkaEncoder encoder=new KafkaEncoder();
			KafkaBean bean=new KafkaBean();
			//se susbrice al conjunto de topics que se quieren escuchar.
			consumer.subscribe(topics);

			//por cada mensaje recibido se escribe en BBDD Big1
			while (true) {
				ConsumerRecords<String, byte[]> records = consumer.poll(Long.MAX_VALUE);
				for (ConsumerRecord<String, byte[]> record : records) {
//					Map<String, Object> data = new HashMap<String, Object>();
//					data.put("partition", record.partition());
//					data.put("offset", record.offset());
					bean=encoder.deserialize(record.value());
					ConsumerManager.getInstance().consume(bean);
//					data.put("value", record.value());
					
				}
			}
		} catch (WakeupException e) {
			// ignore for shutdown
		} finally {
			consumer.close();
		}
	}


	/**
	 * Se deja de escuchar en la cola kafka
	 */
	public void shutdown() {
		consumer.wakeup();
	}
}
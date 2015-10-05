package http_to_kafka;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class KafkaConfiguration {
	String topic;
	String servers;
	String keySerializer;
	String valueSerializer;
	
	public Map<String, Object> asProducerConfigMap() {
		HashMap<String, Object> map = new HashMap<>();
		map.put("bootstrap.servers", getServers());
		map.put("key.serializer", getKeySerializer());
		map.put("value.serializer", getValueSerializer());
		return map;
	}
}

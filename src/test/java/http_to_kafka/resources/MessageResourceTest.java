package http_to_kafka.resources;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.kafka.clients.producer.MockProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Charsets;

public class MessageResourceTest {

	MessageResource testObj;
	MockProducer kafkaProducer = new MockProducer();
	
	@Before
	public void setUp() throws Exception {
		testObj = MessageResource
				.builder()
				.producer(kafkaProducer)
				.topic("topic")
				.build();
	}

	@Test
	public void itSendsPayloadToKafka() {
		testObj.post("dummy");
		ProducerRecord<byte[], byte[]> expected = new ProducerRecord<byte[], byte[]>(
				"topic", "dummy".getBytes(Charsets.UTF_8));
		assertThat(kafkaProducer.history()).hasSize(1);
		ProducerRecord<byte[], byte[]> actual = kafkaProducer.history().get(0);
		assertThat(actual).isEqualToComparingFieldByField(expected);
	}

}

package http_to_kafka;

import http_to_kafka.resources.MessageResource;
import io.dropwizard.Application;
import io.dropwizard.lifecycle.Managed;
import io.dropwizard.setup.Environment;

import org.apache.kafka.clients.producer.KafkaProducer;

public class Main extends Application<AppConfiguration> implements Managed {

	private KafkaProducer<byte[], byte[]> kafkaProducer;

	public static void main(String[] args) throws Exception {
		new Main().run(args);
	}
	
	@Override
	public void run(AppConfiguration configuration, Environment environment) throws Exception {
		kafkaProducer = new KafkaProducer<byte[], byte[]>(
				configuration.getKafka().asProducerConfigMap());
		MessageResource messageResource = MessageResource
				.builder()
				.producer(kafkaProducer)
				.topic(configuration.getKafka().getTopic())
				.build();
		environment.jersey().register(messageResource);
	}

	@Override
	public void start() throws Exception {
		// noop
	}

	@Override
	public void stop() throws Exception {
		if (kafkaProducer != null) {
			kafkaProducer.close();
		}
	}



}

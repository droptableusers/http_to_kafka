package http_to_kafka;

import io.dropwizard.Configuration;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppConfiguration extends Configuration {
	KafkaConfiguration kafka;
}

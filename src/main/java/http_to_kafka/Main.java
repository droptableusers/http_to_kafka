package http_to_kafka;

import http_to_kafka.resources.MessageResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class Main extends Application<AppConfiguration>{

	@Override
	public void run(AppConfiguration configuration, Environment environment) throws Exception {
		MessageResource messageResource = new MessageResource();
		environment.jersey().register(messageResource);
	}

}

package acceptance;

import static org.assertj.core.api.Assertions.assertThat;
import http_to_kafka.AppConfiguration;
import http_to_kafka.Main;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.ClassRule;
import org.junit.Test;

public class AcceptanceTest {

	@ClassRule
	public static final DropwizardAppRule<AppConfiguration> RULE =
		new DropwizardAppRule<AppConfiguration>(Main.class, ResourceHelpers.resourceFilePath("config.yaml"));
	
	@Test
	public void appReceivesHttpPostMessages() {
		Client client = new JerseyClientBuilder(RULE.getEnvironment()).build("test client");
		
		Response response = client
			.target(String.format("http://localhost:%d/message", RULE.getLocalPort()))
			.request()
			.post(Entity.json("hello"));
		
		assertThat(response.getStatus()).isEqualTo(Status.ACCEPTED.getStatusCode());
	}
	
	@Test
	public void inputMustBeJson() {
		Client client = new JerseyClientBuilder(RULE.getEnvironment()).build("test client 2");
		
		Response response = client
			.target(String.format("http://localhost:%d/message", RULE.getLocalPort()))
			.request()
			.post(Entity.text("hello"));
		
		assertThat(response.getStatus()).isEqualTo(Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode());		
	}
	
}

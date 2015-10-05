package http_to_kafka.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import lombok.Builder;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.google.common.base.Charsets;

@Path("/message")
@Builder
public class MessageResource {
	Producer<byte[],byte[]> producer;
	String topic;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response post(String json) {
		producer.send(new ProducerRecord<byte[], byte[]>(topic, json.getBytes(Charsets.UTF_8)));
		return Response.accepted().build();
	}
}

package http_to_kafka.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/message")
public class MessageResource {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response post(String json) {
		return Response.accepted().build();
	}
}

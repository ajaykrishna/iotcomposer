/**
 * 
 */
package fr.inria.convecs.iotcomposer.resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import fr.inria.convecs.iotcomposer.model.ConnectedObject;
import fr.inria.convecs.iotcomposer.service.ModelService;

/**
 * @author ajayk
 *
 */
@Path("/models")
public class ModelResource {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ModelResource.class);

	@GET
	@Path("/all")
	@Produces("application/json")
	public Response getall() throws IOException {
		List<ConnectedObject> objects = new ArrayList<ConnectedObject>();
		ModelService service = new ModelService();
		objects = service.getAllModels();
		
		ObjectMapper objectMapper = new ObjectMapper();
		ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
		String result = objectWriter.writeValueAsString(objects);
		
		return Response.status(200).entity(result).build();
	}
	
	
	@GET
	@Path("/id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("id") String id) throws IOException {
		ConnectedObject object = new ConnectedObject();
		ModelService service = new ModelService();		
		object = service.getModelByName(id); 
		
		ObjectMapper objectMapper = new ObjectMapper();
		ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
		String result = objectWriter.writeValueAsString(object);
		
		return Response.status(200).entity(result).build();
	}
}
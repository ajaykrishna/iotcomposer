/**
 * 
 */
package fr.inria.convecs.iotcomposer.resource;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.inria.convecs.iotcomposer.model.BindingDto;
import fr.inria.convecs.iotcomposer.model.EvaluatorResult;
import fr.inria.convecs.iotcomposer.service.CompatibilityService;

/**
 * @author ajayk
 *
 */

@Path("/compat")
public class CompatibilityResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(CompatibilityResource.class);

	@POST
	@Path("/check")
	@Produces("application/json")
	@Consumes("application/json")
	public Response getResult(String bindingJson) throws IOException {

		//TODO:remove
		String location = "data/lnt";

		ObjectMapper objectMapper = new ObjectMapper();
		List<BindingDto> bindingDtoList = objectMapper
				.readValue(bindingJson, objectMapper.getTypeFactory().constructCollectionType(List.class, BindingDto.class));

		CompatibilityService service = new CompatibilityService();
		service.generateSpec(bindingDtoList);
		EvaluatorResult result = service.getResult(location);

		return Response.status(200).entity(result).build();
	}
}

/**
 * 
 */
package fr.inria.convecs.iotcomposer.resource;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import fr.inria.convecs.iotcomposer.model.EvaluatorResult;
import fr.inria.convecs.iotcomposer.service.CompatibilityService;

/**
 * @author ajayk
 *
 */

@Path("/compat")
public class CompatibilityResource {
	
	@GET
	@Path("/check")
	@Produces("application/json")
	public Response getResult() throws IOException {
		String id = "\\a";
		EvaluatorResult result = new EvaluatorResult(); 
		CompatibilityService service = new CompatibilityService();
		result = service.getResult(id);
		return Response.status(200).entity(result).build();
	}
}

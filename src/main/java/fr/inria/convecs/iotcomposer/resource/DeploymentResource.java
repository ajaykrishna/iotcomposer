/**
 * 
 */
package fr.inria.convecs.iotcomposer.resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.jgrapht.io.ExportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import fr.inria.convecs.iotcomposer.model.BindingDto;
import fr.inria.convecs.iotcomposer.model.DeploymentPlan;
import fr.inria.convecs.iotcomposer.service.DeploymentPlanService;
import fr.inria.convecs.iotcomposer.util.ComposerExceptionMapper;

/**
 * @author ajayk
 *
 */
 
@Path("/deployment")
public class DeploymentResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(DeploymentResource.class);

	@Path("/dependency")
	@POST
	@Produces("text/plain")
	@Consumes("application/json")
	public Response generateDependencyGraph(String bindingJson) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			List<BindingDto> bindingDtoList = objectMapper.readValue(bindingJson, objectMapper.getTypeFactory().constructCollectionType(List.class, BindingDto.class));

			Set<String> objects = new HashSet<String>();

			bindingDtoList.forEach(b -> {
				objects.add(b.getSource().split("-")[0]);
				objects.add(b.getTarget().split("-")[0]);
			});

			DeploymentPlanService deploymentService = new DeploymentPlanService();
			String graph = deploymentService.generateDepependencyGraphinDot(bindingDtoList, objects);


			return Response.status(200).entity(graph).build();
		} catch(Exception e) {
			throw ComposerExceptionMapper.createWebAppException(e);
		}
	}

	@Path("/plan")
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public Response getDeploymentPlan(String bindingJson) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			List<BindingDto> bindingDtoList = objectMapper
					.readValue(bindingJson, objectMapper.getTypeFactory().constructCollectionType(List.class, BindingDto.class));

			Set<String> objects = new HashSet<String>();

			bindingDtoList.forEach(b -> {
				objects.add(b.getSource().split("#")[0]);
				objects.add(b.getTarget().split("#")[0]);
			});

			DeploymentPlanService deploymentService = new DeploymentPlanService();

			DeploymentPlan plan = deploymentService
					.generateDeploymentPlan(bindingDtoList, objects);


			ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
			String result = objectWriter.writeValueAsString(plan);

			return Response.status(200).entity(result).build();
		} catch(Exception e) {
			throw ComposerExceptionMapper.createWebAppException(e);
		}
	}
}

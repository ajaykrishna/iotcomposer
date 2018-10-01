/**
 * 
 */
package fr.inria.convecs.iotcomposer.resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import fr.inria.convecs.iotcomposer.model.Binding;
import fr.inria.convecs.iotcomposer.model.DeploymentPlan;
import fr.inria.convecs.iotcomposer.model.Step;
import fr.inria.convecs.iotcomposer.model.Step.StepOperation;
import fr.inria.convecs.iotcomposer.service.MajordHomeService;

/**
 * @author ajayk
 *
 */

@Path("/majo")
public class MajordHomeResource {

	static final Logger LOGGER = LoggerFactory.getLogger(MajordHomeResource.class);
	
	private MajordHomeService service = new MajordHomeService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/devices")
	public Response getDeviceList () throws JsonParseException, JsonMappingException, IOException {

		List<String> devices = service.getDeviceList();

		ObjectMapper objectMapper = new ObjectMapper();
		ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
		String result = objectWriter.writeValueAsString(devices);

		return Response.status(200).entity(result).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/vspaces")
	public Response getVspaces () throws JsonParseException, JsonMappingException, IOException {

		List<String> devices = service.getListVspace();

		ObjectMapper objectMapper = new ObjectMapper();
		ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
		String result = objectWriter.writeValueAsString(devices);

		return Response.status(200).entity(result).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/vspace/{id}")
	public Response getDevices (@PathParam("id") String id) throws JsonParseException, JsonMappingException, IOException {

		List<String> devices = service.getDeviceByVspace(id);

		ObjectMapper objectMapper = new ObjectMapper();
		ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
		String result = objectWriter.writeValueAsString(devices);

		return Response.status(200).entity(result).build();
	}

	@Path("/deploy")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createVo(DeploymentPlan plan) throws JsonProcessingException {

		MajordHomeService service = new MajordHomeService();
		List<String> respValues = new ArrayList<String>();

		for (Step step: plan.getSteps()) {
			if (step.getOperation().equals(StepOperation.BIND)) {
				Binding binding = plan.getBindings().stream()
						.filter(b -> b.getId()
								.equals(step.getElement()))
						.findFirst().get();
				String resp = service.createCoCo(binding.getSource().getId().split("@")[0], binding.getTarget().getId().split("@")[0]);
				respValues.add(resp);
			}
		}
		ObjectMapper objectMapper = new ObjectMapper();
		ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
		String result = objectWriter.writeValueAsString(respValues);

		return Response.status(200).entity(result).build();
	}

}

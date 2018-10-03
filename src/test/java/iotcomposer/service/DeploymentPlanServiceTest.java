package iotcomposer.service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.core.Response;

import org.jgrapht.io.ExportException;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import fr.inria.convecs.iotcomposer.model.BindingDto;
import fr.inria.convecs.iotcomposer.model.DeploymentPlan;
import fr.inria.convecs.iotcomposer.resource.MajordHomeResource;
import fr.inria.convecs.iotcomposer.service.DeploymentPlanService;
import fr.inria.convecs.iotcomposer.service.MajordHomeService;

public class DeploymentPlanServiceTest {

	static final Logger LOGGER = LoggerFactory.getLogger(DeploymentPlanServiceTest.class);

	@Test
	public void testDeploymentPlan() throws JsonParseException, JsonMappingException, IOException {

		DeploymentPlanService service = new DeploymentPlanService();

		ObjectMapper objectMapper = new ObjectMapper();

		File bindFile = new File(DeploymentPlanServiceTest.class.getResource("/models/bindings.json").getFile());
		List<BindingDto> bindings = objectMapper
				.readValue(bindFile, 
						objectMapper.getTypeFactory().constructCollectionType(List.class, BindingDto.class));

		//File objectFiles = new File(DeploymentPlanServiceTest.class.getResource("/models").getFile());

		List<String> objects = Arrays.asList("VoId_121_Savoc", "VoId_126_Savoc", "VoId_135_Savoc");

		Set objSet = new HashSet<String>(objects);

		List<String> dependency = service.generateDependencyList(bindings, objSet);

		DeploymentPlan plan = service.generateDeploymentPlan(bindings, objSet);

		LOGGER.debug("dependency: {}", dependency);

		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = objectWriter.writeValueAsString(plan);

		LOGGER.debug("plan: {}", json);

		//MajordHomeResource majordHomeResource = new MajordHomeResource();
		//Response resp = majordHomeResource.createVo(plan);
		//String respValue = resp.readEntity(String.class);	
		//LOGGER.debug("respvalue: {}", respValue);
	}
	
	@Test
	public void testDependency() throws JsonParseException, JsonMappingException, IOException, ExportException {
		DeploymentPlanService service = new DeploymentPlanService();

		ObjectMapper objectMapper = new ObjectMapper();

		File bindFile = new File(DeploymentPlanServiceTest.class.getResource("/models/sensor-thermo/bindings.json").getFile());
		List<BindingDto> bindings = objectMapper
				.readValue(bindFile, 
						objectMapper.getTypeFactory().constructCollectionType(List.class, BindingDto.class));
		List<String> objects = Arrays.asList("sensor-thermo/lightsensor", "sensor-thermo/thermostat");

		Set objSet = new HashSet<String>(objects);
		
		Set<String> objectNames = new HashSet<String>();
		objectNames.add("lightsensor");
		objectNames.add("thermostat");

		List<String> dependency = service.generateDependencyList(bindings, objectNames);
		
		String graph = service.generateDepependencyGraphinDot(bindings, objectNames);

		//DeploymentPlan plan = service.generateDeploymentPlan(bindings, objSet);

		LOGGER.debug("dependency: {}", dependency);
		LOGGER.debug("graph: {}", graph);

	}
}

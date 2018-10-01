package iotcomposer.service;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import fr.inria.convecs.iotcomposer.model.ConnectedObject;
import fr.inria.convecs.iotcomposer.service.ModelService;

public class ModelServiceTest {
	
	@Test
	public void getModelTest() throws JsonParseException, JsonMappingException, IOException {
		ModelService service = new ModelService();
		ConnectedObject co = service.getModelByName("VoId_121_Savoc");
		
		ObjectMapper objectMapper = new ObjectMapper();
		ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
		String actual = objectWriter.writeValueAsString(co);
		
		System.out.println(actual);
	}
	
	@Test
	public void getAllModelsTest() throws IOException {
		
		ModelService service = new ModelService();
		List<ConnectedObject> coList = service.getAllModels();
		
		ObjectMapper objectMapper = new ObjectMapper();
		ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
		String actual = objectWriter.writeValueAsString(coList);
		
		System.out.println(actual);
		
	}

}

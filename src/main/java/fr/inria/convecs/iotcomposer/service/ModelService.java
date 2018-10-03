/**
 * 
 */
package fr.inria.convecs.iotcomposer.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.inria.convecs.iotcomposer.model.ConnectedObject;

/**
 * @author ajayk
 *
 */
public class ModelService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ModelService.class);
	
	public ConnectedObject getModelByName(String name) throws JsonParseException, JsonMappingException, IOException {
		
		File object = new File(ModelService.class.getResource("/models"+"/"+name+".json").getFile());
		ObjectMapper objectMapper = new ObjectMapper();
		ConnectedObject co = objectMapper.readValue(object, ConnectedObject.class);
		return co;
	}
	
	public List<ConnectedObject> getAllModels() throws IOException {
		
		List<ConnectedObject> coList = new ArrayList<ConnectedObject>();
		
		File dataFiles = new File(ModelService.class.getResource("/models").getFile());
		
		/*List<Path> fileNames = Files.walk(dataFiles.toPath())
	     .filter(s -> s.toString().endsWith(".json"))
	     .map(Path::getFileName)
	     .sorted()
	     .collect(Collectors.toList());
	
		
		for(Path name: fileNames) {
			//coList.add(getModelByName(name.toString()));
		}
		*/
		
		for (String name: Arrays.asList(dataFiles.list())) {
			coList.add(getModelByName(name));
		}
		
		return coList;
	}

}

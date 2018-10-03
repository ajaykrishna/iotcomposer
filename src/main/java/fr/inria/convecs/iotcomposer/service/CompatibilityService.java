/**
 * 
 */
package fr.inria.convecs.iotcomposer.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import fr.inria.convecs.iotcomposer.model.BindingDto;
import fr.inria.convecs.iotcomposer.model.ConnectedObject;
import fr.inria.convecs.iotcomposer.model.EvaluatorResult;
import fr.inria.convecs.iotcomposer.resource.ModelResource;
import fr.inria.convecs.iotcomposer.transform.LntMainProcessGenerator;
import fr.inria.convecs.iotcomposer.transform.LntProcessGenerator;
import fr.inria.convecs.iotcomposer.util.CommandExecutor;

/**
 * @author ajayk
 *
 */
public class CompatibilityService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CompatibilityService.class);
	
	public void generateSpec(List<BindingDto> bindings) throws JsonParseException, JsonMappingException, IOException {
		String location;
		
		//generate process
		Set<String> objects = new HashSet<String>();
		
		for(BindingDto dto: bindings) {
			objects.add(dto.getSource().split("-")[0]);
			objects.add(dto.getTarget().split("-")[0]);
		}
		
		ModelService modelService = new ModelService();
		
		List<ConnectedObject> cos = new ArrayList<>();
		
		for(String object:objects) {
			ConnectedObject co = modelService.getModelByName(object);
			LntProcessGenerator processgenerator = new LntProcessGenerator(co);
			processgenerator.generateLntProcess();
			cos.add(co);
		}
		
		//rename
		List<BindingDto> renamedBindings = new ArrayList<>();
		
		for(BindingDto dto: bindings) {
			BindingDto bdto = new BindingDto();
			bdto.setId(dto.getId());
			bdto.setSource(dto.getSource().split("-")[1]);
			bdto.setTarget(dto.getTarget().split("-")[1]);
			bdto.setType(dto.getType());
		}
		
		LntMainProcessGenerator lntMainGen = new LntMainProcessGenerator(renamedBindings, cos);
		lntMainGen.generateProdAll();
		lntMainGen.generateFlower();
		lntMainGen.generateProdFlowerMain();
		lntMainGen.generateProdHideMain();
	}
	
	public EvaluatorResult getResult(String id) {
		String command = "svl compat.svl";
		List<String> commandList = new ArrayList<String>();
		commandList.add(command);

		EvaluatorResult result = new EvaluatorResult();
		StringBuilder messages = new StringBuilder();

		CommandExecutor executor = new CommandExecutor(commandList, new File(id));
		int execResult = executor.executeCommand();

		String stdOut = executor.getOutput().trim();
		String stdErr = executor.getErrors().trim();

		if (null != stdErr && !stdErr.isEmpty()) 
		{
			//no error
		} 
		else if (null != stdOut && !stdOut.isEmpty()) 
		{
			if (stdOut.contains("ERROR")) 
			{
				messages.append(stdOut);
				result.setResult(false);

			} 
			else 
			{
				String lastLine = stdOut.substring(stdOut.lastIndexOf("\n")).trim();
				if (lastLine.equalsIgnoreCase("TRUE")) 
				{
					result.setResult(true);
				} 
				else
				{
					result.setResult(false);
				}

				messages.append(stdOut);
			}
		} 
		else 
		{
			messages.append("*** Unable to process request - No Result Available ***");
			result.setResult(false);
		}

		result.setMessage(messages.toString());
		
		return result;

	}

}

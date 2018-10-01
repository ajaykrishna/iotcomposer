/**
 * 
 */
package fr.inria.convecs.iotcomposer.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import fr.inria.convecs.iotcomposer.model.EvaluatorResult;
import fr.inria.convecs.iotcomposer.util.CommandExecutor;

/**
 * @author ajayk
 *
 */
public class CompatibilityService {

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

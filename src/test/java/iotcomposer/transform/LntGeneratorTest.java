/**
 * 
 */
package iotcomposer.transform;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.inria.convecs.iotcomposer.model.BindingDto;
import fr.inria.convecs.iotcomposer.model.ConnectedObject;
import fr.inria.convecs.iotcomposer.service.ModelService;
import fr.inria.convecs.iotcomposer.transform.LntMainProcessGenerator;
import fr.inria.convecs.iotcomposer.transform.LntProcessGenerator;
import fr.inria.convecs.iotcomposer.util.Timer;
import iotcomposer.service.DeploymentPlanServiceTest;

/**
 * @author ajayk
 *
 */
public class LntGeneratorTest {

	@Test
	public void testLntProcessGenerator() throws JsonParseException, JsonMappingException, IOException {

		Timer timer = new Timer();

		ModelService service = new ModelService();
		ConnectedObject co1 = service.getModelByName("sensor-thermo/sensor");

		LntProcessGenerator lpg1 = new LntProcessGenerator(co1);
		lpg1.generateLntProcess();

		ConnectedObject co2 = service.getModelByName("sensor-thermo/thermostat");

		LntProcessGenerator lpg2 = new LntProcessGenerator(co2);
		lpg2.generateLntProcess();

		//ConnectedObject co3 = service.getModelByName("VoId_135_Savoc");

		//LntProcessGenerator lpg3 = new LntProcessGenerator(co3);
		//lpg3.generateLntProcess();

		List<ConnectedObject> coList = new ArrayList<>();
		coList.add(co1);
		coList.add(co2);
		//coList.add(co3);

		ObjectMapper objectMapper = new ObjectMapper();
		File bindFile = new File(DeploymentPlanServiceTest.class.getResource("/models/sensor-thermo/bindings.json").getFile());
		List<BindingDto> bindings = objectMapper
				.readValue(bindFile, 
						objectMapper.getTypeFactory().constructCollectionType(List.class, BindingDto.class));
		
		LntMainProcessGenerator lmg = new LntMainProcessGenerator(bindings, coList);
		lmg.generateFlower();
		lmg.generateProdAll();
		lmg.generateProdFlowerMain();
		lmg.generateProdHideMain();
		
		System.out.println(timer.getElapsedTime());
	}

}

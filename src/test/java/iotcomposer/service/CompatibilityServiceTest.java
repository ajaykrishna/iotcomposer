package iotcomposer.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fr.inria.convecs.iotcomposer.model.EvaluatorResult;
import fr.inria.convecs.iotcomposer.service.CompatibilityService;

class CompatibilityServiceTest {

	@Test
	void testCompatibility() {
		CompatibilityService service = new CompatibilityService();
		
		EvaluatorResult result = service.getResult("/lnt/");
		
		System.out.println(result.getMessage());
	}

}

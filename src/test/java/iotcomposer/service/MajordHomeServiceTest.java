/**
 * 
 */
package iotcomposer.service;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import fr.inria.convecs.iotcomposer.model.CoDto;
import fr.inria.convecs.iotcomposer.service.MajordHomeService;

/**
 * @author ajayk
 *
 */
public class MajordHomeServiceTest {
	
	@Test
	public void testCoDto() throws JsonParseException, JsonMappingException, IOException {
		
		MajordHomeService service = new MajordHomeService();
		
		List<CoDto> cos = service.getDeviceByVspace("lr");
		
	}
}

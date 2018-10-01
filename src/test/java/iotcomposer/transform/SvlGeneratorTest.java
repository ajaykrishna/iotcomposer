/**
 * 
 */
package iotcomposer.transform;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import fr.inria.convecs.iotcomposer.transform.SvlGenerator;

/**
 * @author ajayk
 *
 */
public class SvlGeneratorTest {
	
	@Test
	public void testLntProcessGenerator() throws IOException {
		
		SvlGenerator generator = new SvlGenerator();
		generator.generateSvl();
		
	}
}

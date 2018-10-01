/**
 * 
 */
package fr.inria.convecs.iotcomposer.transform;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author ajayk
 *
 */
public class SvlGenerator {

	public void generateSvl() throws IOException {

		Path path = Paths.get("data/lnt/compat.svl");
		StringBuilder contentString = new StringBuilder();
		try (BufferedWriter writer = Files.newBufferedWriter(path)) {
			writer.write("% CAESAR_OPEN_OPTIONS=\"-silent -warning\"");
			writer.write(System.lineSeparator());
			writer.write("% CAESAR_OPTIONS=\"-more cat\"");
			writer.write(System.lineSeparator());
			writer.write("\"prodflowermain.bcg\" = generation of \"prodflowermain.lnt\";");
			writer.write(System.lineSeparator());
			writer.write("\"prodhidemain.bcg\" = generation of \"prodhidemain.lnt\";\n\n");
			writer.write(System.lineSeparator());
			writer.write("% bcg_open \"prodflowermain.bcg\" bisimulator -equal -branching -diag \"prodhidemain.bcg\"");
			writer.write(System.lineSeparator());
		}

	}

}

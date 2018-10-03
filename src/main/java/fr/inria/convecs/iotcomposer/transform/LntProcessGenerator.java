/**
 * 
 */
package fr.inria.convecs.iotcomposer.transform;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.inria.convecs.iotcomposer.model.AppInterface;
import fr.inria.convecs.iotcomposer.model.ConnectedObject;
import fr.inria.convecs.iotcomposer.model.State;
import fr.inria.convecs.iotcomposer.model.Transition;
import fr.inria.convecs.iotcomposer.util.ComposerExceptionMapper;

/**
 * @author ajayk
 *
 */
public class LntProcessGenerator {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LntProcessGenerator.class);

	private ConnectedObject cObject;

	public LntProcessGenerator(ConnectedObject cObject) {
		this.cObject = cObject;
	}

	public void generateLntProcess() throws IOException {
		String objectId = cObject.getId();
		Path path = Paths.get("data/lnt/"+objectId+".lnt");
		StringBuilder contentString = new StringBuilder();
		List<String> interfaces = cObject.getAppInterfaces()
				.stream()
				.map(AppInterface::getId)
				.collect(Collectors.toList());
		try (BufferedWriter writer = Files.newBufferedWriter(path)) {
			contentString.append("module ")
			.append(objectId).append(" with \"get\" is \n");
			writer.append(contentString.toString());
			for(State state: cObject.getLts().getStates()) {
				String process = buildProcess(state, interfaces);
				writer.append(process);
			}
			writer.write("end module");
		}
	}

	private String buildProcess(State state, List<String> interfaces) {
		StringBuilder contentString = new StringBuilder();
		contentString.append(" process ")
		.append(state.getId()).append("[");
		contentString.append(buildParams(interfaces, true));
		contentString.append("] is \n");

		List<Transition> transitions = state.getTransitions();
		if(transitions.size() < 1) {
			//TODO: handle
		}
		else if(transitions.size() == 1) {
			contentString.append("  ")
			.append(transitions.get(0).getAction().getId());
			contentString.append("; \n");
			contentString.append("  ")
			.append(transitions.get(0).getTarget().getId());
			contentString.append("[").append(buildParams(interfaces, false))
			.append("] \n");
		}
		else {
			contentString.append("  select\n");
			boolean first = true;
			for (Transition t:transitions) {
				if (first) {
					first = false;
				} else {
					contentString.append("  []\n");
				}
				contentString.append("  ")
				.append(t.getAction().getId());
				contentString.append("; \n");
				contentString.append("  ")
				.append(transitions.get(0).getTarget().getId());
				contentString.append("[").append(buildParams(interfaces, false))
				.append("] \n");
			}
			contentString.append("  end select\n");
		}
		contentString.append(" end process \n");
		return contentString.toString();
	}

	public static String buildParams(List<String> gates, boolean withAny) {
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for(String name:gates) {
			if(first) {
				first = false;
			}
			else {
				sb.append(",");
			}
			sb.append(name);
			if(withAny) {
				sb.append(":any");
			}
		}
		return sb.toString();
	}
}

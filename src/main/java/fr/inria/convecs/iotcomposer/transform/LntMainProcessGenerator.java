/**
 * 
 */
package fr.inria.convecs.iotcomposer.transform;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import fr.inria.convecs.iotcomposer.model.AppInterface;
import fr.inria.convecs.iotcomposer.model.BindingDto;
import fr.inria.convecs.iotcomposer.model.ConnectedObject;

/**
 * @author ajayk
 *
 */
public class LntMainProcessGenerator {

	private List<BindingDto> bindings;
	private List<ConnectedObject> objects;

	public LntMainProcessGenerator(List<BindingDto> bindings, List<ConnectedObject> objects) {
		this.bindings = bindings;
		this.objects = objects;
	}

	public void generateProdAll() throws IOException {
		Path path = Paths.get("data/lnt/prodall.lnt");
		StringBuilder contentString = new StringBuilder();
		try (BufferedWriter writer = Files.newBufferedWriter(path)) {
			contentString.append("module prodall(");
			String imports = String.join(",", objects
					.stream().map(ConnectedObject::getId)
					.collect(Collectors.toList()));
			contentString.append(imports).append(") with \"get\" is \n");
			contentString.append("process prod [");
			contentString.append(LntProcessGenerator
					.buildParams(new ArrayList<>(getAllInterfaces()), true));
			contentString.append("] is \n");
			boolean first = true;
			contentString.append("par\n");
			for(ConnectedObject obj:objects) {
				List<String> interfaces = obj.getAppInterfaces()
						.stream()
						.map(AppInterface::getId)
						.collect(Collectors.toList());
				if(first) {
					first = false;
				}
				else {
					contentString.append("|| \n");
				}
				contentString.append(String.join(", ", getBoundInterfaces(obj)));
				contentString.append(" ->")
				.append(obj.getLts().getInitialState().getId());
				contentString.append("[")
				.append(LntProcessGenerator.buildParams(interfaces, false));
				contentString.append("]");
			}
			contentString.append("end par\n");
			contentString.append("end process\n");
			contentString.append("end module");
			writer.write(contentString.toString());
		}
	}

	public void generateFlower() throws IOException {
		Path path = Paths.get("data/lnt/flower.lnt");
		StringBuilder contentString = new StringBuilder();
		try (BufferedWriter writer = Files.newBufferedWriter(path)) {
			contentString.append("module flower with \"get\" is \n");
			contentString.append("process flower_idle [");
			contentString.append(LntProcessGenerator
					.buildParams(new ArrayList<>(getBoundInterfaces()), true));
			contentString.append("] is \n");
			boolean first = true;
			contentString.append("loop \n");
			contentString.append("select \n");
			for(String obj:getBoundInterfaces()) {
				if(first) {
					first = false;
				}
				else {
					contentString.append("[] \n");
				}
				contentString.append(obj).append("\n");
			}
			contentString.append("end select\n");
			contentString.append("end loop\n");
			contentString.append("end process\n");
			contentString.append("end module");
			writer.write(contentString.toString());
		}
	}
	
	public void generateProdFlowerMain() throws IOException {
		Path path = Paths.get("data/lnt/prodflowermain.lnt");
		StringBuilder contentString = new StringBuilder();
		try (BufferedWriter writer = Files.newBufferedWriter(path)) {
			contentString
			.append("module prodflowermain(prodall, flower) with \"get\" is \n");
			contentString.append("process MAIN [");
			contentString.append(LntProcessGenerator
					.buildParams(new ArrayList<>(getAllInterfaces()), true));
			contentString.append("] is \n");
			boolean first = true;
			contentString.append("par ");
			//contentString.append(String.join(",", getBoundInterfaces()));
			contentString.append(LntProcessGenerator
					.buildParams(new ArrayList<>(getAllInterfaces()), false));
			contentString.append(" in \n");
			contentString.append("flower_idle[");
			contentString.append(String.join(",", getBoundInterfaces()))
						.append("] \n")
						.append("|| \n");
			contentString.append("prod[");
			contentString.append(String.join(",", getAllInterfaces()))
						.append("] \n");
			contentString.append("end par\n");
			contentString.append("end process\n");
			contentString.append("end module");
			writer.write(contentString.toString());
		}
	}
	
	public void generateProdHideMain() throws IOException {
		Path path = Paths.get("data/lnt/prodhidemain.lnt");
		StringBuilder contentString = new StringBuilder();
		try (BufferedWriter writer = Files.newBufferedWriter(path)) {
			contentString
			.append("module prodhidemain(prodall) with \"get\" is \n");
			contentString.append("process MAIN [");
			contentString.append(LntProcessGenerator
					.buildParams(new ArrayList<>(getAllInterfaces()), true));
			contentString.append("] is \n");
			boolean first = true;
			contentString.append("hide ");
			contentString.append(LntProcessGenerator.
					buildParams(new ArrayList<>(getUnboundInterfaces()), true));
			contentString.append(" in \n");
			contentString.append("prod [");
			contentString.append(LntProcessGenerator.
					buildParams(new ArrayList<>(getAllInterfaces()), false));
			contentString.append("] \n")
						.append("end hide \n");
			contentString.append("end process\n");
			contentString.append("end module");
			writer.write(contentString.toString());
		}
	}


	private Set<String> getBoundInterfaces() {
		Set<String> boundItfs = new HashSet<>();
		for(ConnectedObject obj: objects) {
			boundItfs.addAll(getBoundInterfaces(obj));
		}
		return boundItfs;
	}

	private Set<String> getUnboundInterfaces() {
		Set<String> complement = new HashSet<>(getAllInterfaces());
		complement.removeAll(getBoundInterfaces());
		return complement;
	}

	private Set<String> getBoundInterfaces(ConnectedObject object) {
		Set<String> objectInterfaces = object.getAppInterfaces()
				.stream().map(AppInterface::getId)
				.collect(Collectors.toSet());

		Set<String> boundItf = new HashSet<>();

		for(BindingDto b: bindings) {
			boundItf.add(b.getSource());
			boundItf.add(b.getTarget());
		}

		Set<String> intersection = new HashSet<>(objectInterfaces);
		intersection.retainAll(boundItf);

		return intersection;
	}

	private Set<String> getAllInterfaces() {
		Set<String> interfaces = new HashSet<String>();
		for(ConnectedObject object: objects) {
			interfaces = object.getAppInterfaces().stream()
					.map(AppInterface::getId)
					.collect(Collectors.toSet());
		}
		return interfaces;
	}
}

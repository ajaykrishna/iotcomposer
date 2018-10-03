/**
 * 
 */
package fr.inria.convecs.iotcomposer.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.io.DOTExporter;
import org.jgrapht.io.ExportException;
import org.jgrapht.io.IntegerComponentNameProvider;
import org.jgrapht.traverse.TopologicalOrderIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import fr.inria.convecs.iotcomposer.model.AppInterface;
import fr.inria.convecs.iotcomposer.model.Binding;
import fr.inria.convecs.iotcomposer.model.BindingDto;
import fr.inria.convecs.iotcomposer.model.ConnectedObject;
import fr.inria.convecs.iotcomposer.model.DeploymentPlan;
import fr.inria.convecs.iotcomposer.model.Step;
import fr.inria.convecs.iotcomposer.model.Step.StepOperation;
import fr.inria.convecs.iotcomposer.resource.MajordHomeResource;

/**
 * @author ajayk
 *
 */
public class DeploymentPlanService {

	static final Logger LOGGER = LoggerFactory.getLogger(DeploymentPlanService.class);

	public List<String> generateDependencyList(List<BindingDto> bindings, Set<String> objects) {
		DefaultDirectedGraph<String, DefaultEdge> graph;

		graph = new DefaultDirectedGraph<String, DefaultEdge>(
				DefaultEdge.class);
		objects.forEach(obj -> graph.addVertex(obj));
		bindings.forEach(b -> graph.addEdge(b.getSource().split("-")[0], b.getTarget().split("-")[0]));

		String connObj;
		TopologicalOrderIterator<String, DefaultEdge> orderIterator =
				new TopologicalOrderIterator<String, DefaultEdge>(graph);
		List<String> dependencyList = new ArrayList<String>();
		while (orderIterator.hasNext()) {
			connObj = orderIterator.next();
			dependencyList.add(connObj);
		}

		Collections.reverse(dependencyList);

		return dependencyList;
	}

	public DeploymentPlan generateDeploymentPlan(List<BindingDto> bindings, Set<String> objects) throws JsonParseException, JsonMappingException, IOException
	{
		List<ConnectedObject> coList = new ArrayList<ConnectedObject>();

		for(String objectName:objects) {
			ConnectedObject co = getConnectedObject(objectName);
			coList.add(co);
		}

		List<Binding> bindingList = getBindings(bindings);

		List<Step> steps = getDeploymentSteps(bindingList, generateDependencyList(bindings, objects));

		DeploymentPlan plan = new DeploymentPlan();
		plan.setBindings(bindingList);
		plan.setObjects(coList);
		plan.setSteps(steps);
		return plan;
	}

	public String generateDepependencyGraphinDot(List<BindingDto> bindings, Set<String> objects) throws ExportException, UnsupportedEncodingException {
		DefaultDirectedGraph<String, DefaultEdge> graph;

		graph = new DefaultDirectedGraph<String, DefaultEdge>(
				DefaultEdge.class);
		objects.forEach(obj -> graph.addVertex(obj));
		bindings.forEach(b -> graph.addEdge(b.getSource().split("-")[0], b.getTarget().split("-")[0]));

		DOTExporter<String, DefaultEdge> exporter = new DOTExporter<>(

				vertex -> vertex, null, new IntegerComponentNameProvider<DefaultEdge>());

		ByteArrayOutputStream os = new ByteArrayOutputStream();

		exporter.exportGraph(graph, os);

		String dotGraph = new String(os.toByteArray(), "UTF-8");
		
		return dotGraph;
	}

	private List<Step> getDeploymentSteps(List<Binding> bindingList, List<String> dependencyList) {

		List<Step> steps = new ArrayList<>();
		//ADD
		for(String obj:dependencyList) {
			Step step = new Step();
			step.setId(RandomStringUtils.randomAlphanumeric(5));
			step.setOperation(StepOperation.ADD);
			step.setElement(obj);
			steps.add(step);
		}

		//BIND
		for (String obj: dependencyList) {
			List<String> bindId = findTargetBind(bindingList, obj);
			for (String id: bindId) {
				Step step = new Step();
				step.setId(RandomStringUtils.randomAlphanumeric(5));
				step.setElement(id);
				step.setOperation(StepOperation.BIND);
				steps.add(step);
			}
		}

		//START
		for(String obj:dependencyList) {
			Step step = new Step();
			step.setId(RandomStringUtils.randomAlphanumeric(5));
			step.setOperation(StepOperation.START);
			step.setElement(obj);
			steps.add(step);
		}

		return steps;
	}

	private List<String> findTargetBind(List<Binding> bindingList, String obj) {
		List<String> bindings = new ArrayList<String>();
		bindingList.forEach(b -> {
			if (b.getTarget().getId()!= null && b.getTarget().getId()
					.split("@")[0].equals(obj)) {
				bindings.add(b.getId());
			}
		});
		return bindings;
	}

	private List<Binding> getBindings(List<BindingDto> bindings) throws IOException {
		List<Binding> bindingList = new ArrayList<Binding>();

		for(BindingDto dto:bindings) {
			String[] sourceNames = dto.getSource().split("#");
			AppInterface sourceItf = getObjectInterface(sourceNames[0], sourceNames[1]);

			String[] targetNames = dto.getTarget().split("#");
			AppInterface targetItf = getObjectInterface(targetNames[0], targetNames[1]);

			Binding b = new Binding();
			b.setId(dto.getId());
			b.setSource(sourceItf);
			b.setTarget(targetItf);
			b.setType(dto.getType());

			bindingList.add(b);

		}

		return bindingList;
	}

	private ConnectedObject getConnectedObject(String objectName) throws JsonParseException, JsonMappingException, IOException {
		ModelService modelService = new ModelService();

		return modelService.getModelByName(objectName);
	}

	private AppInterface getObjectInterface(String objectName, String interfaceName) throws IOException {

		ModelService modelService = new ModelService();

		ConnectedObject co = modelService.getModelByName(objectName);

		AppInterface itf = co.getAppInterfaces().stream().filter(appItf -> appItf.getId().equals(interfaceName))
				.findFirst().get();

		return itf;

	}
}

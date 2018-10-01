/**
 * 
 */
package iotcomposer.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.labs.collab.majo.devices.DeviceList;

import fr.inria.convecs.iotcomposer.model.AppInterface;
import fr.inria.convecs.iotcomposer.model.Binding;
import fr.inria.convecs.iotcomposer.model.Binding.BindingType;
import fr.inria.convecs.iotcomposer.model.BindingDto;
import fr.inria.convecs.iotcomposer.model.ConnectedObject;
import fr.inria.convecs.iotcomposer.model.DeploymentPlan;
import fr.inria.convecs.iotcomposer.model.InterfaceType;
import fr.inria.convecs.iotcomposer.model.Lts;
import fr.inria.convecs.iotcomposer.model.State;
import fr.inria.convecs.iotcomposer.model.Step;
import fr.inria.convecs.iotcomposer.model.Step.StepOperation;
import fr.inria.convecs.iotcomposer.model.Transition;

/**
 * @author ajayk
 *
 */
class JsonTest {

	@Test
	void test() {
		//fail("Not yet implemented");
	}

	@Test
	void jsonWriterTest() throws JsonProcessingException {
		List<Binding> bindings = new ArrayList<Binding>();
		List<Step> steps = new ArrayList<Step>();
		List<ConnectedObject> objects = new ArrayList<ConnectedObject>();
		
		AppInterface appItf = new AppInterface();
		appItf.setId("TV_ON");
		appItf.setType(InterfaceType.NETWORKIN);
		AppInterface appItf2 = new AppInterface();
		appItf2.setId("TV_OFF");
		appItf2.setType(InterfaceType.NETWORKOUT);
		
		List<AppInterface> appItfList = new ArrayList<>();
		appItfList.add(appItf);
		appItfList.add(appItf2);
		
		State stateOn = new State();
		stateOn.setId("State_ON");
		
		State stateOff = new State();
		stateOff.setId("State_OFF");
		
		Transition transitionOn = new Transition();
		transitionOn.setAction(appItf);
		transitionOn.setSource(stateOff);
		transitionOn.setTarget(stateOn);
		
		Transition transitionOff = new Transition();
		transitionOff.setAction(appItf2);
		transitionOff.setSource(stateOn);
		transitionOff.setTarget(stateOff);
		
		stateOn.setTransitions((List<Transition>) Arrays.asList(transitionOff));
		stateOff.setTransitions((List<Transition>) Arrays.asList(transitionOn));
		
		List<State> states = new ArrayList<>();
		states.add(stateOn);
		states.add(stateOff);
		
		Lts ltsTv = new Lts();
		ltsTv.setActions(appItfList);
		ltsTv.setInitialState(stateOff);
		ltsTv.setStates(states);
		
		ConnectedObject co = new ConnectedObject();
		co.setId("TV");
		co.setAppInterfaces(appItfList);
		co.setLts(ltsTv);
		
		objects.add(co);
		
		Binding bind = new Binding();
		bind.setId("b_1");
		bind.setType(BindingType.WEAK);
		bind.setSource(appItf);
		bind.setTarget(appItf2);
		
		bindings.add(bind);
		
		Step step1 = new Step();
		step1.setId("TV_START");
		step1.setElement(co.getId());
		step1.setOperation(StepOperation.START);
		
		Step step2 = new Step();
		step2.setId("TV_Bind");
		step2.setElement(bind.getId());
		step2.setOperation(StepOperation.BIND);
		
		steps.add(step1);
		steps.add(step2);
		
		DeploymentPlan plan = new DeploymentPlan();
		plan.setBindings(bindings);
		plan.setObjects(objects);
		plan.setSteps(steps);

		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = objectWriter.writeValueAsString(plan);
		
		json = objectWriter.writeValueAsString(co);
		
		System.out.println(json);
	}

	@Test
	public void jsonReaderTest() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		ConnectedObject co = objectMapper.readValue(new File("target/test-classes/json/co-tv.json"), ConnectedObject.class);
		ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
		String actual = objectWriter.writeValueAsString(co);
		String expected = new String(Files.readAllBytes(Paths.get("target/test-classes/json/co-tv.json")));
		
		assertEquals(actual, expected);
	}
	
	@Test
	public void bindingReadTest() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		
		List<BindingDto> bindings = objectMapper
				.readValue(new File("target/test-classes/json/bindings.json"), 
						objectMapper.getTypeFactory().constructCollectionType(List.class, BindingDto.class));
		
		assertTrue(bindings.size() == 2);
	}
	
	
	@Test
	public void testDeviceList() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		
		DeviceList deviceList = objectMapper
				.readValue(new File("target/test-classes/json/devicelist.json"), DeviceList.class);
		
		System.out.println(deviceList.getElems().size());
	}
	
	@Test
	public void bindingWriteTest() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		
		Binding binding = new Binding();
		binding.setId("tv");
		AppInterface appItf = new AppInterface();
		appItf.setId("TV_ON");
		appItf.setType(InterfaceType.NETWORKIN);
		AppInterface appItf2 = new AppInterface();
		appItf2.setId("TV_OFF");
		appItf2.setType(InterfaceType.NETWORKOUT);
		binding.setSource(appItf);
		binding.setTarget(appItf2);
		binding.setType(BindingType.STRONG);
		
		ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
		String actual = objectWriter.writeValueAsString(binding);
		
		System.out.println(actual);
		
	}
}

/**
 * 
 */
package iotcomposer.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import fr.inria.convecs.iotcomposer.model.AppInterface;
import fr.inria.convecs.iotcomposer.model.Binding.BindingType;
import fr.inria.convecs.iotcomposer.model.BindingDto;
import fr.inria.convecs.iotcomposer.model.ConnectedObject;
import fr.inria.convecs.iotcomposer.model.InterfaceType;
import fr.inria.convecs.iotcomposer.model.Lts;
import fr.inria.convecs.iotcomposer.model.State;
import fr.inria.convecs.iotcomposer.model.Transition;

/**
 * @author ajayk
 *
 */
public class ConnectedObjectModelTest {
	
	@Test
	void generateCameraJsonTest() throws JsonProcessingException {
		
		ConnectedObject co = new ConnectedObject();
		co.setId("dlink5020l");
		
		AppInterface shutterOn = new AppInterface();
		shutterOn.setId("shutteron");
		shutterOn.setType(InterfaceType.PHYSICALIN);
		
		AppInterface shutterOff = new AppInterface();
		shutterOff.setId("shutteroff");
		shutterOff.setType(InterfaceType.PHYSICALIN);
		
		AppInterface motion = new AppInterface();
		motion.setId("motion");
		motion.setType(InterfaceType.NETWORKIN);
		
		AppInterface alert = new AppInterface();
		alert.setId("alert");
		alert.setType(InterfaceType.NETWORKOUT);
		
		State idle = new State();
		idle.setId("idle");
		
		State capturing = new State();
		capturing.setId("capturing");
		
		State motionDetected = new State();
		motionDetected.setId("motion-detected");
		
		
		Transition t1 = new Transition();
		t1.setAction(shutterOn);
		t1.setSource(idle);
		t1.setTarget(capturing);
		
		Transition t2 = new Transition();
		t2.setAction(motion);
		t2.setSource(capturing);
		t2.setTarget(motionDetected);
		
		Transition t3 = new Transition();
		t3.setAction(alert);
		t3.setSource(motionDetected);
		t3.setTarget(capturing);
		
		Transition t4 = new Transition();
		t4.setAction(shutterOff);
		t4.setSource(capturing);
		t4.setTarget(idle);
		
		Transition t5 = new Transition();
		t5.setAction(shutterOff);
		t5.setSource(motionDetected);
		t5.setTarget(idle);
		
		idle.setTransitions(Arrays.asList(t1));
		capturing.setTransitions(Arrays.asList(t2, t4));
		motionDetected.setTransitions(Arrays.asList(t3, t5));
		
		Lts lts = new Lts();
		lts.setInitialState(idle);
		lts.setStates(Arrays.asList(idle, capturing, motionDetected));
		lts.setActions(Arrays.asList(motion, alert, shutterOn, shutterOff));
		
		co.setLts(lts);
		co.setAppInterfaces(Arrays.asList(motion, alert, shutterOn, shutterOff));
		
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = objectWriter.writeValueAsString(co);
		
		System.out.println(json);
	}
	
	@Test
	public void generatePhoneJsonTest() throws JsonProcessingException {
		AppInterface tvOn = new AppInterface();
		tvOn.setId("On");
		tvOn.setType(InterfaceType.NETWORKOUT);
		AppInterface tvOff = new AppInterface();
		tvOff.setId("Off");
		tvOff.setType(InterfaceType.NETWORKOUT);
		
		AppInterface alert = new AppInterface();
		alert.setId("alert");
		alert.setType(InterfaceType.NETWORKIN);
		
		List<AppInterface> appItfList = new ArrayList<>();
		appItfList.add(tvOn);
		appItfList.add(tvOff);
		appItfList.add(alert);
		
		State stateOn = new State();
		stateOn.setId("StateOn");
		
		State stateOff = new State();
		stateOff.setId("StateOff");
		
		Transition transitionOn = new Transition();
		transitionOn.setAction(tvOn);
		transitionOn.setSource(stateOff);
		transitionOn.setTarget(stateOn);
		
		Transition transitionOff = new Transition();
		transitionOff.setAction(tvOff);
		transitionOff.setSource(stateOn);
		transitionOff.setTarget(stateOff);
		
		Transition alert1 = new Transition();
		alert1.setAction(alert);
		alert1.setSource(stateOn);
		alert1.setTarget(stateOn);
		
		Transition alert2 = new Transition();
		alert2.setAction(alert);
		alert2.setSource(stateOff);
		alert2.setTarget(stateOff);
		
		stateOn.setTransitions((List<Transition>) Arrays.asList(transitionOff, alert2));
		stateOff.setTransitions((List<Transition>) Arrays.asList(transitionOn, alert2));
		
		List<State> states = new ArrayList<>();
		states.addAll(Arrays.asList(stateOn, stateOff));
		
		Lts ltsTv = new Lts();
		ltsTv.setActions(appItfList);
		ltsTv.setInitialState(stateOff);
		ltsTv.setStates(states);
		
		ConnectedObject co = new ConnectedObject();
		co.setId("Phone");
		co.setAppInterfaces(appItfList);
		co.setLts(ltsTv);
		
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = objectWriter.writeValueAsString(co);
		
		System.out.println(json);
	}
	
	@Test
	public void generateTvJsonTest() throws JsonProcessingException {
		AppInterface appItf = new AppInterface();
		appItf.setId("TvOn");
		appItf.setType(InterfaceType.NETWORKIN);
		AppInterface appItf2 = new AppInterface();
		appItf2.setId("TvOff");
		appItf2.setType(InterfaceType.NETWORKIN);
		
		List<AppInterface> appItfList = new ArrayList<>();
		appItfList.add(appItf);
		appItfList.add(appItf2);
		
		State stateOn = new State();
		stateOn.setId("StateOn");
		
		State stateOff = new State();
		stateOff.setId("StateOff");
		
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
		
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = objectWriter.writeValueAsString(co);
		
		System.out.println(json);
	}
	
	@Test
	public void testBindingDto() throws JsonProcessingException {
		
		List<BindingDto> dto = new ArrayList<>();
		
		BindingDto b1 = new BindingDto();
		b1.setId(RandomStringUtils.randomAlphanumeric(5));
		b1.setSource("phone#on");
		b1.setTarget("tv#on");
		b1.setType(BindingType.WEAK);
		
		BindingDto b2 = new BindingDto();
		b2.setId(RandomStringUtils.randomAlphanumeric(5));
		b2.setSource("phone#off");
		b2.setTarget("tv#off");
		b2.setType(BindingType.WEAK);
		
		BindingDto b3 = new BindingDto();
		b3.setId(RandomStringUtils.randomAlphanumeric(5));
		b3.setSource("camera#alert");
		b3.setTarget("phone#alert");
		b3.setType(BindingType.STRONG);
		
		dto.addAll(Arrays.asList(b1, b2, b3));
		
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = objectWriter.writeValueAsString(dto);
		
		System.out.println(json);
		
	}
}

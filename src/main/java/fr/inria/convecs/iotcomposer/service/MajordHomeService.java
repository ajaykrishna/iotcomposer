/**
 * 
 */
package fr.inria.convecs.iotcomposer.service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.labs.collab.majo.devices.DeviceList;
import com.labs.collab.majo.vo.CoCoData;
import com.labs.collab.majo.vo.DefinedRulesSetDefinedRule;
import com.labs.collab.majo.vo.Elem;
import com.labs.collab.majo.vo.IdsSetElementId;
import com.labs.collab.majo.vo.PredicateIdPredicate;
import com.labs.collab.majo.vo.RemotepartsSetCocoId;

/**
 * @author ajayk
 *
 */
public class MajordHomeService {

	static final Logger LOGGER = LoggerFactory.getLogger(MajordHomeService.class);
	private static final String REST_URI = "http://dummyurl.com/MajordHome";

	private Client client = ClientBuilder.newClient();

	public List<String> getDeviceList() throws JsonParseException, JsonMappingException, IOException {

		Response response = client.target(REST_URI)
				.path("virtualobjects")
				.queryParam("user", "pwd")
				.request(MediaType.APPLICATION_JSON)
				.get();

		String responseJson = (response.readEntity(String.class));

		ObjectMapper objectMapper = new ObjectMapper();

		DeviceList deviceList = objectMapper
				.readValue(responseJson, DeviceList.class);

		List<String> deviceId = deviceList.getElems().stream()
				.map(e -> e.getId()).collect(Collectors.toList());

		return deviceId;
	}

	public String createCoCo(String device1, String device2) throws JsonProcessingException {

		CoCoData coCoData = new CoCoData();
		coCoData.setFlags("");
		coCoData.setEnv("");

		Elem elem = new Elem();
		elem.setDefinedRulesSetDefinedRule(null);
		//elem.setId(compBinding.getId());
		elem.setName(device1 + "i" + device2 + "i" + RandomStringUtils.randomAlphanumeric(5));
		//elem.setName(device1+device2);
		elem.setKind("PERSISTENT");
		elem.setStartingmode("CONNECT_AUTOMATICALLY");
		DefinedRulesSetDefinedRule definedRulesSetDefinedRule = new DefinedRulesSetDefinedRule();
		elem.setDefinedRulesSetDefinedRule(definedRulesSetDefinedRule);
		RemotepartsSetCocoId remotepartsSetCocoId = new RemotepartsSetCocoId();
		elem.setRemotepartsSetCocoId(remotepartsSetCocoId);

		PredicateIdPredicate predicateIdPredicate = new PredicateIdPredicate();
		IdsSetElementId idsSetElementId = new IdsSetElementId();
		idsSetElementId.set1(device1);
		idsSetElementId.set2(device2);

		predicateIdPredicate.setIdsSetElementId(idsSetElementId);
		elem.setPredicateIdPredicate(predicateIdPredicate);
		//elem.setId("");

		coCoData.setElems(Arrays.asList(elem));

		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = null;
		try {
			json = objectWriter.writeValueAsString(elem);
		} catch (JsonProcessingException e) {
			LOGGER.warn("error processing json element: {}", e);
			throw e;
		}

		LOGGER.debug("json posted: {}", json);

		Entity<Elem> postContent = Entity.entity(elem, MediaType.APPLICATION_JSON);
		LOGGER.debug("json: {}, post: {}", json, postContent);

		Response response = client.target(REST_URI)
				.path("cocos")
				.queryParam("user", "LN")
				.request(MediaType.APPLICATION_JSON)
				.post(postContent);

		String respValues = response.readEntity(String.class);

		return respValues;

	}

	public List<String> getDeviceByVspace(String vspace) throws JsonParseException, JsonMappingException, IOException {

		Response response = client.target(REST_URI)
				.path("virtualobjects")
				.path(vspace)
				.queryParam("user", "pwd")
				.request(MediaType.APPLICATION_JSON)
				.get();

		String responseJson = (response.readEntity(String.class));

		ObjectMapper objectMapper = new ObjectMapper();

		DeviceList deviceList = objectMapper
				.readValue(responseJson, DeviceList.class);

		List<String> deviceId = deviceList.getElems().stream()
				.map(e -> e.getId()).collect(Collectors.toList());

		return deviceId;
	}
	
	public List<String> getListVspace() throws JsonParseException, JsonMappingException, IOException {

		Response response = client.target(REST_URI)
				.path("vspace")
				.queryParam("user", "pwd")
				.request(MediaType.APPLICATION_JSON)
				.get();

		String responseJson = (response.readEntity(String.class));

		ObjectMapper objectMapper = new ObjectMapper();

		DeviceList deviceList = objectMapper
				.readValue(responseJson, DeviceList.class);

		List<String> deviceId = deviceList.getElems().stream()
				.map(e -> e.getId()).collect(Collectors.toList());

		return deviceId;
	}
}
package com.modis.acciaback.service;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.modis.acciaback.model.Ticket;
import com.modis.acciaback.model.User;
import com.modis.acciaback.payloads.ChatResponse;
import com.modis.acciaback.payloads.EntityResponse;
import com.modis.acciaback.payloads.InputDetails;
import com.modis.acciaback.payloads.UserResponse;

@Service
public class ChatService {
	Logger log = LogManager.getLogger(ChatService.class);
	@Value("${accia.api.uri}")
	private String urlAcciaApi;
	
	
	@Value("${accia.api.iot.uri}")
	private String urlAcciaApiIot;

	private RestTemplate restTemplate;

	@Autowired
	private TicketService ticketService;

	@Autowired
	public ChatService() {
		RestTemplateBuilder builder = new RestTemplateBuilder();
		this.restTemplate = builder.build();

		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
		messageConverters.add(converter);
		messageConverters.add(new StringHttpMessageConverter());
		this.restTemplate.setMessageConverters(messageConverters);
	}

	/**
	 * @param query
	 * @return
	 */
	public ChatResponse sendMessage(String query) {
		log.info("-------------------- Je suis sendMessage Service -------");
		ChatResponse response = new ChatResponse();

		InputDetails inputDetails = languageAnalysis(query);

		// response.setQuery(query);
		response.setIntention(inputDetails.getIntention());
		LinkedHashMap<String, String> entities = inputDetails.getEntities();

		for (Map.Entry<String, String> entry : entities.entrySet()) {

			response.getEntities().add(new EntityResponse(entry.getKey(), entry.getValue()));

		}

		response.setCriticityLevel(inputDetails.getCriticity_level());

		if ("urgent".equals(inputDetails.getCriticity_level())) {

			Ticket newTicket = ticketService.openTicket();
			if (newTicket != null) {
				User chosenUser = newTicket.getAssignedUser();
				response.setAssignedUser(new UserResponse(chosenUser.getId(), chosenUser.getNom(),
						chosenUser.getPrenom(), chosenUser.getEmail()));
			}

		}
		return response;
	}

	/**
	 * *
	 * 
	 * @param request
	 * @return
	 */
	private InputDetails languageAnalysis(String request) {

		String url = urlAcciaApi + "/LanguageAnalysis/inputDetails";
		log.info("---------- url envoyé à l'API -------: " + url);

		RestTemplate restTemplate = new RestTemplate();

		JSONObject json = new JSONObject();
		json.put("text", request);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(json.toString(), headers);

		ResponseEntity<InputDetails> response = restTemplate.exchange(url, HttpMethod.POST, entity, InputDetails.class);
		InputDetails result = response.getBody();

		return result;
	}

	public String intentionModel(String query) {
		log.info("-------------------- je suis intentionModel Service -------");

		String url = urlAcciaApi + "/ModelAnalysis/intention";

		return modelAnalysis(query, url);

	}

//	private String modelAnalysis(String query, String urlString) {
//		RestTemplate restTemplate = new RestTemplate();
//		JSONObject json = new JSONObject();
//		json.put("text", query);
//
//		Charset utf8 = Charset.forName("UTF-8");
//		MediaType mediaType = new MediaType("application", "json", utf8);
//
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(mediaType);
//		headers.add("Accept", "*/*");
//		headers.setAccept(java.util.Arrays.asList(mediaType));
//
//		String headerValue = "en-US,en;q=0.8";
//		headers.setAcceptLanguage(Locale.LanguageRange.parse(headerValue));
//
//		headers.setCacheControl("max-age=0");
//		HttpEntity<String> entity = new HttpEntity<String>(json.toString(), headers);
//
//		ResponseEntity<String> response = restTemplate.exchange(urlString, HttpMethod.POST, entity, String.class);
//		String result = response.getBody();
//
//		log.info("----- Model Analysis Request : --------------------" + result);
//		return result;
//
//	}

	private String modelAnalysis(String query, String urlString) {
		JSONObject json = new JSONObject();
		json.put("text", query);

		String result = sendHttpPost(json, urlString);
		log.info("----- Model Analysis Request : --------------------" + result);
		return result;
	}
	
	private String sendHttpPost(JSONObject body, String urlString) {
		RestTemplate restTemplate = new RestTemplate();

		Charset utf8 = Charset.forName("UTF-8");
		MediaType mediaType = new MediaType("application", "json", utf8);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(mediaType);
		headers.add("Accept", "*/*");
		headers.setAccept(java.util.Arrays.asList(mediaType));

		String headerValue = "en-US,en;q=0.8";
		headers.setAcceptLanguage(Locale.LanguageRange.parse(headerValue));

		headers.setCacheControl("max-age=0");
		HttpEntity<String> entity = new HttpEntity<String>(body.toString(), headers);

		ResponseEntity<String> response = restTemplate.exchange(urlString, HttpMethod.POST, entity, String.class);
		String result = response.getBody();

		log.info("----- sendHttpPost Request : --------------------" + result);
		return result;

	}

	public String criticityModel(String query) {
		log.info("-------------------- je suis criticityModel Service -------");

		String url = urlAcciaApi + "/ModelAnalysis/criticity";
		System.out.println("---------------- Url envoyé à l'API : ------" + url);

		return modelAnalysis(query, url);

	}

	

	public String intentionAnalysis(String query) {
		log.info("-------------------- je suis intentionAnalysis Service -------");
		String url = urlAcciaApi + "/LanguageAnalysis/intention";
		System.out.println("----- url envoyé à l'API : --------------------" + url);
	
		return modelAnalysis(query, url);
	}

	public String entitiesAnalysis(String query) {
		log.info("-------------------- je suis entitiesAnalysis Service -------");
		String url = urlAcciaApi + "/LanguageAnalysis/entities";
		System.out.println("----- url envoyé à l'API : --------------------" + url);
		return modelAnalysis(query, url);
	}

	public String criticityAnalysis(String query) {
		log.info("-------------------- je suis criticityAnalysis Service -------");
		String url = urlAcciaApi + "/LanguageAnalysis/criticity";
		System.out.println("----- url envoyé à l'API : --------------------" + url);
		return modelAnalysis(query, url);
	}

//	public String laststateAnalysis(String engine) {
//		log.info("-------------------- je suis laststateAnalysis Service -------");
//		String url = urlAcciaApiIot + "/Components/lastState";
//		System.out.println("----- url envoyé à l'API : --------------------" + url);
//		
//		JSONObject body = new JSONObject();
//		body.put("engine", engine);
//
//		return sendHttpPost(body, url);
//	}
//	
//	public String componentsAnalysis(String engine) {
//		log.info("-------------------- je suis componentsAnalysis Service -------");
//		String url = urlAcciaApiIot + "/Components";
//		System.out.println("----- url envoyé à l'API : --------------------" + url);
//
//		JSONObject body = new JSONObject();
//		body.put("engine", engine);
//
//		return sendHttpPost(body, url);
//	}
//	
//	public String componentsWithDateAnalysis(String engine, String date) {
//		log.info("-------------------- je suis componentsWithDateAnalysis Service -------");
//		String url = urlAcciaApiIot + "/Components";
//		System.out.println("----- url envoyé à l'API : --------------------" + url);
//
//		JSONObject body = new JSONObject();
//		body.put("engine", engine);
//		body.put("date", date);
//
//		return sendHttpPost(body, url);
//	}
//	
//	public String rulAnalysis(String engine) {
//		log.info("-------------------- je suis rulAnalysis Service -------");
//		String url = urlAcciaApiIot + "/RUL";
//		System.out.println("----- url envoyé à l'API : --------------------" + url);
//
//		JSONObject body = new JSONObject();
//		body.put("engine", engine);
//
//		return sendHttpPost(body, url);
//	}
	
	public String laststateAnalysis(String engine) {
		log.info("-------------------- je suis laststateAnalysis Service -------");
		String url = urlAcciaApiIot + "/Components/lastState/" + engine;
		System.out.println("----- url envoyé à l'API : --------------------" + url);
		return sendHttpGet(url);
	}
	
	public String componentsAnalysis(String engine) {
		log.info("-------------------- je suis componentsAnalysis Service -------");
		String url = urlAcciaApiIot + "/Components/" + engine;
		System.out.println("----- url envoyé à l'API : --------------------" + url);
		return sendHttpGet(url);
	}
	
	public String componentsWithDateAnalysis(String engine, String date) {
		log.info("-------------------- je suis componentsWithDateAnalysis Service -------");
		String url = urlAcciaApiIot + "/Components/" + engine + "/" + date;
		System.out.println("----- url envoyé à l'API : --------------------" + url);
		return sendHttpGet(url);
	}
	
	public String rulAnalysis(String engine) {
		log.info("-------------------- je suis rulAnalysis Service -------");
		String url = urlAcciaApiIot + "/RUL/" + engine;
		System.out.println("----- url envoyé à l'API : --------------------" + url);
		return sendHttpGet(url);
	}
	
	private String sendHttpGet(String urlString) {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<String> response = restTemplate.exchange(urlString, HttpMethod.GET, null, String.class);
		String result = response.getBody();

		log.info("----- sendHttpGet Request : --------------------" + result);
		return result;

	}
}

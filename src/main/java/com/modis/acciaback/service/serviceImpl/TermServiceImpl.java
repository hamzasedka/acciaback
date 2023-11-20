package com.modis.acciaback.service.serviceImpl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.modis.acciaback.payloads.CheckingResponse;
import com.modis.acciaback.payloads.DefinitionsResponse;
import com.modis.acciaback.service.TermService;

/**
 * 
 * @author nadege.tchuindem
 *
 */
@Service
public class TermServiceImpl implements TermService {

	Logger log = LogManager.getLogger(TermServiceImpl.class);

	@Value("${accia.api.uri}")
	private String urlAcciaApi;

	@Override
	public DefinitionsResponse searchDefinition(String sigle, String domaine) {
		String url = urlAcciaApi + "/Definition/{sigle}/{domaine}";
		System.out.println("----- url envoyé à l'API : --------------------" + url);
		
		Map<String, String> urlParams = new HashMap<String, String>();
		urlParams.put("sigle", sigle);
		urlParams.put("domaine", domaine);

		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);
		String searchurl = UriComponentsBuilder.fromUriString(url).buildAndExpand(urlParams).toString();

		System.out.println("----- searchurl envoyé à l'API : --------------------" + searchurl);
		HttpEntity request = new HttpEntity(headers);

		RestTemplate restTemplate = new RestTemplate();
		try {
			ResponseEntity<DefinitionsResponse> response = restTemplate.exchange(searchurl, HttpMethod.GET, request,
					DefinitionsResponse.class, urlParams);
			System.out.println("----- réponse API DefinitionsResponse: --------------------"
					+ response.getBody().getDefinitions());

			return response.getBody();
		} catch (HttpClientErrorException e) {
			String errorResponseBody = e.getResponseBodyAsString();
			e.printStackTrace();
			System.out.println("----- réponse API DefinitionsResponse: --------------------" + e);

		}

		return null;
	}

	@Override
	public DefinitionsResponse searchDefinition(String sigle) {
		String url = urlAcciaApi + "/Definition/{sigle}";
		System.out.println("----- url envoyé à l'API : --------------------" + url);
		Map<String, String> params = Collections.singletonMap("sigle", sigle);

		RestTemplate restTemplate = new RestTemplate();
		try {
			ResponseEntity<DefinitionsResponse> response = restTemplate.getForEntity(url, DefinitionsResponse.class,
					params);
			System.out.println("----- réponse API DefinitionsResponse: --------------------"
					+ response.getBody().getDefinitions());
			return response.getBody();
		} catch (HttpClientErrorException e) {
			String errorResponseBody = e.getResponseBodyAsString();
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public CheckingResponse checking(String termInfos) {
		String url = urlAcciaApi + "/Checking/DefinitionEntities";
		System.out.println("----- url envoyé à l'API : --------------------" + url);
		JSONObject entitejson = new JSONObject();
		entitejson.put("sigle", termInfos);
		RestTemplate restTemplate = new RestTemplate();
		JSONObject jsonRequest = new JSONObject();

		jsonRequest.put("entities", entitejson);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(jsonRequest.toString(), headers);

		ResponseEntity<CheckingResponse> response = restTemplate.exchange(url, HttpMethod.POST, entity,
				CheckingResponse.class);
		CheckingResponse result = response.getBody();
		System.out.println(
				"----- réponse API checkingTerm entities missing: --------------------" + result.getEntities_missing());
		return result;

	}

}

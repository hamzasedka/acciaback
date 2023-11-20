package com.modis.acciaback.service.serviceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

import com.modis.acciaback.model.Doc;
import com.modis.acciaback.payloads.CheckingResponse;
import com.modis.acciaback.payloads.DocumentRequest;
import com.modis.acciaback.repository.DocRepository;
import com.modis.acciaback.service.DocService;

/**
 * 
 * @author diane.dransart
 *
 */

@Service
public class DocServiceImpl implements DocService {
	Logger log = LogManager.getLogger(DocServiceImpl.class);
	
	@Value("${accia.api.uri}")
	private String urlAcciaApi;

	private RestTemplate restTemplate;
	
	@Autowired
	private DocRepository docRepository;
	
	@Autowired
	public DocServiceImpl(DocRepository docRepository) {
		log.info("-------------------- Je suis DocServiceImpl -------");
		this.docRepository = docRepository;
		
		RestTemplateBuilder builder = new RestTemplateBuilder();
		this.restTemplate = builder.build();

		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
		messageConverters.add(converter);
		messageConverters.add(new StringHttpMessageConverter());
		this.restTemplate.setMessageConverters(messageConverters);
	}

	@Override
	public Doc searchDoc(DocumentRequest docInfos) {
		log.info("-------------------- Je suis SearchOneDoc Service Impl -------");
		Doc docFound = null;
		docFound = docRepository.findDocByTerms(docInfos.getDocumentType(), docInfos.getComponentLabel(),
				docInfos.getSerialNumber());

		if (docFound == null) {
			log.info("-------------------- Doc non trouvé dans la BDD -------");

		} else {
			log.info("-------------------- Doc trouvé dans la BDD -------");
		}
		return docFound;
	}

	@Override
	public CheckingResponse checking(DocumentRequest docInfos) {

		String url = urlAcciaApi + "/Checking/DocumentEntities";
		System.out.println("----- url envoyé à l'API : --------------------" + url);
		JSONObject entitejson = new JSONObject();
		
		 entitejson.put("documentType", docInfos.getDocumentType());
		 entitejson.put("component",docInfos.getComponentLabel());
		 entitejson.put("serialNumber",docInfos.getSerialNumber());
		 
				
		RestTemplate restTemplate = new RestTemplate();
		JSONObject jsonRequest = new JSONObject();

		jsonRequest.put("entities", entitejson);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(jsonRequest.toString(), headers);

		ResponseEntity<CheckingResponse> response = restTemplate.exchange(url, HttpMethod.POST, entity,
				CheckingResponse.class);
		CheckingResponse result = response.getBody();
		System.out.println("----- réponse API checkingDocument: --------------------" + result.getEntities_missing());
		return result;

	}

	
	/**
	 * @param entites
	 * @return
	 * @throws JSONException
	 */
	/*
	 * private JSONObject entiteJsonExtracted(JSONObject entitejson, String entites)
	 * throws JSONException { log.info("Entite String : " + entites); if (entitejson
	 * == null) entitejson = new JSONObject();
	 * 
	 * String replaceStr1 = entites.replace('[', ' '); String replaceStr2 =
	 * replaceStr1.replace(']', ' '); String replaceStr4 = replaceStr2.replace('"',
	 * ' '); String replaceStr3 = replaceStr4.trim();
	 * log.info("replaceStr3 String : " + replaceStr3); String[] split =
	 * replaceStr3.split(","); int l = split.length; for (int i = 0; i < l - 1; i =
	 * i + 2) { String key = split[i].trim(); String value = split[i + 1].trim();
	 * entitejson.put(key, value); log.info("Entite JSON : " + entitejson); } return
	 * entitejson; }
	 */
}

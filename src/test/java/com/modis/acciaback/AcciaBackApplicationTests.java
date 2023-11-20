package com.modis.acciaback;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.modis.acciaback.model.Chart;
import com.modis.acciaback.model.Doc;
import com.modis.acciaback.model.InfoTypeValue;
import com.modis.acciaback.payloads.ChartRequest;
import com.modis.acciaback.payloads.ChatResponse;
import com.modis.acciaback.payloads.CheckingResponse;
import com.modis.acciaback.payloads.ComponentRequest;
import com.modis.acciaback.payloads.DefinitionsResponse;
import com.modis.acciaback.payloads.DocumentRequest;
import com.modis.acciaback.payloads.EntityResponse;
import com.modis.acciaback.payloads.TermDefinition;
import com.modis.acciaback.service.ChatService;
import com.modis.acciaback.service.serviceImpl.ChartServiceImpl;
import com.modis.acciaback.service.serviceImpl.ComponentServiceImpl;
import com.modis.acciaback.service.serviceImpl.DocServiceImpl;
import com.modis.acciaback.service.serviceImpl.TermServiceImpl;

@SpringBootTest
class AcciaBackApplicationTests {

	@Autowired
	private ChatService chatSrv;

	@Autowired
	private DocServiceImpl docSrv;

	@Autowired
	private ChartServiceImpl chartSrv;
	
	@Autowired
	private ComponentServiceImpl cmpSrv;
	
	@Autowired
	private TermServiceImpl termSrv;

	@Test
	void contextLoads() {
	}

	@Test
	public void testInputAnalysis() {
		String querry = "Je veux la notice du ventilateur SX200";
		ChatResponse expectedResponse = new ChatResponse();

		List<EntityResponse> entites = new ArrayList<EntityResponse>();
		EntityResponse notice = new EntityResponse( "documentType","notice");
		entites.add(notice);
		EntityResponse ventilateur = new EntityResponse("component","ventilateur");
		entites.add(ventilateur);
		EntityResponse serie = new EntityResponse( "serialNumber","SX200");
		entites.add(serie);

		// expectedResponse.setCriticityLevel("standard");
		expectedResponse.setEntities(entites);
		//expectedResponse.setQuery(querry);
		expectedResponse.setIntention("document");
		ChatResponse requestResponse = chatSrv.sendMessage(querry);

		assertEquals(expectedResponse.getIntention(), requestResponse.getIntention());
		// assertEquals(expectedResponse.getCriticityLevel(),
		// requestResponse.getCriticityLevel());

		List<EntityResponse> entitesResponse = requestResponse.getEntities();

		assertEquals(entitesResponse.get(0).getName(), notice.getName());
		assertEquals(entitesResponse.get(0).getValue(), notice.getValue());

		assertEquals(entitesResponse.get(1).getName(), ventilateur.getName());
		assertEquals(entitesResponse.get(1).getValue(), ventilateur.getValue());
		assertEquals(entitesResponse.get(2).getName(), serie.getName());
		assertEquals(entitesResponse.get(2).getValue(), serie.getValue());
		//assertEquals(expectedResponse.getQuery(), requestResponse.getQuery());
	}

	

	@Test
	public void testIntentionAnalysis() {

		String intention = "document";
		Object requestResponse = chatSrv.intentionAnalysis(intention);
		assertTrue(requestResponse != null);		
	}

	@Test
	public void testCriticityModel() {
		String querry = "Je veux la notice du ventilateur SX200";
		
			String result = chatSrv.criticityModel(querry);
			assertTrue(result != null);
		

	}

	@Test
	public void testIntentionModel() {
		String querry = "Je veux la notice du ventilateur SX200";
		
			String result = chatSrv.intentionModel(querry);
			assertTrue(result != null);
		
	}


	@Test
	public void testSearchDoc() {

		//String querry = "Je veux la notice du ventilateur SX200";
		DocumentRequest docinfos = new DocumentRequest();
		docinfos.setComponentLabel("ventilateur");
		docinfos.setDocumentType("notice");
		docinfos.setSerialNumberLabel("SX200");
			
		Doc responseDoc = docSrv.searchDoc(docinfos);
		assertTrue(responseDoc != null);
		}

	
	
	@Test
	public void testSearchDefinition() {
				
		
		String sigle = "AAC";
		
		String domain = "informatique";
		
		DefinitionsResponse definitionsResponse = termSrv.searchDefinition(sigle, domain);
		List<TermDefinition> definitions = definitionsResponse.getDefinitions();
		assertTrue(definitions!=null);
		assertTrue(definitions.size()!=0);
		TermDefinition termDefinition = definitions.get(0);
		assertTrue((termDefinition.getSigle()).equals(sigle));
		//assertTrue((termDefinition.getDomaine()).equals(domain));

		}
	
	@Test
	public void testDefinition() {
			
		String sigle = "AAC";
		DefinitionsResponse definitionsResponse = termSrv.searchDefinition(sigle);
		List<TermDefinition> definitions = definitionsResponse.getDefinitions();
		System.out.println("definitions : "+ definitions);
		assertTrue(definitions!=null);
		assertTrue(definitions.size()!=0);
		TermDefinition termDefinition = definitions.get(0);
		assertTrue((termDefinition.getSigle()).equals(sigle));
		}
	
	@Test
	public void testSearchChart() {

		//String querry = "Je veux la notice du ventilateur SX200";
		ChartRequest chrtInfos = new ChartRequest();
		chrtInfos.setComponentLabel("ventilateur");
		chrtInfos.setSerialNumber("SX200");
		Chart responseChart = chartSrv.searchChart(chrtInfos);
		assertTrue(responseChart != null);
	}

	@Test
	public void testComponentDetails() {

		//String querry = "Je veux la notice du ventilateur SX200";
		ComponentRequest cmpInfos = new ComponentRequest();
		cmpInfos.setComponentLabel("ventilateur");
		cmpInfos.setSerialNumber("SX200");
		List<InfoTypeValue> responseInformations = cmpSrv.informationsDetails(cmpInfos);
		assertTrue(responseInformations != null);
		
	}

	@Test
	public void testCheckingDoc() {

		//String querry = "Je veux la notice du ventilateur SX200";
		DocumentRequest docinfos = new DocumentRequest();
		docinfos.setComponentLabel("ventilateur");
		docinfos.setDocumentType("notice");
		docinfos.setSerialNumberLabel("SX200");
			
		CheckingResponse checkingValue = docSrv.checking(docinfos);
		
		assertTrue(checkingValue != null);
		assertTrue(checkingValue.getEntities_present().size()==3);
		assertTrue(checkingValue.getEntities_missing().size()==0);
		assertTrue(checkingValue.isIs_complete());
		}

	@Test
	public void testCheckingDefinition() {
			
		CheckingResponse checkingValue = termSrv.checking("AAC");
		assertTrue(checkingValue != null);
		assertTrue(checkingValue.getEntities_missing().size()==0);
		assertTrue(checkingValue.getEntities_present().size()==1);
		assertTrue(checkingValue.isIs_complete());
		}

	@Test
	public void testLaststateAnalysis() {

		String engine = "FD001";
		Object requestResponse = chatSrv.laststateAnalysis(engine);
		assertTrue(requestResponse != null);		
	}

	@Test
	public void testComponentsAnalysis() {

		String engine = "FD001";
		Object requestResponse = chatSrv.componentsAnalysis(engine);
		assertTrue(requestResponse != null);		
	}

	@Test
	public void testComponentsWithDateAnalysis() {

		String engine = "FD001";
		String date = "16-02-2023";
		Object requestResponse = chatSrv.componentsWithDateAnalysis(engine, date);
		assertTrue(requestResponse != null);		
	}

	@Test
	public void testRulAnalysis() {

		String engine = "FD001";
		Object requestResponse = chatSrv.rulAnalysis(engine);
		assertTrue(requestResponse != null);		
	}

	@Test
	public void testEntitiesAnalysis() {

		String query = "donne moi la notice du ventilateur SX400";
		Object requestResponse = chatSrv.entitiesAnalysis(query);
		assertTrue(requestResponse != null);		
	}

	@Test
	public void testCriticityAnalysis() {

		String query = "donne moi la notice du ventilateur SX400";
		Object requestResponse = chatSrv.criticityAnalysis(query);
		assertTrue(requestResponse != null);		
	}
}

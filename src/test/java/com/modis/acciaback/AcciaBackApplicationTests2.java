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
class AcciaBackApplicationTests2 {

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
	public void testDefinition() {
			
		String sigle = "AAC";
		DefinitionsResponse definitionsResponse = termSrv.searchDefinition(sigle);
		List<TermDefinition> definitions = definitionsResponse.getDefinitions();
		assertTrue(definitions!=null);
		assertTrue(definitions.size()!=0);
		TermDefinition termDefinition = definitions.get(0);
		assertTrue((termDefinition.getSigle()).equals(sigle));
		}
}

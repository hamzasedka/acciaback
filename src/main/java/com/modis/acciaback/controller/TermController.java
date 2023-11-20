package com.modis.acciaback.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.modis.acciaback.payloads.CheckingResponse;
import com.modis.acciaback.payloads.DefinitionsResponse;
import com.modis.acciaback.service.serviceImpl.TermServiceImpl;

@RestController
@RequestMapping("term")
@CrossOrigin(origins = "http://localhost:4200")
public class TermController {

	Logger log = LogManager.getLogger(TermController.class);

	@Autowired
	private TermServiceImpl termService;

	@GetMapping(value = "/definitions/{sigle}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<DefinitionsResponse> searchDefinition(@PathVariable(value = "sigle") String sigle)
			throws ResourceNotFoundException {
		log.info(" ------- je suis searchDefinition controller : -------");
		DefinitionsResponse termDefinitions = termService.searchDefinition(sigle);
		
		if (termDefinitions == null) {
			return ResponseEntity.notFound().build();
		} else {
			System.out.println("definitions : "+ termDefinitions.getDefinitions().toString());
			return ResponseEntity.ok(termDefinitions);
		}
	}

	@GetMapping(value = "/definitions/{sigle}/{domaine}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<DefinitionsResponse> searchDefinition(@PathVariable(value = "sigle") String sigle,@PathVariable(value = "domaine") String domaine) {
		log.info(" ------- je suis searchDefinition controller : -------");
		DefinitionsResponse termDefinition = termService.searchDefinition(sigle,domaine);
		if (termDefinition == null) {
			return ResponseEntity.notFound().build();
		} else {

			return ResponseEntity.ok(termDefinition);
		}

	}

	@PostMapping("/checking")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<CheckingResponse> checking(@RequestBody String term) {
		log.info(" ------- je suis checking term controller : -------");
		return ResponseEntity.ok(termService.checking(term));
	}
}

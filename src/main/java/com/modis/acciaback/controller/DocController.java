package com.modis.acciaback.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.modis.acciaback.model.Doc;
import com.modis.acciaback.payloads.CheckingResponse;
import com.modis.acciaback.payloads.DocumentRequest;
import com.modis.acciaback.service.serviceImpl.DocServiceImpl;

/**
 * 
 * @author diane.dransart
 *
 */

@RestController
@RequestMapping("document")
@CrossOrigin(origins = "http://localhost:4200")
public class DocController {
	Logger log = LogManager.getLogger(DocController.class);

	@Autowired
	private DocServiceImpl docService;

	@PostMapping("/")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Doc> searchDoc(@RequestBody DocumentRequest docInfos) {
		log.info(" ------- je suis searchDoc controller : -------");
		Doc doc = docService.searchDoc(docInfos);

		if (doc == null) {
			return ResponseEntity.notFound().build();
		} else {

			return ResponseEntity.ok(doc);
		}
	}

	@PostMapping("/checking")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<CheckingResponse> checking(@RequestBody DocumentRequest docInfos) {
		log.info(" ------- je suis checking document controller : -------");
		CheckingResponse checkingDoc = docService.checking(docInfos);
		if (checkingDoc == null) {
			return ResponseEntity.notFound().build();
		} else {
		
		return ResponseEntity.ok(checkingDoc);
		}
	}

}
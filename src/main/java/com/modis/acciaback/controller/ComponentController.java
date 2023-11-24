package com.modis.acciaback.controller;

import java.util.List;

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

import com.modis.acciaback.model.InfoTypeValue;
import com.modis.acciaback.payloads.ComponentRequest;
import com.modis.acciaback.service.serviceImpl.ComponentServiceImpl;

@RestController
@RequestMapping("component")
@CrossOrigin(origins = "https://main--cosmic-moonbeam-82881c.netlify.app")
public class ComponentController {
	Logger log = LogManager.getLogger(ComponentController.class);

	@Autowired
	private ComponentServiceImpl componentService;

	@PostMapping("/informations")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<List<InfoTypeValue>> informationsDetails(@RequestBody ComponentRequest componentInfos) {
		log.info(" ----------------------- Je suis Component controller informationsDetails: ------------------------");

		List<InfoTypeValue> informationsDetails = componentService.informationsDetails(componentInfos);
		if (informationsDetails == null) {
			return ResponseEntity.notFound().build();
		} else {

			return ResponseEntity.ok(informationsDetails);
		}

	}
}

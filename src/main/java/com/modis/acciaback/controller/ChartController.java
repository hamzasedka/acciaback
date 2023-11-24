package com.modis.acciaback.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.modis.acciaback.model.Chart;
import com.modis.acciaback.payloads.ChartRequest;
import com.modis.acciaback.service.serviceImpl.ChartServiceImpl;

/**
 * 
 * @author diane.dransart
 *
 */

@RestController
@RequestMapping("chart")
@CrossOrigin(origins = "https://main--cosmic-moonbeam-82881c.netlify.app")
public class ChartController {
	Logger log = LogManager.getLogger(ChartController.class);

	@Autowired
	private ChartServiceImpl chartService;

	@GetMapping("/")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Chart> searchChart(@RequestBody ChartRequest chartInfos) {
		log.info(" ----------------------- Je suis searchChart controller : ------------------------");
		Chart chart = chartService.searchChart(chartInfos);
		if (chart == null) {
			return ResponseEntity.notFound().build();
		} else {

			return ResponseEntity.ok(chart);
		}

	}

}

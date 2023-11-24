package com.modis.acciaback.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.modis.acciaback.payloads.ChatResponse;
import com.modis.acciaback.service.ChatService;

@RestController
@RequestMapping("chat")
@CrossOrigin(origins = "https://main--cosmic-moonbeam-82881c.netlify.app")
public class ChatController {
	Logger log = LogManager.getLogger(ChatController.class);
	private ChatService chatService;

	@Autowired
	ChatController(ChatService cServ) {
		chatService = cServ;
	}

	@PostMapping("/analysis")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<ChatResponse> sendMessage(@RequestBody String query) {
		log.info(" ------- je suis sendMessage controller : -------");
		ChatResponse inputAnalysis = chatService.sendMessage(query);
		if (inputAnalysis == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(inputAnalysis);
		}

	}

	@PostMapping("/analysis/entities")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<String> entitiesAnalysis(@RequestBody String query) {
		log.info("----- Je suis intentionAnalysis Controller ---------");
		String entitiesAnalysis = chatService.entitiesAnalysis(query);
		if (entitiesAnalysis == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(entitiesAnalysis);
		}

	}

	@PostMapping(value = "/model/intention", produces = { MediaType.APPLICATION_XHTML_XML_VALUE })
	@PreAuthorize("isAuthenticated()")
	public String intentionModel(@RequestBody String query) throws Exception {
		log.info(" ------- je suis intentionModel controller : -------");
		String intentionModel = chatService.intentionModel(query);

		return intentionModel;

	}

	@PostMapping("/analysis/intention")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<String> intentionAnalysis(@RequestBody String query) {
		log.info("----- Je suis intentionAnalysis Controller ---------");
		String intentionAnalysis = chatService.intentionAnalysis(query);
		if (intentionAnalysis == null) {
			return ResponseEntity.notFound().build();
		} else {

			return ResponseEntity.ok(intentionAnalysis);
		}
	}

	@PostMapping("/analysis/criticity")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<String> criticityAnalysis(@RequestBody String query) {
		log.info("----- Je suis criticityAnalysis Controller ---------");
		String criticityAnalysis = chatService.criticityAnalysis(query);

		if (criticityAnalysis == null) {
			return ResponseEntity.notFound().build();
		} else {

			return ResponseEntity.ok(criticityAnalysis);
		}
	}

	@PostMapping(value = "/model/criticity", produces = { MediaType.APPLICATION_XHTML_XML_VALUE })
	@PreAuthorize("isAuthenticated()")
	public String criticityModel(@RequestBody String query) {
		log.info("----- Je suis criticityAnalysis Controller ---------");
		String criticityModel = chatService.criticityModel(query);

		return criticityModel;
	}

	// @PostMapping("/components/laststate")
	// @PreAuthorize("isAuthenticated()")
	// public ResponseEntity<String> laststateAnalysis(@RequestBody String engine) {
	// log.info("----- Je suis laststateAnalysis Controller ---------");
	// String laststateAnalysis = chatService.laststateAnalysis(engine);
	//
	// if (laststateAnalysis == null) {
	// return ResponseEntity.notFound().build();
	// } else {
	// return ResponseEntity.ok(laststateAnalysis);
	// }
	// }
	//
	// @PostMapping("/components")
	// @PreAuthorize("isAuthenticated()")
	// public ResponseEntity<String> componentsAnalysis(@RequestBody String engine)
	// {
	// log.info("----- Je suis laststateAnalysis Controller ---------");
	// String componentsAnalysis = chatService.componentsAnalysis(engine);
	//
	// if (componentsAnalysis == null) {
	// return ResponseEntity.notFound().build();
	// } else {
	// return ResponseEntity.ok(componentsAnalysis);
	// }
	// }
	//
	// @PostMapping("/components/date")
	// @PreAuthorize("isAuthenticated()")
	// public ResponseEntity<String> componentsWithDateAnalysis(@RequestBody String
	// engine, @RequestBody String date) {
	// log.info("----- Je suis componentsWithDateAnalysis Controller ---------");
	// String componentsWithDateAnalysis =
	// chatService.componentsWithDateAnalysis(engine, date);
	//
	// if (componentsWithDateAnalysis == null) {
	// return ResponseEntity.notFound().build();
	// } else {
	// return ResponseEntity.ok(componentsWithDateAnalysis);
	// }
	// }
	//
	// @PostMapping("/rul")
	// @PreAuthorize("isAuthenticated()")
	// public ResponseEntity<String> rulAnalysis(@RequestBody String engine) {
	// log.info("----- Je suis rulAnalysis Controller ---------");
	// String rulAnalysis = chatService.rulAnalysis(engine);
	//
	// if (rulAnalysis == null) {
	// return ResponseEntity.notFound().build();
	// } else {
	// return ResponseEntity.ok(rulAnalysis);
	// }
	// }

	@GetMapping("/components/laststate/{engine}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<String> laststateAnalysis(@PathVariable(value = "engine") String engine) {
		log.info("----- Je suis laststateAnalysis Controller ---------");
		String laststateAnalysis = chatService.laststateAnalysis(engine);

		if (laststateAnalysis == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(laststateAnalysis);
		}
	}

	@GetMapping("/components/{engine}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<String> componentsAnalysis(@PathVariable(value = "engine") String engine) {

		log.info("----- Je suis laststateAnalysis Controller ---------");
		String componentsAnalysis = chatService.componentsAnalysis(engine);

		if (componentsAnalysis == null) {
			log.info("----- Je suis componentsAnalysis ---------");

			return ResponseEntity.notFound().build();
		} else {
			log.info("----- Je suis componentsAnalysis ---------");

			return ResponseEntity.ok(componentsAnalysis);
		}

	}

	@GetMapping("/components/{engine}/{date}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<String> componentsWithDateAnalysis(@PathVariable(value = "engine") String engine,
			@PathVariable(value = "date") String date) {
		log.info("----- Je suis componentsWithDateAnalysis Controller ---------");
		String componentsWithDateAnalysis = chatService.componentsWithDateAnalysis(engine, date);

		if (componentsWithDateAnalysis == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(componentsWithDateAnalysis);
		}
	}

	@GetMapping("/rul/{engine}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<String> rulAnalysis(@PathVariable(value = "engine") String engine) {
		log.info("----- Je suis rulAnalysis Controller ---------");
		String rulAnalysis = chatService.rulAnalysis(engine);

		if (rulAnalysis == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(rulAnalysis);
		}
	}
}

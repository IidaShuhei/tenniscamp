package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.form.RegisterSinglesPlayerForm;
import com.example.service.RegisterSinglesPlayerService;

@RestController
@RequestMapping("/")
public class RegisterSinglesController {

	Logger logger = LoggerFactory.getLogger(RegisterSinglesController.class);

	@Autowired
	private RegisterSinglesPlayerService service;

	@PostMapping("/registerSinglesPlayer")
	public void registerSinglesPlayer(@RequestBody RegisterSinglesPlayerForm form) {

		logger.trace("error");
		service.registerSinglesPlayer(form);
	}

}

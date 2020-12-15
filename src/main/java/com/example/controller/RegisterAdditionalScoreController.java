package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.form.RegisterAdditionalScoreForm;
import com.example.service.RegisterAdditionalScoreService;

@RestController
@RequestMapping("/")
public class RegisterAdditionalScoreController {

	@Autowired
	private RegisterAdditionalScoreService service;
	
	@PostMapping("/registerAdditionalScore")
	public void registerAdditionalScore(@RequestBody RegisterAdditionalScoreForm form) {
		service.register(form);
	}
	
}

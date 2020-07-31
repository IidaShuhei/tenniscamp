package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.form.RegisterDoublesScoreForm;
import com.example.service.RegisterDoublesScoreService;

@RestController
@RequestMapping("/")
public class RegisterDoublesScoreController {

	@Autowired
	private RegisterDoublesScoreService registerDoublesScoreService;
	
	@PostMapping("/registerDoublesScore")
	public Integer registerDoublesScore(@RequestBody RegisterDoublesScoreForm form) {
		return registerDoublesScoreService.registerDoublesScore(form);
	}
	
}

package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.form.RegisterDoublesPlayerForm;
import com.example.service.RegisterDoublesPlayerService;

@RestController
@RequestMapping("/")
public class RegisterDoublesPlayerController {

	@Autowired
	private RegisterDoublesPlayerService registerDoublesPlayerService;

	@PostMapping("/registerDoublesPlayer")
	public void registerDoublesPlayer(@RequestBody RegisterDoublesPlayerForm form) {
		registerDoublesPlayerService.registerDoublesPlayer(form);
	}

}

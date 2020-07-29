package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.form.RegisterSinglesPlayerForm;
import com.example.service.RegisterSinglesPlayerService;

@RestController
@RequestMapping("/")
public class RegisterSinglesController {

	@Autowired
	private RegisterSinglesPlayerService service;
	
	@PostMapping("/registerSinglesPlayer")
	public void registerSinglesPlayer(@RequestPart("obj") RegisterSinglesPlayerForm form, @RequestParam(value = "file", required = false) MultipartFile uploadFile) {
		service.registerSinglesPlayer(form, uploadFile);
	}
	
}

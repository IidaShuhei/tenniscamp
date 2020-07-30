package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.form.RegisterDoublesPlayerForm;
import com.example.service.RegisterDoublesPlayerService;

@RestController
@RequestMapping("/")
public class RegisterDoublesPlayerController {

	@Autowired
	private RegisterDoublesPlayerService registerDoublesPlayerService;
	
	@PostMapping("/registerDoublesPlayer")
	public void registerDoublesPlayer(
//			@RequestPart("obj")
	@RequestBody RegisterDoublesPlayerForm form
//	, @RequestParam(value = "file", required = false) MultipartFile uploadFile
	) {
		registerDoublesPlayerService.registerDoublesPlayer(form
//				,uploadFile
				);
	}
	
}

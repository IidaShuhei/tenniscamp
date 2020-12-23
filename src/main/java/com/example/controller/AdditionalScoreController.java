package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.PlayerListDto;
import com.example.form.RegisterAdditionalScoreForm;
import com.example.service.DeleteAdditionalScoreService;
import com.example.service.RegisterAdditionalScoreService;
import com.example.service.ShowAdditionalScoreService;

@RestController
@RequestMapping("/additionalScore")
@CrossOrigin
public class AdditionalScoreController {

	@Autowired
	private RegisterAdditionalScoreService registerAdditionalScoreService;
	
	@Autowired
	private DeleteAdditionalScoreService deleteAdditionalScoreService;
	
	@Autowired
	private ShowAdditionalScoreService showAdditionalScoreService;
	
	@GetMapping
	public List<PlayerListDto> findAdditinalScore() {
		return showAdditionalScoreService.findAdditinalScore();
	}
	
	@PostMapping
	public void registerAdditionalScore(@RequestBody RegisterAdditionalScoreForm form) {
		registerAdditionalScoreService.register(form);
	}

	@DeleteMapping("/{id}")
	@CrossOrigin
	public void delete(@PathVariable("id") Integer id) {
		deleteAdditionalScoreService.delete(id);
	}
	
}

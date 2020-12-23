package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.ResultListDto;
import com.example.form.DeleteScoreForm;
import com.example.form.RegisterDoublesScoreForm;
import com.example.service.DeleteScoreService;
import com.example.service.RegisterDoublesScoreService;
import com.example.service.ShowDoublesScoreService;

@RestController
@RequestMapping("doublesScore")
@CrossOrigin
public class DoublesScoreController {

	@Autowired
	private ShowDoublesScoreService service;
	
	@Autowired
	private RegisterDoublesScoreService registerDoublesScoreService;
	
	@Autowired
	private DeleteScoreService deleteScoreService;
	
	@GetMapping
	public List<ResultListDto> findDoublesResult() {
		return service.findDoublesResult();
	}
	
	@PostMapping
	public void registerDoublesScore(@RequestBody RegisterDoublesScoreForm form) throws Exception {
		registerDoublesScoreService.registerDoublesScore(form);
	}
	
	@DeleteMapping
	@CrossOrigin
	public void deleteDoublesScore(@RequestBody DeleteScoreForm form) {
		deleteScoreService.deleteDoublesResult(form.getPlayerId(), form.getOpponentPlayerId());
	}
	
}

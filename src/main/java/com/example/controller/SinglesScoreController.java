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
import com.example.form.RegisterSinglesScoreForm;
import com.example.service.DeleteScoreService;
import com.example.service.RegisterSinglesScoreService;
import com.example.service.ShowSinglesScoreService;

@RestController
@RequestMapping("singlesScore")
@CrossOrigin
public class SinglesScoreController {

	@Autowired
	private ShowSinglesScoreService showSinglesScoreService;
	
	@Autowired
	private RegisterSinglesScoreService registerSinglesScoreService;
	
	@Autowired
	private DeleteScoreService deleteScoreService;
	
	@GetMapping
	public List<ResultListDto> findResult() {
		return showSinglesScoreService.findResult();
	}
	
	@PostMapping
	public void registerSinglesScore(@RequestBody RegisterSinglesScoreForm form) throws Exception {
		registerSinglesScoreService.registerSinglesScore(form);
	}
	
	@DeleteMapping
	@CrossOrigin
	public void deleteSinglesScore(@RequestBody DeleteScoreForm form) {
		deleteScoreService.deleteSinglesResult(form.getPlayerId(), form.getOpponentPlayerId());
	}
}

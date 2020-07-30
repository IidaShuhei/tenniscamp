package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.form.DeleteScoreForm;
import com.example.service.DeleteScoreService;

@RestController
@RequestMapping("/")
public class DeleteScoreController {

	@Autowired
	private DeleteScoreService deleteScoreService;
	
	@PostMapping("/deleteSinglesScore")
	public void deleteSinglesScore(@RequestBody DeleteScoreForm form) {
		deleteScoreService.deleteSinglesResult(form.getPlayerId(), form.getOpponentPlayerId());
	}
	
	@PostMapping("/deleteDoublesScore")
	public void deleteDoublesScore(@RequestBody DeleteScoreForm form) {
		deleteScoreService.deleteDoublesResult(form.getPlayerId(), form.getOpponentPlayerId());
	}
	
}

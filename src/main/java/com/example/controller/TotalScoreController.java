package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.PlayerListDto;
import com.example.service.ShowTotalScoreService;

@RestController
@RequestMapping("totalScore")
public class TotalScoreController {

	@Autowired
	private ShowTotalScoreService showTotalScoreService;
	
	@GetMapping
	public List<PlayerListDto> findTotalScore() {
		return showTotalScoreService.findTotalScore();
	}
	
}

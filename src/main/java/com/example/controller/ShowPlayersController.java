package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.PlayerListDto;
import com.example.service.ShowPlayersService;

@RestController
@RequestMapping("/")
public class ShowPlayersController {

	@Autowired
	private ShowPlayersService showPlayersService;
	
	@GetMapping("/showPlayers")
	public List<PlayerListDto> findAll() {
		return showPlayersService.findAll();
	}
	
}

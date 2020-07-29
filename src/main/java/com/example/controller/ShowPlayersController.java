package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.SinglesPlayer;
import com.example.dto.PlayerListDto;
import com.example.service.ShowPlayersService;

@RestController
@RequestMapping("/")
public class ShowPlayersController {

	@Autowired
	private ShowPlayersService showPlayersService;
	
	@GetMapping("/showAllPlayers")
	public List<SinglesPlayer> findAllPlayer() {
		return showPlayersService.findAllPlayer();
	}
	
	@GetMapping("/showPlayersExceptSinglesPlayerId")
	public List<SinglesPlayer> findPlayerExceptByPlayerId(@RequestParam Integer singlesPlayerId) {
		return showPlayersService.findPlayerExceptByPlayerId(singlesPlayerId);
	}
	
	@GetMapping("/showPlayers")
	public List<PlayerListDto> findAll() {
		return showPlayersService.findAll();
	}
	
}

package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.DoublesPlayer;
import com.example.domain.SinglesPlayer;
import com.example.dto.PlayerListDto;
import com.example.dto.ResultListDto;
import com.example.form.DeleteScoreForm;
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
	
	@GetMapping("/showAllSinglesPlayers")
	public List<SinglesPlayer> findAllSinglesPlayers() {
		return showPlayersService.findAllSinglesPlayers();
	}
	
	@GetMapping("/showPlayersExceptSinglesPlayerId")
	public List<SinglesPlayer> findPlayerExceptByPlayerId(@RequestParam Integer singlesPlayerId) {
		return showPlayersService.findPlayerExceptByPlayerId(singlesPlayerId);
	}
	
	@GetMapping("/findPlayersExceptSinglesPlayerId")
	public List<SinglesPlayer> findPlayersExceptSinglesPlayerId(@RequestParam Integer singlesPlayerId) {
		return showPlayersService.findPlayersExceptSinglesPlayerId(singlesPlayerId);
	}
	
	@GetMapping("/showSinglesPlayers")
	public List<PlayerListDto> findAllSinglesPlayer() {
		return showPlayersService.findAllSinglesPlayer();
	}
	
	@GetMapping("/showDoublesPlayers")
	public List<PlayerListDto> findAllDoublesPlayer() {
		return showPlayersService.findAllDoublesPlayer();
	}
	
	@GetMapping("/showPlayersTeamResult")
	public List<PlayerListDto> findAllPlayersTeamResult() {
		return showPlayersService.findAllPlayersTeamResult();
	}
	
	@GetMapping("/showAllDoublesPlayer")
	public List<DoublesPlayer> findAllDoublesPlayers() {
		return showPlayersService.findAllDoublesPlayers();
	}
	
	@GetMapping("/showPlayersExceptDoublesPlayerId")
	public List<DoublesPlayer> findPlayersExceptDoublesPlayerId(@RequestParam Integer doublesPlayerId) {
		return showPlayersService.findPlayersExceptDoublesPlayerId(doublesPlayerId);
	}
	
	@GetMapping("/showPlayers")
	public List<PlayerListDto> findAllPlayers() {
		return showPlayersService.findAllPlayers();
	}
	
	@GetMapping("/showSinglesResult")
	public List<ResultListDto> findSinglesResult() {
		return showPlayersService.findSinglesResult();
	}
	
	@GetMapping("/showDoublesResult")
	public List<ResultListDto> findDoublesResult() {
		return showPlayersService.findDoublesResult();
	}
	
	@PostMapping("/deletePlayer")
	public void deleteSinglesPlayer(@RequestBody DeleteScoreForm form) {
		showPlayersService.deleteSinglesPlayer(form.getPlayerId());
	}
	
	@PostMapping("/deleteTeamPlayer")
	public void deleteTeamPlayer(@RequestBody DeleteScoreForm form) {
		showPlayersService.deleteTeamPlayer(form.getPlayerId());
	}
	
	@PostMapping("/deleteDoublesPlayer")
	public void deleteDoublesPlayer(@RequestBody DeleteScoreForm form) {
		showPlayersService.deleteDoublesPlayer(form.getPlayerId());
	}
	
}

package com.example.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.SinglesPlayer;
import com.example.dto.PlayerListDto;
import com.example.service.DeleteSinglesPlayerService;
import com.example.service.RegisterSinglesPlayerService;
import com.example.service.ShowSinglesPlayerService;

@RestController
@RequestMapping("/singlesPlayer")
@CrossOrigin
public class SinglesPlayerController {
	
	@Autowired
	private ShowSinglesPlayerService showSinglesPlayerService;
	
	@Autowired
	private RegisterSinglesPlayerService registerSinglesPlayerService;
	
	@Autowired
	private DeleteSinglesPlayerService deleteSinglesPlayerService;
	
	@GetMapping("/noDoublesId")
	public List<SinglesPlayer> findSinglesPlayersWithNoDoublesId() {
		return showSinglesPlayerService.findSinglesPlayersWithNoDoublesId();
	}
	
	@GetMapping("")
	public List<PlayerListDto> findResult() {
		return showSinglesPlayerService.findSinglesPlayers();
	}
	
	@PostMapping
	public void insert(@RequestBody Map<String, String> param) {
		registerSinglesPlayerService.insert(param.get("name"));
	}
	
	@DeleteMapping("/{id}")
	@CrossOrigin
	public void delete(@PathVariable("id") Integer id) {
		deleteSinglesPlayerService.delete(id);
	}
	
}

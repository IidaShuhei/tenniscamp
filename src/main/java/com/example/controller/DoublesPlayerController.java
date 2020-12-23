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

import com.example.dto.PlayerListDto;
import com.example.service.DeleteDoublesPlayerService;
import com.example.service.RegisterDoublesPlayerService;
import com.example.service.ShowDoublesPlayerService;

@RestController
@RequestMapping("doublesPlayer")
@CrossOrigin
public class DoublesPlayerController {
	
	@Autowired
	private RegisterDoublesPlayerService registerDoublesPlayerService;
	
	@Autowired
	private DeleteDoublesPlayerService deleteDoublesPlayerService;
	
	@Autowired
	private ShowDoublesPlayerService showDoublesPlayerService;
	
	@GetMapping
	public List<PlayerListDto> findDoublesPlayers() {
		return showDoublesPlayerService.findDoublesPlayers();
	}
	
	@PostMapping
	public void insert(@RequestBody Map<String, Integer> param) {
		registerDoublesPlayerService.insert(param.get("doubles1"), param.get("doubles2"));
	}

	@DeleteMapping("/{id}")
	@CrossOrigin
	public void delete(@PathVariable("id") Integer id) {
		deleteDoublesPlayerService.delete(id);
	}
	
}

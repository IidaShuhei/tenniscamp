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

import com.example.domain.Mission;
import com.example.service.DeleteMissionService;
import com.example.service.RegisterMissionService;
import com.example.service.ShowMissionService;

@RestController
@RequestMapping("mission")
@CrossOrigin
public class MissionController {
	
	@Autowired
	private ShowMissionService showMissionService;
	
	@Autowired
	private RegisterMissionService registerMissionService;
	
	@Autowired
	private DeleteMissionService deleteMissionService;
	
	@GetMapping
	public List<Mission> findAll() {
		return showMissionService.findAll();
	}
	
	@PostMapping
	public void register(@RequestBody Map<String, String> param) {
		registerMissionService.register(param.get("mission"));
	}

	@DeleteMapping("/{id}")
	@CrossOrigin
	public void delete(@PathVariable("id") Integer id) {
		deleteMissionService.delete(id);
	}
}

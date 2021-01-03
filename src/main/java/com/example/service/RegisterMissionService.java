package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mapper.MissionMapper;

@Service
@Transactional
public class RegisterMissionService {

	@Autowired
	private MissionMapper mapper;
	
	public void register(String mission) {
		mapper.register(mission);
	}
	
}

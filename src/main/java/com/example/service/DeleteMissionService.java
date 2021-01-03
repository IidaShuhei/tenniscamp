package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mapper.MissionMapper;

@Service
@Transactional
public class DeleteMissionService {

	@Autowired
	private MissionMapper mapper;
	
	public void delete(Integer id) {
		mapper.delete(id);
	}
	
}

package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.domain.SinglesPlayer;
import com.example.mapper.SinglesPlayerMapper;

@Service
@Transactional
public class RegisterSinglesPlayerService {

	@Autowired
	private SinglesPlayerMapper singlesPlayerMapper;
	
	public void insert(String name) {
		
		final Integer resetNumber = 0;
		
		SinglesPlayer singlesPlayer = new SinglesPlayer();
		singlesPlayer.setSinglesPlayerName(name);
		singlesPlayer.setDoublesPlayerId(resetNumber);
    	singlesPlayerMapper.register(singlesPlayer);
    	
	}
	
}

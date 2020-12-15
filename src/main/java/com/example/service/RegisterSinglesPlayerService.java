package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.domain.SinglesPlayer;
import com.example.form.RegisterSinglesPlayerForm;
import com.example.mapper.SinglesPlayerMapper;

@Service
@Transactional
public class RegisterSinglesPlayerService {

	@Autowired
	private SinglesPlayerMapper singlesPlayerMapper;
	
	public void registerSinglesPlayer(RegisterSinglesPlayerForm form) {
		
		SinglesPlayer singlesPlayer = new SinglesPlayer();
		singlesPlayer.setSinglesPlayerName(form.getSinglesPlayerName());
    	singlesPlayerMapper.registerSinglesPlayer(singlesPlayer);
    	
	}
	
}

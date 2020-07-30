package com.example.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.SinglesScore;
import com.example.form.RegisterSinglesScoreForm;
import com.example.mapper.SinglesScoreMapper;

@Service
@Transactional
public class RegisterSinglesScoreService {

	@Autowired
	private SinglesScoreMapper singlesScoreMapper;
	
	public void registerSinglesScore(RegisterSinglesScoreForm form) {
		SinglesScore singlesScore = new SinglesScore();
		singlesScore.setSinglesPlayerId(form.getSinglesPlayerId());
		singlesScore.setOpponentSinglesPlayerId(form.getOpponentSinglesPlayerId());
		singlesScore.setMyMatchScore(form.getMyMatchScore());
		singlesScore.setOpponentMatchScore(form.getOpponentMatchScore());
		
		Integer totalMission = 0;
		Integer mustMission = form.getMustMission();
		Integer addMission = form.getAddMission();
		totalMission = mustMission + addMission;
		
		singlesScore.setMission(totalMission);
		singlesScore.setRegisterDate(new Timestamp(System.currentTimeMillis()));
		singlesScoreMapper.registerSinglesScore(singlesScore);
	}
	
}

package com.example.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.common.exception.BadRequestException;
import com.example.domain.SinglesScore;
import com.example.form.RegisterSinglesScoreForm;
import com.example.mapper.SinglesScoreMapper;

@Service
@Transactional
public class RegisterSinglesScoreService {

	@Autowired
	private SinglesScoreMapper singlesScoreMapper;
	
	public void registerSinglesScore(RegisterSinglesScoreForm form) throws BadRequestException {
		SinglesScore singlesScore = new SinglesScore();
		if(!form.getMyMatchScore().equals(4) && !form.getOpponentMatchScore().equals(4)) {
			
			throw new BadRequestException("どっちが勝ったん⁉︎");
		
		} else if(form.getMyMatchScore().equals(4) && form.getOpponentMatchScore().equals(4)) {
		
			throw new BadRequestException("どっちが勝ったん⁉︎");
		
		} else {
			
			SinglesScore existScore = singlesScoreMapper.findByBothId(form.getSinglesPlayerId(), form.getOpponentSinglesPlayerId());
			SinglesScore reverseScore = singlesScoreMapper.findByBothReverseId(form.getOpponentSinglesPlayerId(), form.getSinglesPlayerId());
			
		if(existScore != null) {
			
			throw new BadRequestException("その試合結果登録済み");
		
		} else if(reverseScore != null && (reverseScore.getMyMatchScore() != form.getOpponentMatchScore() || reverseScore.getOpponentMatchScore() != form.getMyMatchScore())) {
			
			throw new BadRequestException("スコアが違うぞ");
	
		} else {
			
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
	}
	
}

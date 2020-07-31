package com.example.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.DoublesScore;
import com.example.form.RegisterDoublesScoreForm;
import com.example.mapper.DoublesScoreMapper;

@Service
@Transactional
public class RegisterDoublesScoreService {

	@Autowired
	private DoublesScoreMapper doublesScoreMapper;

	public Integer registerDoublesScore(RegisterDoublesScoreForm form) {
		DoublesScore doublesScore = new DoublesScore();
		DoublesScore doublesOpponentScore = new DoublesScore();

		if (!form.getMyMatchScore().equals(4) && !form.getOpponentMatchScore().equals(4)) {

			return 1;

		} else if (form.getMyMatchScore().equals(4) && form.getOpponentMatchScore().equals(4)) {

			return 1;

		} else {

			DoublesScore existScore = doublesScoreMapper.findByBothId(form.getDoublesPlayerId(),form.getOpponentDoublesPlayerId());
			DoublesScore reverseScore = doublesScoreMapper.findByBothReverseId(form.getOpponentDoublesPlayerId(),form.getDoublesPlayerId());

			if (existScore != null) {

				return 2;
				
			} else if (reverseScore != null && (reverseScore.getMyMatchScore() != form.getOpponentMatchScore() || reverseScore.getOpponentMatchScore() != form.getMyMatchScore())) {

				return 3;

			} else {

				//自分たち
				doublesScore.setDoublesPlayerId(form.getDoublesPlayerId());
				doublesScore.setOpponentDoublesPlayerId(form.getOpponentDoublesPlayerId());
				doublesScore.setMyMatchScore(form.getMyMatchScore());
				doublesScore.setOpponentMatchScore(form.getOpponentMatchScore());

//				Integer mission = form.getMission();
//				Integer addMission = form.getAddMission();

				doublesScore.setMission(form.getMission1());
				doublesScore.setRegisterDate(new Timestamp(System.currentTimeMillis()));
				doublesScoreMapper.registerDoublesScore(doublesScore);
				
				//相手チーム
				doublesOpponentScore.setDoublesPlayerId(form.getOpponentDoublesPlayerId());
				doublesOpponentScore.setOpponentDoublesPlayerId(form.getDoublesPlayerId());
				doublesOpponentScore.setMyMatchScore(form.getOpponentMatchScore());
				doublesOpponentScore.setOpponentMatchScore(form.getMyMatchScore());
				
//				Integer mission = form.getMission();
//				Integer addMission = form.getAddMission();
				
				doublesOpponentScore.setMission(form.getMission2());
				doublesOpponentScore.setRegisterDate(new Timestamp(System.currentTimeMillis()));
				doublesScoreMapper.registerDoublesScore(doublesOpponentScore);
				
				return 4;
			}
		}
	}

}

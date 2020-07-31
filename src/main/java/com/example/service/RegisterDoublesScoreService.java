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

				doublesScore.setDoublesPlayerId(form.getDoublesPlayerId());
				doublesScore.setOpponentDoublesPlayerId(form.getOpponentDoublesPlayerId());
				doublesScore.setMyMatchScore(form.getMyMatchScore());
				doublesScore.setOpponentMatchScore(form.getOpponentMatchScore());

				Integer totalMission = 0;
				Integer mustMission = form.getMustMission();
				Integer addMission = form.getAddMission();
				totalMission = mustMission + addMission;

				doublesScore.setMission(totalMission);
				doublesScore.setRegisterDate(new Timestamp(System.currentTimeMillis()));
				doublesScoreMapper.registerDoublesScore(doublesScore);
				
				return 4;
			}
		}
	}

}

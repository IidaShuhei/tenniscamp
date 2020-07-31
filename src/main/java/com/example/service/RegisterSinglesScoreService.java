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

	public Integer registerSinglesScore(RegisterSinglesScoreForm form) throws BadRequestException {
		SinglesScore singlesScore = new SinglesScore();
		if (!form.getMyMatchScore().equals(4) && !form.getOpponentMatchScore().equals(4)) {

			return 1;

		} else if (form.getMyMatchScore().equals(4) && form.getOpponentMatchScore().equals(4)) {

			return 1;

		} else {

			SinglesScore existScore = singlesScoreMapper.findByBothId(form.getSinglesPlayerId(),
					form.getOpponentSinglesPlayerId());
			SinglesScore reverseScore = singlesScoreMapper.findByBothReverseId(form.getOpponentSinglesPlayerId(),
					form.getSinglesPlayerId());

			if (existScore != null) {

				return 2;

			} else if (reverseScore != null && (reverseScore.getMyMatchScore() != form.getOpponentMatchScore()
					|| reverseScore.getOpponentMatchScore() != form.getMyMatchScore())) {

				return 3;

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

				return 4;

			}
		}
	}

}

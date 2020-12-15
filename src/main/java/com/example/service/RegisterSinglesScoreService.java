package com.example.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.SinglesScore;
import com.example.form.RegisterSinglesScoreForm;
import com.example.mapper.SinglesScoreMapper;
import com.example.utils.CheckScore;

@Service
@Transactional
public class RegisterSinglesScoreService {

	@Autowired
	private SinglesScoreMapper singlesScoreMapper;

	public void registerSinglesScore(RegisterSinglesScoreForm form) throws Exception {
		SinglesScore singlesScore = new SinglesScore();
		CheckScore.checkScore(form.getMyMatchScore(), form.getOpponentMatchScore());
		singlesScore.checkMatch(singlesScoreMapper.findByBothId(form.getSinglesPlayerId(), form.getOpponentSinglesPlayerId()));

		// 自分のスコアを登録
		singlesScore.setSinglesPlayerId(form.getSinglesPlayerId());
		singlesScore.setOpponentSinglesPlayerId(form.getOpponentSinglesPlayerId());
		singlesScore.setMyMatchScore(form.getMyMatchScore());
		singlesScore.setOpponentMatchScore(form.getOpponentMatchScore());
		singlesScore.setMission(form.getMission1());
		singlesScore.setRegisterDate(new Timestamp(System.currentTimeMillis()));
		singlesScoreMapper.registerSinglesScore(singlesScore);

		// 相手のスコアも登録
		SinglesScore singlesOpponentScore = new SinglesScore();
		singlesOpponentScore.setSinglesPlayerId(form.getOpponentSinglesPlayerId());
		singlesOpponentScore.setOpponentSinglesPlayerId(form.getSinglesPlayerId());
		singlesOpponentScore.setMyMatchScore(form.getOpponentMatchScore());
		singlesOpponentScore.setOpponentMatchScore(form.getMyMatchScore());
		singlesOpponentScore.setMission(form.getMission2());
		singlesOpponentScore.setRegisterDate(new Timestamp(System.currentTimeMillis()));
		singlesScoreMapper.registerSinglesScore(singlesOpponentScore);

	}

}

package com.example.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.DoublesScore;
import com.example.form.RegisterDoublesScoreForm;
import com.example.mapper.DoublesScoreMapper;
import com.example.utils.CheckScore;

@Service
@Transactional
public class RegisterDoublesScoreService {

	@Autowired
	private DoublesScoreMapper doublesScoreMapper;

	public void registerDoublesScore(RegisterDoublesScoreForm form) throws Exception {
		DoublesScore doublesScore = new DoublesScore();
		CheckScore.checkScore(form.getMyMatchScore(), form.getOpponentMatchScore());
		doublesScore.checkMatch(doublesScoreMapper.findByBothId(form.getDoublesPlayerId(),form.getOpponentDoublesPlayerId()));

		// 自分たち
		doublesScore.setDoublesPlayerId(form.getDoublesPlayerId());
		doublesScore.setOpponentDoublesPlayerId(form.getOpponentDoublesPlayerId());
		doublesScore.setMyMatchScore(form.getMyMatchScore());
		doublesScore.setOpponentMatchScore(form.getOpponentMatchScore());
		doublesScore.setMission(form.getMission1());
		doublesScore.setRegisterDate(new Timestamp(System.currentTimeMillis()));
		doublesScoreMapper.register(doublesScore);

		// 相手チーム
		DoublesScore doublesOpponentScore = new DoublesScore();
		doublesOpponentScore.setDoublesPlayerId(form.getOpponentDoublesPlayerId());
		doublesOpponentScore.setOpponentDoublesPlayerId(form.getDoublesPlayerId());
		doublesOpponentScore.setMyMatchScore(form.getOpponentMatchScore());
		doublesOpponentScore.setOpponentMatchScore(form.getMyMatchScore());
		doublesOpponentScore.setMission(form.getMission2());
		doublesOpponentScore.setRegisterDate(new Timestamp(System.currentTimeMillis()));
		doublesScoreMapper.register(doublesOpponentScore);

	}

}

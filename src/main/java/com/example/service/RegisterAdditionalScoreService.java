package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.AdditionalScore;
import com.example.form.RegisterAdditionalScoreForm;
import com.example.mapper.AdditionalScoreMapper;

@Service
@Transactional
public class RegisterAdditionalScoreService {

	@Autowired
	private AdditionalScoreMapper mapper;
	
	private final static Integer win = 1;
	private final static Integer add = 1;
	private final static Integer nothing = 0;
	
	public void register(RegisterAdditionalScoreForm form) {
		
		AdditionalScore oldScore = mapper.findByPlayerId(form.getPlayerId());
		
		AdditionalScore score = new AdditionalScore();
		score.setPlayerId(form.getPlayerId());
		
		//なかったら登録
		if(oldScore == null) {
			if(form.getResult() == win) {
				score.setTotalWins(add);
				score.setTotalLoss(nothing);
			} else {
				score.setTotalWins(nothing);
				score.setTotalLoss(add);
			}
			score.setMissions(form.getMission());
			mapper.register(score);
		
		//あれば更新
		} else {
			if(form.getResult() == win) {
				score.setTotalWins(oldScore.getTotalWins() + add);
				score.setTotalLoss(oldScore.getTotalLoss());
			} else {
				score.setTotalWins(oldScore.getTotalWins());
				score.setTotalLoss(oldScore.getTotalLoss() + add);
			}
			score.setMissions(oldScore.getMissions() + form.getMission());
			mapper.update(score);
		}
	}
	
}

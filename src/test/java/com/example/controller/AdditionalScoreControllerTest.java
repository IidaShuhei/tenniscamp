package com.example.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.AdditionalScore;
import com.example.mapper.AdditionalScoreMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AdditionalScoreControllerTest {

	@Autowired
	AdditionalScoreMapper mapper;
	
	@Autowired
	AdditionalScoreController controller;
	
	@Test
	public void 団体戦結果を取得する() {
		assertEquals(1, mapper.count().size());
	}
	
	@Test
	public void 勝ち登録したらtotalWinsが増える() {
		assertEquals(2, mapper.findByPlayerId(2).getTotalWins());
		AdditionalScore score = new AdditionalScore();
		score.setPlayerId(2);
		score.setTotalWins(3);
		score.setTotalLoss(1);
		score.setMissions(3);
		mapper.update(score);
		assertEquals(3, mapper.findByPlayerId(2).getTotalWins());
	}
	
	@Test
	public void 削除したらその人の結果が消える() {
		assertEquals(2, mapper.findByPlayerId(2).getPlayerId());
		controller.delete(2);
		assertEquals(null, mapper.findByPlayerId(2));
	}
	
}

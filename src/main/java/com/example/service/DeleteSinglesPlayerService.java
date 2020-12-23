package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.SinglesPlayer;
import com.example.mapper.AdditionalScoreMapper;
import com.example.mapper.DoublesPlayerMapper;
import com.example.mapper.DoublesScoreMapper;
import com.example.mapper.SinglesPlayerMapper;
import com.example.mapper.SinglesScoreMapper;

@Service
@Transactional
public class DeleteSinglesPlayerService {
	
	@Autowired
	private SinglesPlayerMapper singlesPlayerMapper;

	@Autowired
	private SinglesScoreMapper singlesScoreMapper;

	@Autowired
	private DoublesPlayerMapper doublesPlayerMapper;

	@Autowired
	private DoublesScoreMapper doublesScoreMapper;
	
	@Autowired
	private AdditionalScoreMapper additionalScoreMapper;

	// シングルスプレイヤーを削除
	public void delete(Integer singlesPlayerId) {
		
		final Integer resetNumber = 0;
		
		SinglesPlayer player = singlesPlayerMapper.load(singlesPlayerId);
		
		//シングルスのスコア削除
		singlesScoreMapper.deleteScore(singlesPlayerId);
		singlesScoreMapper.deleteScoreFromOpponent(singlesPlayerId);
		
		//ダブルスのスコア削除
		doublesScoreMapper.deleteScore(player.getDoublesPlayerId());
		doublesScoreMapper.deleteScoreFromOpponent(player.getDoublesPlayerId());

		//削除する人のペアのダブルスIDをリセット
		singlesPlayerMapper.resetDoublesId(resetNumber,player.getDoublesPlayerId());
		
		//削除する人のダブルスペアを削除
		doublesPlayerMapper.delete(player.getDoublesPlayerId());
		
		//団体戦結果を削除
		additionalScoreMapper.delete(singlesPlayerId);

		//選手を削除
		singlesPlayerMapper.delete(singlesPlayerId);
	}

}

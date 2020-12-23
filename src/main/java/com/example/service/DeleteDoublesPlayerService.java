package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mapper.DoublesPlayerMapper;
import com.example.mapper.DoublesScoreMapper;
import com.example.mapper.SinglesPlayerMapper;

@Service
@Transactional
public class DeleteDoublesPlayerService {
	
	@Autowired
	private SinglesPlayerMapper singlesPlayerMapper;

	@Autowired
	private DoublesPlayerMapper doublesPlayerMapper;

	@Autowired
	private DoublesScoreMapper doublesScoreMapper;
	
	// ダブルスペアを削除
	public void delete(Integer playerId) {
		
		final Integer resetNumber = 0;
		
		//削除する人のペアのダブルスIDをリセット
		singlesPlayerMapper.resetDoublesId(resetNumber,playerId);
		
		//ダブルスのスコア削除
		doublesScoreMapper.deleteScore(playerId);
		doublesScoreMapper.deleteScoreFromOpponent(playerId);
		
		//ダブルスペアを削除
		doublesPlayerMapper.delete(playerId);
	}

}

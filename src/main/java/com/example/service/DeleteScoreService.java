package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mapper.DoublesScoreMapper;
import com.example.mapper.SinglesScoreMapper;

@Service
@Transactional
public class DeleteScoreService {

	@Autowired
	private SinglesScoreMapper singlesScoreMapper;
	
	@Autowired
	private DoublesScoreMapper doublesScoreMapper;
	
	public void deleteSinglesResult(Integer singlesPlayerId, Integer opponentSinglesPlayerId) {
		singlesScoreMapper.deleteSinglesResult(singlesPlayerId, opponentSinglesPlayerId);
	}
	
	public void deleteDoublesResult(Integer doublesPlayerId, Integer opponentDoublesPlayerId) {
		doublesScoreMapper.deleteDoublesResult(doublesPlayerId, opponentDoublesPlayerId);
	}
	
}

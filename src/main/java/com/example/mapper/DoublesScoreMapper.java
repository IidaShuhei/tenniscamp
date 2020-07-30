package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.DoublesScore;

@Mapper
public interface DoublesScoreMapper {

	void registerDoublesScore(DoublesScore doublesScore);
	
	List<DoublesScore> findByDoublesPlayerId(Integer doublesPlayerId);
	
	List<DoublesScore> findAll();
	
	void deleteDoublesResult(Integer doublesPlayerId, Integer opponentDoublesPlayerId);
	
	DoublesScore findByBothId(Integer doublesPlayerId, Integer opponentDoublesPlayerId);
	
	DoublesScore findByBothReverseId(Integer opponentDoublesPlayerId, Integer doublesPlayerId);
	
}

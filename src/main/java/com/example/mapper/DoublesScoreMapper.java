package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.DoublesScore;

@Mapper
public interface DoublesScoreMapper {

	void register(DoublesScore doublesScore);
	List<DoublesScore> load(Integer doublesPlayerId);
	List<DoublesScore> findAll();
	void delete(Integer doublesPlayerId, Integer opponentDoublesPlayerId);
	void deleteScore(Integer doublesPlayerId);
	void deleteScoreFromOpponent(Integer opponentDoublesPlayerId);
	DoublesScore findByBothId(Integer doublesPlayerId, Integer opponentDoublesPlayerId);
	
}

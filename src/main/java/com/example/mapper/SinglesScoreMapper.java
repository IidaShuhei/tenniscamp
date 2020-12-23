package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.SinglesScore;

@Mapper
public interface SinglesScoreMapper {

	void register(SinglesScore singlesScore);
	List<SinglesScore> findAll();
	void delete(Integer singlesPlayerId, Integer opponentSinglesPlayerId);
	void deleteScore(Integer singlesPlayerId);
	void deleteScoreFromOpponent(Integer opponentSinglesPlayerId);
	SinglesScore findByBothId(Integer singlesPlayerId, Integer opponentSinglesPlayerId);
	
}

package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.SinglesPlayer;

@Mapper
public interface SinglesPlayerMapper {

	List<SinglesPlayer> findPlayersWithScores();
	SinglesPlayer load(Integer singlesPlayerId);
	List<SinglesPlayer> findAll();
	void register(SinglesPlayer singlesPlayer);
	void updateDoublesId(Integer doublesPlayerId, Integer singlesPlayerId);
	void resetDoublesId(Integer resetNumber, Integer doublesPlayerId);
	void delete(Integer singlesPlayerId);
	
}

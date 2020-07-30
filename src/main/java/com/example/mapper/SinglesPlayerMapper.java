package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.SinglesPlayer;

@Mapper
public interface SinglesPlayerMapper {

	List<SinglesPlayer> findAll();
	void registerSinglesPlayer(SinglesPlayer singlesPlayer);
	void updateSinglesPlayer(Integer doublesPlayerId, Integer singlesPlayerId);
	List<SinglesPlayer> findAllPlayer();
	List<SinglesPlayer> findAllSinglesPlayers();
	List<SinglesPlayer> findPlayerExceptByPlayerId(Integer singlesPlayerId);
	List<SinglesPlayer> findPlayersExceptSinglesPlayerId(Integer singlesPlayerId);
	SinglesPlayer load(Integer singlesPlayerId);
}

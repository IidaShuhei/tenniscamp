package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.DoublesPlayer;

@Mapper
public interface DoublesPlayerMapper {

	Integer register(DoublesPlayer doublesPlayer);
	void delete(Integer doublesPlayerId);
	List<DoublesPlayer> findPlayersWithScores();
	
}

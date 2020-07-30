package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.DoublesPlayer;

@Mapper
public interface DoublesPlayerMapper {

	Integer registerDoublesPlayer(DoublesPlayer doublesPlayer);
	List<DoublesPlayer> findAllDoublesPlayer();
	List<DoublesPlayer> findPlayersExceptDoublesPlayerId(Integer doublesPlayerId);
	List<DoublesPlayer> findAll();
	
}

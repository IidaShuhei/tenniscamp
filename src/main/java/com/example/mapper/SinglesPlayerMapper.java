package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.SinglesPlayer;

@Mapper
public interface SinglesPlayerMapper {

	List<SinglesPlayer> findAll();
	void registerSinglesPlayer(SinglesPlayer singlesPlayer);
	void registerDoublesPlayer(Integer singlesPlayerId);
}

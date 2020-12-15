package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.AdditionalScore;

@Mapper
public interface AdditionalScoreMapper {

	void register(AdditionalScore score);
	
	void update(AdditionalScore score);
	
	AdditionalScore findByPlayerId(Integer playerId);
	
	List<AdditionalScore> findAll();
	
	void delete(Integer playerId);
	
}

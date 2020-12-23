package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.AdditionalScore;

@Mapper
public interface AdditionalScoreMapper {

	void register(AdditionalScore score);
	void update(AdditionalScore score);
	AdditionalScore load(Integer playerId);
	void delete(Integer playerId);
	
}

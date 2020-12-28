package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.AdditionalScore;

@Mapper
public interface AdditionalScoreMapper {

	void register(AdditionalScore score);
	void update(AdditionalScore score);
	AdditionalScore load(Integer playerId);
	void delete(Integer playerId);
	
	//テストで使用
	List<AdditionalScore> count();
	AdditionalScore findByPlayerId(Integer playerId);
	
}

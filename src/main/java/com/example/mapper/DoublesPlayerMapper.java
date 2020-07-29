package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.DoublesPlayer;

@Mapper
public interface DoublesPlayerMapper {

	void registerDoublesPlayer(DoublesPlayer doublesPlayer);
	
}

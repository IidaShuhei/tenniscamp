package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.Mission;

@Mapper
public interface MissionMapper {

	void register(String mission);
	void delete(Integer id);
	List<Mission> findAll();
	
}

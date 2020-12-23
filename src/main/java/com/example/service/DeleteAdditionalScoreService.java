package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mapper.AdditionalScoreMapper;

@Service
@Transactional
public class DeleteAdditionalScoreService {
	
	@Autowired
	private AdditionalScoreMapper additionalScoreMapper;

	// 団体戦結果を削除
	public void delete(Integer id) {
		additionalScoreMapper.delete(id);
	}

}

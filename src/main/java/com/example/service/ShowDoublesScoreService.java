package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.DoublesScore;
import com.example.dto.ResultListDto;
import com.example.mapper.DoublesScoreMapper;

@Service
@Transactional
public class ShowDoublesScoreService {

	@Autowired
	private DoublesScoreMapper doublesScoreMapper;

	// ダブルスの結果を取得して、試合結果一覧で閲覧する
	public List<ResultListDto> findDoublesResult() {
		List<DoublesScore> doublesScoreList = doublesScoreMapper.findAll();
		List<ResultListDto> resultListDtoList = new ArrayList<>();

		doublesScoreList.stream().forEach(score -> {
			ResultListDto dto = new ResultListDto(null, score);
			resultListDtoList.add(dto);
			dto.removeSameResult(resultListDtoList, dto);
		});

		return resultListDtoList;
	}

}

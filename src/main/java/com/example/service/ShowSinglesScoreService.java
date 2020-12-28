package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.SinglesScore;
import com.example.dto.ResultListDto;
import com.example.mapper.SinglesScoreMapper;

@Service
@Transactional
public class ShowSinglesScoreService {

	@Autowired
	private SinglesScoreMapper singlesScoreMapper;

	// シングルスの結果を取得
	public List<ResultListDto> findResult() {
		List<SinglesScore> singlesScoreList = singlesScoreMapper.findAll();
		List<ResultListDto> resultListDtoList = new ArrayList<>();

		singlesScoreList.stream().forEach(score -> {
			ResultListDto dto = new ResultListDto(score, null);
			resultListDtoList.add(dto);
			dto.removeSameResult(resultListDtoList, dto);
		});
		if (resultListDtoList.isEmpty()) {
			return null;
		} else {
			return resultListDtoList;
		}
	}

}

package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.DoublesPlayer;
import com.example.dto.PlayerListDto;
import com.example.mapper.DoublesPlayerMapper;
import com.example.utils.CheckScore;
import com.example.utils.OrderPlayer;

@Service
@Transactional
public class ShowDoublesPlayerService {
	
	@Autowired
	private DoublesPlayerMapper doublesPlayerMapper;
	
	/**
	 * ダブルス選手一覧を表示するサービス.
	 * 
	 * @return 選手DTO
	 */
	public List<PlayerListDto> findDoublesPlayers() {
		List<PlayerListDto> playerListDtoList = new ArrayList<>();
		List<DoublesPlayer> doublesPlayerList = doublesPlayerMapper.findPlayersWithScores();
		for (DoublesPlayer player : doublesPlayerList) {
			CheckScore score = new CheckScore();
			score = score.calcScore(null, player.getDoublesScoreList());
			PlayerListDto dto = new PlayerListDto(null, player,score);
			playerListDtoList.add(dto);
		}
		return OrderPlayer.order(playerListDtoList);
	}

}

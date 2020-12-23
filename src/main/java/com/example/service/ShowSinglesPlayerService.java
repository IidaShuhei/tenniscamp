package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.SinglesPlayer;
import com.example.dto.PlayerListDto;
import com.example.mapper.SinglesPlayerMapper;
import com.example.utils.CheckScore;
import com.example.utils.OrderPlayer;

@Service
@Transactional
public class ShowSinglesPlayerService {

	@Autowired
	private SinglesPlayerMapper singlesPlayerMapper;

	//ダブルスペアがいないシングルス選手を全員取得
	//ダブルス登録で使用
	public List<SinglesPlayer> findSinglesPlayersWithNoDoublesId() {
		List<SinglesPlayer> playerList = singlesPlayerMapper.findAll().stream().filter(player -> player.getDoublesPlayerId() == 0).collect(Collectors.toList());
		return playerList;
	}
	
	/**
	 * シングルス選手一覧を表示するサービス.
	 * 「選手一覧」で使用
	 * 
	 * @return 選手DTO
	 */
	public List<PlayerListDto> findSinglesPlayers() {
		List<PlayerListDto> playerListDtoList = new ArrayList<>();
		List<SinglesPlayer> singlesPlayerList = singlesPlayerMapper.findPlayersWithScores();
		singlesPlayerList.stream().forEach(player -> {
			CheckScore score = new CheckScore();
			score = score.calcScore(player.getSinglesScoreList(), null);
			PlayerListDto dto = new PlayerListDto(player,null,score);
			playerListDtoList.add(dto);
		});
		return OrderPlayer.order(playerListDtoList);
	}
	
}

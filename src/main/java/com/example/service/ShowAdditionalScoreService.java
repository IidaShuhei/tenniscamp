package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.SinglesPlayer;
import com.example.dto.PlayerListDto;
import com.example.mapper.AdditionalScoreMapper;
import com.example.mapper.SinglesPlayerMapper;
import com.example.utils.CheckScore;
import com.example.utils.OrderPlayer;

@Service
@Transactional
public class ShowAdditionalScoreService {

	@Autowired
	private SinglesPlayerMapper singlesPlayerMapper;

	@Autowired
	private AdditionalScoreMapper additionalScoreMapper;

	// 団体戦の結果を表示する
	public List<PlayerListDto> findAdditinalScore() {
		List<PlayerListDto> playerListDtoList = new ArrayList<>();
		List<SinglesPlayer> singlesPlayerList = singlesPlayerMapper.findPlayersWithScores();
		singlesPlayerList.stream().forEach(player -> {
			CheckScore score = new CheckScore();
			score = score.calcTeamResult(player, additionalScoreMapper);
			PlayerListDto dto = new PlayerListDto(player, null, score);
			playerListDtoList.add(dto);
		});
		if (playerListDtoList.isEmpty()) {
			return null;
		} else {
			return OrderPlayer.order(playerListDtoList);
		}
	}

}

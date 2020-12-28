package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.DoublesScore;
import com.example.domain.SinglesPlayer;
import com.example.dto.PlayerListDto;
import com.example.mapper.AdditionalScoreMapper;
import com.example.mapper.DoublesScoreMapper;
import com.example.mapper.SinglesPlayerMapper;
import com.example.utils.CheckScore;
import com.example.utils.OrderPlayer;

@Service
@Transactional
public class ShowTotalScoreService {

	@Autowired
	private SinglesPlayerMapper singlesPlayerMapper;

	@Autowired
	private DoublesScoreMapper doublesScoreMapper;

	@Autowired
	private AdditionalScoreMapper additionalScoreMapper;

	/**
	 * シングルス・ダブルス、団体戦、すべて合わせた得点のプレイヤー一覧
	 * 
	 * @return 総合
	 */
	public List<PlayerListDto> findTotalScore() {
		List<PlayerListDto> playerListDtoList = new ArrayList<>();
		List<SinglesPlayer> singlesPlayerList = singlesPlayerMapper.findPlayersWithScores();
		for (SinglesPlayer player : singlesPlayerList) {
			List<DoublesScore> doublesScore = doublesScoreMapper.load(player.getDoublesPlayerId());
			CheckScore score = new CheckScore();
			score = score.totalScore(player, doublesScore, additionalScoreMapper);
			PlayerListDto dto = new PlayerListDto(player, null, score);
			playerListDtoList.add(dto);
		}
		if (playerListDtoList.isEmpty()) {
			return null;
		} else {
			return OrderPlayer.order(playerListDtoList);
		}
	}

}

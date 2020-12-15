package com.example.dto;

import com.example.domain.DoublesPlayer;
import com.example.domain.SinglesPlayer;
import com.example.utils.CheckScore;

import lombok.Data;

@Data
public class PlayerListDto {

	private Integer playerId;
	private String playerName;
	private Integer totalWin;
	private Integer totalLose;
	private Integer totalMission;
	private Integer totalScore;
	private Integer ranking;

	public PlayerListDto(SinglesPlayer singles, DoublesPlayer doubles, CheckScore score) {
		super();
		if (singles != null) {
			this.playerId = singles.getSinglesPlayerId();
			this.playerName = singles.getSinglesPlayerName();
		} else {
			this.playerId = doubles.getDoublesPlayerId();
			this.playerName = doubles.getDoublesPlayerName();
		}
		this.totalWin = score.getTotalWin();
		this.totalLose = score.getTotalLose();
		this.totalMission = score.getTotalMission();
		this.totalScore = score.getTotalScore();
	}

}

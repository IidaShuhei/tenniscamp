package com.example.dto;

import lombok.Data;

@Data
public class PlayerListDto {

	private Integer playerId;
	private String playerName;
	private String imagePath;
	private Integer totalWin;
	private Integer totalLose;
	private Integer totalMission;
	private Integer totalScore;
	private Integer ranking;
	
}

package com.example.domain;

import java.util.List;

import lombok.Data;

@Data
public class DoublesPlayer {

	private Integer doublesPlayerId;
	private String doublesPlayerName;
	private List<DoublesScore> doublesScoreList;
	
}

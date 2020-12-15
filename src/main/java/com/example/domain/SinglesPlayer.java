package com.example.domain;

import java.util.List;

import lombok.Data;

@Data
public class SinglesPlayer {

	private Integer singlesPlayerId;
	private String singlesPlayerName;
	private Integer doublesPlayerId;
	private List<SinglesScore> singlesScoreList;
	
}

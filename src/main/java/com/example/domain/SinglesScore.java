package com.example.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class SinglesScore {

	private Integer singlesScoreId;
	private Integer singlesPlayerId;
	private Integer opponentSinglesPlayerId;
	private Integer myMatchScore;
	private Integer opponentMatchScore;
	private Integer mission;
	private Timestamp registerDate;
	
}

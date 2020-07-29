package com.example.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class SinglesScore {

	private Integer singlesScoreId;
	private Integer singlesPlayerId;
	private Integer opponentSinglesPlayerId;
	private Integer matchScore;
	private Integer mission;
	private Timestamp registerDate;
	
}

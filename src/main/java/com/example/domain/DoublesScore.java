package com.example.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class DoublesScore {

	private Integer doublesScoreId;
	private Integer doublesPlayerId;
	private Integer opponentDoublesPlayerId;
	private Integer matchScore;
	private Integer mission;
	private Timestamp registerDate;
	
}

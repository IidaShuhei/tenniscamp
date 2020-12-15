package com.example.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class DoublesScore {

	private Integer doublesScoreId;
	private Integer doublesPlayerId;
	private Integer opponentDoublesPlayerId;
	private Integer myMatchScore;
	private Integer opponentMatchScore;
	private Integer mission;
	private Timestamp registerDate;
	
	//試合が重複になってないかチェック
	public void checkMatch(DoublesScore existScore) throws Exception {
		if (existScore != null) {
			throw new Exception("その試合は登録済み");
		}
	}
	
}

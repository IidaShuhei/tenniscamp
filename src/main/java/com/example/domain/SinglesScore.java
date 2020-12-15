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

	// 試合が重複になってないかチェック
	public void checkMatch(SinglesScore existScore) throws Exception {
		if (existScore != null) {
			throw new Exception("その試合は登録済み");
		}
	}

}

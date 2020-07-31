package com.example.form;

import lombok.Data;

@Data
public class RegisterSinglesScoreForm {
	
	private Integer singlesPlayerId;
	private Integer opponentSinglesPlayerId;
	private Integer myMatchScore;
	private Integer opponentMatchScore;
	private Integer mission1;
	private Integer mission2;
//	private Integer addMission;
	
}

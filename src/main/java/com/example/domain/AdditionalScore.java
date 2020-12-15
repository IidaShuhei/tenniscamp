package com.example.domain;

import lombok.Data;

@Data
public class AdditionalScore {

	private Integer id;
	private Integer	playerId;
	private Integer totalWins;
	private Integer totalLoss;
	private Integer missions;
	
}

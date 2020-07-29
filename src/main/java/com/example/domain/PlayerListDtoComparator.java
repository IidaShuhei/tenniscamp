package com.example.domain;

import java.util.Comparator;

import com.example.dto.PlayerListDto;

public class PlayerListDtoComparator implements Comparator<PlayerListDto> {

	@Override
	public int compare(PlayerListDto dto1, PlayerListDto dto2) {
		return dto1.getTotalScore() > dto2.getTotalScore() ? -1 : 1;
	}
	
}

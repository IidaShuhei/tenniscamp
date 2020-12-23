package com.example.utils;

import java.util.Collections;
import java.util.List;

import com.example.domain.PlayerListDtoComparator;
import com.example.dto.PlayerListDto;

public class OrderPlayer {

	// 選手の並び替え
	public static List<PlayerListDto> order(List<PlayerListDto> playerListDtoList) {
		Collections.sort(playerListDtoList, new PlayerListDtoComparator());
		int rank = 1;
		playerListDtoList.get(0).setRanking(rank);
		for (int i = 1; i < playerListDtoList.size(); i++) {
			if (playerListDtoList.get(i).getTotalScore() != playerListDtoList.get(i - 1).getTotalScore()) {
				rank = i + 1;
			}
			playerListDtoList.get(i).setRanking(rank);
		}
		return playerListDtoList;
	}

}

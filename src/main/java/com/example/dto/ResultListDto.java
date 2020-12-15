package com.example.dto;

import java.util.List;

import com.example.domain.DoublesScore;
import com.example.domain.SinglesScore;

import lombok.Data;

@Data
public class ResultListDto {

	private Integer playerId;
	private Integer opponentId;
	private Integer playerScore;
	private Integer opponentScore;

	public ResultListDto(SinglesScore singles, DoublesScore doubles) {
		super();
		if (singles != null) {
			this.playerId = singles.getSinglesPlayerId();
			this.opponentId = singles.getOpponentSinglesPlayerId();
			this.playerScore = singles.getMyMatchScore();
			this.opponentScore = singles.getOpponentMatchScore();
		} else {
			this.playerId = doubles.getDoublesPlayerId();
			this.opponentId = doubles.getOpponentDoublesPlayerId();
			this.playerScore = doubles.getMyMatchScore();
			this.opponentScore = doubles.getOpponentMatchScore();
		}

	}
	
	//同じ結果のものは削除
	public void removeSameResult(List<ResultListDto> resultListDtoList, ResultListDto dto) {
		for (int i = 0; i < resultListDtoList.size(); i++) {
			if ((resultListDtoList.get(i).getOpponentId().equals(dto.getPlayerId())) && (resultListDtoList.get(i).getPlayerId().equals(dto.getOpponentId()))) {
				resultListDtoList.remove(i);
			}
		}
	}

}

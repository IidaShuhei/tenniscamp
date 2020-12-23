package com.example.utils;

import java.util.List;

import com.example.domain.DoublesScore;
import com.example.domain.SinglesPlayer;
import com.example.domain.SinglesScore;
import com.example.mapper.AdditionalScoreMapper;

import lombok.Data;

@Data
public class CheckScore {

	private int totalWin = 0;
	private int totalLose = 0;
	private int totalMission = 0;
	private int totalScore = 0;
	private static int plusNumber = 1;
	private static int winScore = 4;
	private static int winPoint = 7;
	
	private static int additionalWinPoint = 5;

	// スコアが間違っていないかチェック
	public static void checkScore(Integer myMatchScore, Integer opponentMatchScore) throws Exception {
		if ((!myMatchScore.equals(winScore) && !opponentMatchScore.equals(winScore))
				|| (myMatchScore.equals(winScore) && opponentMatchScore.equals(winScore))) {
			throw new Exception("勝ったのはどっち？");
		}
	}

	// 各スコアの計算
	public CheckScore calcScore(List<SinglesScore> singlesList, List<DoublesScore> doublesList) {
		
		CheckScore checkScore = new CheckScore();
		
		//シングルスのスコア計算
		if(singlesList != null) {
			for (SinglesScore score : singlesList) {
				if (score.getMyMatchScore().equals(winScore)) {
					totalWin += plusNumber;
				} else {
					totalLose += plusNumber;
				}
				if (score.getMission() != null) {
					totalMission += score.getMission();
				}
			}
			totalScore = (totalWin * winPoint) + totalMission;
			checkScore.setTotalWin(totalWin);
			checkScore.setTotalMission(totalMission);
			checkScore.setTotalScore(totalScore);
			checkScore.setTotalLose(totalLose);
			return checkScore;
			
		//ダブルスのスコア計算
		} else {
			for (DoublesScore score : doublesList) {
				if (score.getMyMatchScore().equals(winScore)) {
					totalWin += plusNumber;
				} else {
					totalLose += plusNumber;
				}
				if (score.getMission() != null) {
					totalMission += score.getMission();
				}
			}
			totalScore = (totalWin * winPoint) + totalMission;
			checkScore.setTotalWin(totalWin);
			checkScore.setTotalMission(totalMission);
			checkScore.setTotalScore(totalScore);
			checkScore.setTotalLose(totalLose);
			return checkScore;
		}
	}
	
	//団体戦の計算
	public CheckScore calcTeamResult(SinglesPlayer player,AdditionalScoreMapper additionalScoreMapper) {
		CheckScore checkScore = new CheckScore();
		
		if(additionalScoreMapper.load(player.getSinglesPlayerId()) != null) {
			totalWin = additionalScoreMapper.load(player.getSinglesPlayerId()).getTotalWins();
			totalLose = additionalScoreMapper.load(player.getSinglesPlayerId()).getTotalLoss();
			totalMission = additionalScoreMapper.load(player.getSinglesPlayerId()).getMissions();
			totalScore = (additionalScoreMapper.load(player.getSinglesPlayerId()).getTotalWins() * additionalWinPoint) + totalMission;
			
			checkScore.setTotalWin(totalWin);
			checkScore.setTotalMission(totalMission);
			checkScore.setTotalScore(totalScore);
			checkScore.setTotalLose(totalLose);
		}
		return checkScore;
	}
	
	//総合点の計算
	public CheckScore totalScore(SinglesPlayer player, List<DoublesScore> doublesScore, AdditionalScoreMapper additionalScoreMapper) {
		
		CheckScore checkScore = new CheckScore();
		
		for (SinglesScore score : player.getSinglesScoreList()) {
			if (score.getMyMatchScore().equals(winScore)) {
				totalWin += plusNumber;
			} else {
				totalLose += plusNumber;
			}
			if (score.getMission() != null) {
				totalMission += score.getMission();
			}

		}
		for (DoublesScore score : doublesScore) {
			if (score.getMyMatchScore().equals(winScore)) {
				totalWin += plusNumber;
			} else {
				totalLose += plusNumber;
			}
			if (score.getMission() != null) {
				totalMission += score.getMission();
			}
		}
		totalScore = (totalWin * winPoint) + totalMission;
		
		//追加得点の加算
		if(additionalScoreMapper.load(player.getSinglesPlayerId()) != null) {
			totalWin += additionalScoreMapper.load(player.getSinglesPlayerId()).getTotalWins();
			totalLose += additionalScoreMapper.load(player.getSinglesPlayerId()).getTotalLoss();
			totalMission += additionalScoreMapper.load(player.getSinglesPlayerId()).getMissions();
			totalScore += ((additionalScoreMapper.load(player.getSinglesPlayerId()).getTotalWins() * additionalWinPoint) + additionalScoreMapper.load(player.getSinglesPlayerId()).getMissions());
		}
		checkScore.setTotalWin(totalWin);
		checkScore.setTotalMission(totalMission);
		checkScore.setTotalScore(totalScore);
		checkScore.setTotalLose(totalLose);
		return checkScore;
	}

}

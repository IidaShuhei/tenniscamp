package com.example.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.DoublesPlayer;
import com.example.domain.DoublesScore;
import com.example.domain.PlayerListDtoComparator;
import com.example.domain.SinglesPlayer;
import com.example.domain.SinglesScore;
import com.example.dto.PlayerListDto;
import com.example.dto.ResultListDto;
import com.example.mapper.AdditionalScoreMapper;
import com.example.mapper.DoublesPlayerMapper;
import com.example.mapper.DoublesScoreMapper;
import com.example.mapper.SinglesPlayerMapper;
import com.example.mapper.SinglesScoreMapper;
import com.example.utils.CheckScore;

@Service
@Transactional
public class ShowPlayersService {

	@Autowired
	private SinglesPlayerMapper singlesPlayerMapper;

	@Autowired
	private SinglesScoreMapper singlesScoreMapper;

	@Autowired
	private DoublesPlayerMapper doublesPlayerMapper;

	@Autowired
	private DoublesScoreMapper doublesScoreMapper;
	
	@Autowired
	private AdditionalScoreMapper additionalScoreMapper;

	// シングルスプレイヤーを削除
	public void deleteSinglesPlayer(Integer singlesPlayerId) {
		SinglesPlayer player = singlesPlayerMapper.load(singlesPlayerId);
		singlesScoreMapper.deleteSinglesResults(singlesPlayerId);
		singlesScoreMapper.deleteSinglesResultsFromOpponent(singlesPlayerId);
		doublesScoreMapper.deleteDoublesResults(player.getDoublesPlayerId());
		doublesScoreMapper.deleteDoublesResultsFromOpponent(player.getDoublesPlayerId());
		
		singlesPlayerMapper.updateDoublesIdToNULL(player.getDoublesPlayerId());
		doublesPlayerMapper.deleteDoublesPlayer(player.getDoublesPlayerId());
		additionalScoreMapper.delete(singlesPlayerId);
		singlesPlayerMapper.deleteSinglesPlayer(singlesPlayerId);
	}
	
	//ダブルスペアを削除
	public void deleteDoublesPlayer(Integer playerId) {
		singlesPlayerMapper.updateDoublesIdToNULL(playerId);
		doublesScoreMapper.deleteDoublesResults(playerId);
		doublesScoreMapper.deleteDoublesResultsFromOpponent(playerId);
		doublesPlayerMapper.deleteDoublesPlayer(playerId);
	}
	
	//団体戦結果を削除
	public void deleteTeamPlayer(Integer playerId) {
		additionalScoreMapper.delete(playerId);
	}

	/**
	 * ダブルスの選手を登録する際に選手全員を返すサービス.
	 * 
	 * @return 選手一覧
	 */
	public List<SinglesPlayer> findAllPlayer() {
		return singlesPlayerMapper.findAllPlayer();
	}

	// シングルス選手を全て取得
	public List<SinglesPlayer> findAllSinglesPlayers() {
		return singlesPlayerMapper.findAllSinglesPlayers();
	}

	/**
	 * ダブルスの選手を登録する際に選ばれた人以外の選手を返すサービス.
	 * 
	 * @param singlesPlayerId シングルスプレイヤーID
	 * @return 選手一覧
	 */
	public List<SinglesPlayer> findPlayerExceptByPlayerId(Integer singlesPlayerId) {
		return singlesPlayerMapper.findPlayerExceptByPlayerId(singlesPlayerId);
	}

	// シングルスの試合結果を登録する際に選ばれた人以外の選手を返すサービス.
	public List<SinglesPlayer> findPlayersExceptSinglesPlayerId(Integer singlesPlayerId) {
		return singlesPlayerMapper.findPlayersExceptSinglesPlayerId(singlesPlayerId);
	}

	// シングルスの結果を取得
	public List<ResultListDto> findSinglesResult() {
		List<SinglesScore> singlesScoreList = singlesScoreMapper.findAll();
		List<ResultListDto> resultListDtoList = new ArrayList<>();

		singlesScoreList.stream().forEach(score -> {
			ResultListDto dto = new ResultListDto(score,null);
			resultListDtoList.add(dto);
			dto.removeSameResult(resultListDtoList, dto);
		});

		return resultListDtoList;
	}

	// ダブルスの結果を取得
	public List<ResultListDto> findDoublesResult() {
		List<DoublesScore> doublesScoreList = doublesScoreMapper.findAll();
		List<ResultListDto> resultListDtoList = new ArrayList<>();

		doublesScoreList.stream().forEach(score -> {
			ResultListDto dto = new ResultListDto(null, score);
			resultListDtoList.add(dto);
			dto.removeSameResult(resultListDtoList, dto);
		});

		return resultListDtoList;
	}

	/**
	 * シングルス選手一覧を表示するサービス.
	 * 
	 * @return 選手DTO
	 */
	public List<PlayerListDto> findAllSinglesPlayer() {
		List<PlayerListDto> playerListDtoList = new ArrayList<>();
		List<SinglesPlayer> singlesPlayerList = singlesPlayerMapper.findAll();
		singlesPlayerList.stream().forEach(player -> {
			CheckScore score = new CheckScore();
			score = score.calcScore(player.getSinglesScoreList(), null);
			PlayerListDto dto = new PlayerListDto(player,null,score);
			playerListDtoList.add(dto);
		});
		return orderPlayer(playerListDtoList);
	}

	/**
	 * ダブルス選手一覧を表示するサービス.
	 * 
	 * @return 選手DTO
	 */
	public List<PlayerListDto> findAllDoublesPlayer() {
		List<PlayerListDto> playerListDtoList = new ArrayList<>();
		List<DoublesPlayer> doublesPlayerList = doublesPlayerMapper.findAll();
		for (DoublesPlayer player : doublesPlayerList) {
			CheckScore score = new CheckScore();
			score = score.calcScore(null, player.getDoublesScoreList());
			PlayerListDto dto = new PlayerListDto(null, player,score);
			playerListDtoList.add(dto);
		}
		return orderPlayer(playerListDtoList);
	}
	
	//団体戦の結果を表示する
	public List<PlayerListDto> findAllPlayersTeamResult() {
		List<PlayerListDto> playerListDtoList = new ArrayList<>();
		List<SinglesPlayer> singlesPlayerList = singlesPlayerMapper.findAll();
		singlesPlayerList.stream().forEach(player -> {
			CheckScore score = new CheckScore();
			score = score.calcTeamResult(player, additionalScoreMapper);
			PlayerListDto dto = new PlayerListDto(player,null,score);
			playerListDtoList.add(dto);
		});
		return orderPlayer(playerListDtoList);
	}

	/**
	 * シングルス・ダブルス、団体戦、すべて合わせた得点のプレイヤー一覧
	 * 
	 * @return 総合
	 */
	public List<PlayerListDto> findAllPlayers() {
		List<PlayerListDto> playerListDtoList = new ArrayList<>();
		List<SinglesPlayer> singlesPlayerList = singlesPlayerMapper.findAll();
		for (SinglesPlayer player : singlesPlayerList) {
			List<DoublesScore> doublesScore = doublesScoreMapper.findByDoublesPlayerId(player.getDoublesPlayerId());
			CheckScore score = new CheckScore();
			score = score.totalScore(player, doublesScore, additionalScoreMapper);
			PlayerListDto dto = new PlayerListDto(player,null,score);
			playerListDtoList.add(dto);
		}
		return orderPlayer(playerListDtoList);
	}
	
	// 選手の並び替え
	public List<PlayerListDto> orderPlayer(List<PlayerListDto> playerListDtoList) {
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
	
	// ダブルス選手をすべて取得
	public List<DoublesPlayer> findAllDoublesPlayers() {
		return doublesPlayerMapper.findAllDoublesPlayer();
	}

	public List<DoublesPlayer> findPlayersExceptDoublesPlayerId(Integer doublesPlayerId) {
		return doublesPlayerMapper.findPlayersExceptDoublesPlayerId(doublesPlayerId);
	}

}

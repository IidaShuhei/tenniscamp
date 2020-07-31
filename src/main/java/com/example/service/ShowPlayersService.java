package com.example.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.common.UploadPathConfiguration;
import com.example.domain.DoublesPlayer;
import com.example.domain.DoublesScore;
import com.example.domain.PlayerListDtoComparator;
import com.example.domain.SinglesPlayer;
import com.example.domain.SinglesScore;
import com.example.dto.PlayerListDto;
import com.example.dto.ResultListDto;
import com.example.mapper.DoublesPlayerMapper;
import com.example.mapper.DoublesScoreMapper;
import com.example.mapper.SinglesPlayerMapper;
import com.example.mapper.SinglesScoreMapper;

@Service
@Transactional
public class ShowPlayersService {

	@Autowired
	private UploadPathConfiguration uploadPathConfiguration;
	
	@Autowired
	private SinglesPlayerMapper singlesPlayerMapper;
	
	@Autowired
	private SinglesScoreMapper singlesScoreMapper;
	
	@Autowired
	private DoublesPlayerMapper doublesPlayerMapper;
	
	@Autowired
	private DoublesScoreMapper doublesScoreMapper;
	
	/**
	 * ダブルスの選手を登録する際に選手全員を返すサービス.
	 * 
	 * @return 選手一覧
	 */
	public List<SinglesPlayer> findAllPlayer() {
		return singlesPlayerMapper.findAllPlayer();
	}
	
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
	
	public List<SinglesPlayer> findPlayersExceptSinglesPlayerId(Integer singlesPlayerId) {
		return singlesPlayerMapper.findPlayersExceptSinglesPlayerId(singlesPlayerId);
	}
	
	public List<ResultListDto> findSinglesResult() {
		List<SinglesScore> singlesScoreList = singlesScoreMapper.findAll();
		List<ResultListDto> resultListDtoList = new ArrayList<>();
		
		for(SinglesScore score : singlesScoreList) {
			ResultListDto dto = new ResultListDto();
			dto.setPlayerId(score.getSinglesPlayerId());
			dto.setOpponentId(score.getOpponentSinglesPlayerId());
			dto.setPlayerScore(score.getMyMatchScore());
			dto.setOpponentScore(score.getOpponentMatchScore());
			
			resultListDtoList.add(dto);
			
			for (int i = 0; i < resultListDtoList.size(); i++) {
				if(resultListDtoList.get(i).getPlayerId().equals(dto.getOpponentId()) && resultListDtoList.get(i).getOpponentId().equals(dto.getPlayerId())) {
					resultListDtoList.remove(i);
				}
			}
			
			
		}
		return resultListDtoList;
	}
	
	public List<ResultListDto> findDoublesResult() {
		List<DoublesScore> doublesScoreList = doublesScoreMapper.findAll();
		List<ResultListDto> resultListDtoList = new ArrayList<>();
		
		for(DoublesScore score : doublesScoreList) {
			ResultListDto dto = new ResultListDto();
			dto.setPlayerId(score.getDoublesPlayerId());
			dto.setOpponentId(score.getOpponentDoublesPlayerId());
			dto.setPlayerScore(score.getMyMatchScore());
			dto.setOpponentScore(score.getOpponentMatchScore());
			resultListDtoList.add(dto);
			
			for (int i = 0; i < resultListDtoList.size(); i++) {
				if(resultListDtoList.get(i).getPlayerId().equals(dto.getOpponentId()) && resultListDtoList.get(i).getOpponentId().equals(dto.getPlayerId())) {
					resultListDtoList.remove(i);
				}
			}
		}
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
		for(SinglesPlayer player : singlesPlayerList) {
			PlayerListDto dto = new PlayerListDto();
			
//			String imagePath = player.getImagePath();
//			String uploadPath = uploadPathConfiguration.getUploadPath() + imagePath;
//			try (FileInputStream fis = new FileInputStream(uploadPath);) {
//				StringBuffer data = new StringBuffer();
//				ByteArrayOutputStream os = new ByteArrayOutputStream();
//				byte[] buffer = new byte[1024];
//				// バイト配列に変換
//				while (true) {
//					int len = fis.read(buffer);
//					if (len < 0) {
//						break;
//					}
//					os.write(buffer, 0, len);
//				}
//				// 画像データをbaseにエンコード
//				String base64 = new String(
//						org.apache.tomcat.util.codec.binary.Base64.encodeBase64(os.toByteArray()),"ASCII");
//				// 画像タイプはJPEG
//				data.append("data:image/jpeg;base64,");
//				data.append(base64);
//				
//				dto.setImagePath(data.toString());
//				
//			} catch (Exception e) {     
//				e.printStackTrace();
//				return null;
//			}
			
			int totalWin = 0;
			int totalLose = 0;
			int totalMission = 0;
			int totalScore = 0;
			
			dto.setPlayerId(player.getSinglesPlayerId());
			dto.setPlayerName(player.getSinglesPlayerName());
			for(SinglesScore score : player.getSinglesScoreList()) {
				if(score.getMyMatchScore().equals(4)) {
					totalWin += 1;
				} else {
					totalLose += 1;
				}
				
				if(score.getMission() != null) {
					totalMission += score.getMission();
				}
				
			}
			totalScore = (totalWin * 7) + totalMission;
			dto.setTotalWin(totalWin);
			dto.setTotalLose(totalLose);
			dto.setTotalMission(totalMission);
			dto.setTotalScore(totalScore);
			playerListDtoList.add(dto);
		}
		Collections.sort(playerListDtoList, new PlayerListDtoComparator());
		
		int rank = 1;
		playerListDtoList.get(0).setRanking(rank);
		for (int i = 1; i < playerListDtoList.size(); i++) {
			if(playerListDtoList.get(i).getTotalScore() != playerListDtoList.get(i - 1).getTotalScore()) {
				rank = i + 1;
			}
			playerListDtoList.get(i).setRanking(rank);
		}
		return playerListDtoList;
	}
	
	/**
	 * ダブルス選手一覧を表示するサービス.
	 * 
	 * @return 選手DTO
	 */
	public List<PlayerListDto> findAllDoublesPlayer() {
		List<PlayerListDto> playerListDtoList = new ArrayList<>();
		
		List<DoublesPlayer> doublesPlayerList = doublesPlayerMapper.findAll();
		for(DoublesPlayer player : doublesPlayerList) {
			PlayerListDto dto = new PlayerListDto();
			
//			String imagePath = player.getImagePath();
//			String uploadPath = uploadPathConfiguration.getUploadPath() + imagePath;
//			try (FileInputStream fis = new FileInputStream(uploadPath);) {
//				StringBuffer data = new StringBuffer();
//				ByteArrayOutputStream os = new ByteArrayOutputStream();
//				byte[] buffer = new byte[1024];
//				// バイト配列に変換
//				while (true) {
//					int len = fis.read(buffer);
//					if (len < 0) {
//						break;
//					}
//					os.write(buffer, 0, len);
//				}
//				// 画像データをbaseにエンコード
//				String base64 = new String(
//						org.apache.tomcat.util.codec.binary.Base64.encodeBase64(os.toByteArray()),"ASCII");
//				// 画像タイプはJPEG
//				data.append("data:image/jpeg;base64,");
//				data.append(base64);
//				
//				dto.setImagePath(data.toString());
//				
//			} catch (Exception e) {     
//				e.printStackTrace();
//				return null;
//			}
			
			int totalWin = 0;
			int totalLose = 0;
			int totalMission = 0;
			int totalScore = 0;
			
			dto.setPlayerId(player.getDoublesPlayerId());
			dto.setPlayerName(player.getDoublesPlayerName());
			for(DoublesScore score : player.getDoublesScoreList()) {
				if(score.getMyMatchScore().equals(4)) {
					totalWin += 1;
				} else {
					totalLose += 1;
				}
				
				if(score.getMission() != null) {
					totalMission += score.getMission();
				}
				
			}
			totalScore = (totalWin * 7) + totalMission;
			dto.setTotalWin(totalWin);
			dto.setTotalLose(totalLose);
			dto.setTotalMission(totalMission);
			dto.setTotalScore(totalScore);
			playerListDtoList.add(dto);
		}
		Collections.sort(playerListDtoList, new PlayerListDtoComparator());
		
		int rank = 1;
		playerListDtoList.get(0).setRanking(rank);
		for (int i = 1; i < playerListDtoList.size(); i++) {
			if(playerListDtoList.get(i).getTotalScore() != playerListDtoList.get(i - 1).getTotalScore()) {
				rank = i + 1;
			}
			playerListDtoList.get(i).setRanking(rank);
		}
		return playerListDtoList;
	}
	
	/**
	 * シングルス・ダブルス、両方合わせた得点のプレイヤー一覧
	 * 
	 * @return 総合
	 */
	public List<PlayerListDto> findAllPlayers() {
		List<PlayerListDto> playerListDtoList = new ArrayList<>();
		
		//シングルスの点数
		List<SinglesPlayer> singlesPlayerList = singlesPlayerMapper.findAll();
		for(SinglesPlayer player : singlesPlayerList) {
			PlayerListDto dto = new PlayerListDto();
//			String imagePath = player.getImagePath();
//			String uploadPath = uploadPathConfiguration.getUploadPath() + imagePath;
//			try (FileInputStream fis = new FileInputStream(uploadPath);) {
//				StringBuffer data = new StringBuffer();
//				ByteArrayOutputStream os = new ByteArrayOutputStream();
//				byte[] buffer = new byte[1024];
//				// バイト配列に変換
//				while (true) {
//					int len = fis.read(buffer);
//					if (len < 0) {
//						break;
//					}
//					os.write(buffer, 0, len);
//				}
//				// 画像データをbaseにエンコード
//				String base64 = new String(
//						org.apache.tomcat.util.codec.binary.Base64.encodeBase64(os.toByteArray()),"ASCII");
//				// 画像タイプはJPEG
//				data.append("data:image/jpeg;base64,");
//				data.append(base64);
//				
//				dto.setImagePath(data.toString());
//				
//			} catch (Exception e) {     
//				e.printStackTrace();
//				return null;
//			}
			
			int totalWin = 0;
			int totalLose = 0;
			int totalMission = 0;
			int totalScore = 0;
			
			dto.setPlayerId(player.getSinglesPlayerId());
			dto.setPlayerName(player.getSinglesPlayerName());
			for(SinglesScore score : player.getSinglesScoreList()) {
				if(score.getMyMatchScore().equals(4)) {
					totalWin += 1;
				} else {
					totalLose += 1;
				}
				
				if(score.getMission() != null) {
					totalMission += score.getMission();
				}
				
			}
			
			//ダブルスの点数加算
			List<DoublesScore> doublesScore = doublesScoreMapper.findByDoublesPlayerId(player.getDoublesPlayerId());
			for(DoublesScore score : doublesScore) {
				if(score.getMyMatchScore().equals(4)) {
					totalWin += 1;
				} else {
					totalLose += 1;
				}
				
				if(score.getMission() != null) {
					totalMission += score.getMission();
				}
				
			}
			
			totalScore = (totalWin * 7) + totalMission;
			dto.setTotalWin(totalWin);
			dto.setTotalLose(totalLose);
			dto.setTotalMission(totalMission);
			dto.setTotalScore(totalScore);
			playerListDtoList.add(dto);
		}
		
		Collections.sort(playerListDtoList, new PlayerListDtoComparator());
		
		int rank = 1;
		playerListDtoList.get(0).setRanking(rank);
		for (int i = 1; i < playerListDtoList.size(); i++) {
			if(playerListDtoList.get(i).getTotalScore() != playerListDtoList.get(i - 1).getTotalScore()) {
				rank = i + 1;
			}
			playerListDtoList.get(i).setRanking(rank);
		}
		return playerListDtoList;
	}
	
	public List<DoublesPlayer> findAllDoublesPlayers() {
		return doublesPlayerMapper.findAllDoublesPlayer();
	}
	
	public List<DoublesPlayer> findPlayersExceptDoublesPlayerId(Integer doublesPlayerId) {
		return doublesPlayerMapper.findPlayersExceptDoublesPlayerId(doublesPlayerId);
	}
	
}

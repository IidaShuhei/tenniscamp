package com.example.service;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.common.UploadPathConfiguration;
import com.example.domain.PlayerListDtoComparator;
import com.example.domain.SinglesPlayer;
import com.example.domain.SinglesScore;
import com.example.dto.PlayerListDto;
import com.example.mapper.SinglesPlayerMapper;

@Service
@Transactional
public class ShowPlayersService {

	@Autowired
	private SinglesPlayerMapper playerMapper;
	
	@Autowired
	private UploadPathConfiguration uploadPathConfiguration;
	
	@Autowired
	private SinglesPlayerMapper singlesPlayerMapper;
	
	/**
	 * ダブルスの選手を登録する際に選手全員を返すサービス.
	 * 
	 * @return 選手一覧
	 */
	public List<SinglesPlayer> findAllPlayer() {
		return singlesPlayerMapper.findAllPlayer();
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
	
	/**
	 * 選手一覧を表示するサービス.
	 * 
	 * @return 選手DTO
	 */
	public List<PlayerListDto> findAll() {
		List<PlayerListDto> playerListDtoList = new ArrayList<>();
		
		List<SinglesPlayer> singlesPlayerList = playerMapper.findAll();
		for(SinglesPlayer player : singlesPlayerList) {
			PlayerListDto dto = new PlayerListDto();
			
			String imagePath = player.getImagePath();
			String uploadPath = uploadPathConfiguration.getUploadPath() + imagePath;
			try (FileInputStream fis = new FileInputStream(uploadPath);) {
				StringBuffer data = new StringBuffer();
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				// バイト配列に変換
				while (true) {
					int len = fis.read(buffer);
					if (len < 0) {
						break;
					}
					os.write(buffer, 0, len);
				}
				// 画像データをbaseにエンコード
				String base64 = new String(
						org.apache.tomcat.util.codec.binary.Base64.encodeBase64(os.toByteArray()),"ASCII");
				// 画像タイプはJPEG
				data.append("data:image/jpeg;base64,");
				data.append(base64);
				
				dto.setImagePath(data.toString());
				
			} catch (Exception e) {     e.printStackTrace();
				return null;
			}
			
			int totalWin = 0;
			int totalLose = 0;
			int totalMission = 0;
			int totalScore = 0;
			
			dto.setSinglesPlayerId(player.getSinglesPlayerId());
			dto.setSinglesPlayerName(player.getSinglesPlayerName());
			for(SinglesScore score : player.getSinglesScoreList()) {
				if(score.getMatchScore().equals(4)) {
					totalWin += 1;
				} else {
					totalLose += 1;
				}
				
				if(score.getMission() != null) {
					totalMission += score.getMission();
				}
				
			}
			totalScore = (totalWin * 5) + totalMission;
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
	
}

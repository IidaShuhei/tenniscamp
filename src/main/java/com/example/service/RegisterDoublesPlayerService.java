package com.example.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.domain.DoublesPlayer;
import com.example.domain.SinglesPlayer;
import com.example.mapper.DoublesPlayerMapper;
import com.example.mapper.SinglesPlayerMapper;

@Service
@Transactional
public class RegisterDoublesPlayerService {

	@Autowired
	private DoublesPlayerMapper doublesPlayerMapper;
	
	@Autowired
	private SinglesPlayerMapper singlesPlayerMapper;
	
	public void insert(Integer doubles1, Integer doubles2) {
		DoublesPlayer doublesPlayer = new DoublesPlayer();
		SinglesPlayer player1 = singlesPlayerMapper.load(doubles1);
		SinglesPlayer player2 = singlesPlayerMapper.load(doubles2);
		doublesPlayer.setDoublesPlayerName(player1.getSinglesPlayerName() + "・" + player2.getSinglesPlayerName());
		Integer doublesPlayerId = doublesPlayerMapper.register(doublesPlayer);
		
		//ダブルスペアを登録
		singlesPlayerMapper.updateDoublesId(doublesPlayerId,doubles2);
		singlesPlayerMapper.updateDoublesId(doublesPlayerId,doubles1);
	}
	
}

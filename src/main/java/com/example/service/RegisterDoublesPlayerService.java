package com.example.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.domain.DoublesPlayer;
import com.example.domain.SinglesPlayer;
import com.example.form.RegisterDoublesPlayerForm;
import com.example.mapper.DoublesPlayerMapper;
import com.example.mapper.SinglesPlayerMapper;

@Service
@Transactional
public class RegisterDoublesPlayerService {

	@Autowired
	private DoublesPlayerMapper doublesPlayerMapper;
	
	@Autowired
	private SinglesPlayerMapper singlesPlayerMapper;
	
	public void registerDoublesPlayer(RegisterDoublesPlayerForm form) {
		DoublesPlayer doublesPlayer = new DoublesPlayer();
		SinglesPlayer player1 = singlesPlayerMapper.load(form.getDoublesPlayerId1());
		SinglesPlayer player2 = singlesPlayerMapper.load(form.getDoublesPlayerId2());
		doublesPlayer.setDoublesPlayerName(player1.getSinglesPlayerName() + "・" + player2.getSinglesPlayerName());
		Integer doublesPlayerId = doublesPlayerMapper.registerDoublesPlayer(doublesPlayer);
		
		//ダブルスペアを登録
		singlesPlayerMapper.updateSinglesPlayer(doublesPlayerId,form.getDoublesPlayerId2());
		singlesPlayerMapper.updateSinglesPlayer(doublesPlayerId,form.getDoublesPlayerId1());
	}
	
}

package com.example.service;


import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.common.UploadPathConfiguration;
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
	
//	@Autowired
//	private UploadPathConfiguration uploadPathConfiguration;
	
	public void registerDoublesPlayer(RegisterDoublesPlayerForm form
//			, MultipartFile uploadFile
			) {
		DoublesPlayer doublesPlayer = new DoublesPlayer();
		SinglesPlayer player1 = singlesPlayerMapper.load(form.getDoublesPlayerId1());
		SinglesPlayer player2 = singlesPlayerMapper.load(form.getDoublesPlayerId2());
		doublesPlayer.setDoublesPlayerName(player1.getSinglesPlayerName() + "・" + player2.getSinglesPlayerName());
		//画像チェック
//    	if (uploadFile != null) {
//			
//	        try {
//	        	
//	            // 保存先を定義
//	            String uploadPath = uploadPathConfiguration.getUploadPath();
//	            byte[] bytes = uploadFile.getBytes();
//	            
//	            // 指定ファイルへ読み込みファイルを書き込み
//	            BufferedOutputStream stream = new BufferedOutputStream(
//						new FileOutputStream(new File(uploadPath + new File(uploadFile.getOriginalFilename()))));
//				stream.write(bytes);
//				stream.close();
//
//				// 圧縮
//				File input = new File(uploadPath + new File(uploadFile.getOriginalFilename()));
//				BufferedImage image = ImageIO.read(input);
//				OutputStream os = new FileOutputStream(input);
//				Iterator<ImageWriter> writers = ImageIO
//						.getImageWritersByFormatName("png");
//				ImageWriter writer = writers.next();
//				ImageOutputStream ios = ImageIO.createImageOutputStream(os);
//				writer.setOutput(ios);
//				ImageWriteParam param = new JPEGImageWriteParam(null);
//				param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
//				param.setCompressionQuality(0.30f);
//				writer.write(null, new IIOImage(image, null, null), param);
//				os.close();
//				ios.close();
//				writer.dispose();
//				
//				File fileName = new File(uploadFile.getOriginalFilename());
//	            String imagePath = fileName.toString();
//				
//	            doublesPlayer.setImagePath(imagePath);
//	            
//			} catch (Exception exception) {
//				
//				System.err.println("エラー");
//				System.out.println(exception);
//
//			}
//		}
		Integer doublesPlayerId = doublesPlayerMapper.registerDoublesPlayer(doublesPlayer);
		
		singlesPlayerMapper.updateSinglesPlayer(doublesPlayerId,form.getDoublesPlayerId2());
		singlesPlayerMapper.updateSinglesPlayer(doublesPlayerId,form.getDoublesPlayerId1());
	}
	
}

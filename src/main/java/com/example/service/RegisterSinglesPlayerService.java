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
import com.example.domain.SinglesPlayer;
import com.example.form.RegisterSinglesPlayerForm;
import com.example.mapper.SinglesPlayerMapper;

@Service
@Transactional
public class RegisterSinglesPlayerService {

	@Autowired
	private SinglesPlayerMapper singlesPlayerMapper;
	
//	@Autowired
//	private UploadPathConfiguration uploadPathConfiguration;
	
	public void registerSinglesPlayer(RegisterSinglesPlayerForm form
//			, MultipartFile uploadFile
			) {
		
		SinglesPlayer singlesPlayer = new SinglesPlayer();
		singlesPlayer.setSinglesPlayerName(form.getSinglesPlayerName());
		
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
//				singlesPlayer.setImagePath(imagePath);
//	            
//			} catch (Exception exception) {
//				
//				System.err.println("エラー");
//				System.out.println(exception);
//
//			}
//		}
    	
    	singlesPlayerMapper.registerSinglesPlayer(singlesPlayer);
    	
	}
	
}

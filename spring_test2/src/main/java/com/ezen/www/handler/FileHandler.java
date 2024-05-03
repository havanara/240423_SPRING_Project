package com.ezen.www.handler;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.www.domain.FileVO;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@Slf4j
@Component //사용자 클래스를 bean으로 등록
public class FileHandler {
	private final String UP_DIR = "D:\\_myProject\\_java\\_fileUpload"; //파일 등록하는 내 위치
	
	public List<FileVO> uploadFiles(MultipartFile[] files){
		List<FileVO> flist = new ArrayList<>();
		//flist 두번째 글자에 대문자가 들어가면 jsp에서 인지를 못함 ex)fList는 인지를 못해 두번째만!
		
		//FileVO 생성, 파일을 저장, 썸네일(이미지만) 저장
		//날짜로 폴더를 생성하여 업로드 파일 관리
		LocalDate date = LocalDate.now(); //오늘 날짜가 2024-05-03 형태로 입력됨
		String today = date.toString(); //String으로 변환
		today = today.replace("-", File.separator); // - 를 파일 경로 표시로 변환(\ 형태로 변환)
		
		//File folders 폴더의 경로는 D:\\_myProject\\_java\\_fileUpload\\2024\\05\\03 형태
		File folders = new File(UP_DIR, today);
		
		//폴더 생성
		if(!folders.exists()) {
			folders.mkdirs(); //mkdirs() : 모든 하위 폴더까지 생성 / mkdir() : 하나의 하위 폴더 생성 
		}
		
		//files를 가지고 객체 설정
		for(MultipartFile file : files) {
			FileVO fvo = new FileVO();
			fvo.setSaveDir(today);
			fvo.setFileSize(file.getSize());
			
			String originalFileName = file.getOriginalFilename();
			String fileName = originalFileName.substring(
					originalFileName.lastIndexOf(File.separator)+1);
			fvo.setFileName(fileName);
			
			UUID uuid = UUID.randomUUID();
			String uuidStr = uuid.toString();
			fvo.setUuid(uuidStr);
//			여기까지는 세팅 완료
			
//			디스크에 저장 -> 저장 객체를 생성
			String fullFileName = uuidStr+"_"+fileName;
			File storeFile = new File(folders, fullFileName);
			
			//실제 파일이 저장되려면 첫 경로부터 다 설정되어야 저장이 됨
//			D:\_myProject\_java\_fileUpload\\2024\\05\\03\\uuid_name.jpg
			
			try {
				file.transferTo(storeFile); //저장
				//썸네일(이미지만) 저장
				//이미지인지 먼저 확인
				if(isImgeFile(storeFile)) {
					fvo.setFileType(1); //이미지는 타입이 1
					//썸네일 생성
					File thumbNail = new File(folders, uuidStr+"_th_"+fileName);
					Thumbnails.of(storeFile).size(200, 200).toFile(thumbNail);
				}
			} catch (Exception e) {
				log.info("파일 저장 오류 발생");
				e.printStackTrace();
			}
			flist.add(fvo);
		}
		return flist;
	}
	
	private boolean isImgeFile(File storeFile) throws IOException {
		String mimeType = new Tika().detect(storeFile); //type image/jpg
		return mimeType.startsWith("image")? true : false;
	}
}

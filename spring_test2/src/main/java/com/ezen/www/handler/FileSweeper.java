package com.ezen.www.handler;

import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ezen.www.domain.FileVO;
import com.ezen.www.repository.FileDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
@EnableScheduling
public class FileSweeper {
	//직접 DB에 접속해서 
	
	private final FileDAO fdao;
	
	private final String BASE_PATH = "D:\\_myProject\\_java\\_fileUpload\\"; //삭제하는 기본 경로
	
	//매일 정해진 시간에 스케줄러를 실행
	//매일 등록된 파일과 (DB) <-> 해당일의 폴더에 있는 파일이 일치하는 파일은 남기고 일치하지 않는 파일은 삭제
	//cron -> 주기를 설정할 때 사용하는 방식(초 분 시 일 월 요일 년도 -> 생략 가능) 쉼표 없이 한칸씩 띄어쓰기로 사용
	@Scheduled(cron="0 33 14 * * *")
	public void fileSweeper() {
		log.info(">> FileSweeper Running Start : {}", LocalDateTime.now());
		
		//DB에 등록된 파일 목록을 일단 가져오기
		List<FileVO> dbList = fdao.selectListAllFile();
		
		//저장소를 검색할 때 필요한 파일 경로 리스트(실제 존재해야하는 리스트)
		List<String> currFiles = new ArrayList<String>();
		
		for(FileVO fvo : dbList) {
			String filePath = fvo.getSaveDir()+File.separator+fvo.getUuid();
			String fileName = fvo.getFileName();
			currFiles.add(BASE_PATH+filePath+"_"+fileName);
			
			//이미지라면 썸네일 경로도 추가
			if(fvo.getFileType()>0) {
				currFiles.add(BASE_PATH+filePath+"_th_"+fileName);
			}
		}
		
		log.info(">> currFile >> {}", currFiles);
		
		//오늘 날짜를 반영한 폴더 구조 경로 만들기
		LocalDate now = LocalDate.now();
		String today = now.toString();
		today = today.replace("-", File.separator);
		
		//경로를 기반으로 저장되어있는 파일을 검색
		File dir = Paths.get(BASE_PATH+today).toFile();
		File[] allFileObject = dir.listFiles();
		
		//실제 저장되어 있는 파일과 DB에 존재하는 파일을 비교하여 없는 파일은 삭제 진행
		for(File file : allFileObject) {
			String storedFileName = file.toPath().toString();
			//없으면 삭제 반드시 앞에 ! 붙이기(있으면 지우면 안됨)
			if(!currFiles.contains(storedFileName)) {
				file.delete(); //파일 삭제
				log.info(">> delete files >> {}", storedFileName);
			}
		}
		log.info(">> FileSweeper Running End : {}", LocalDateTime.now());
	}
}

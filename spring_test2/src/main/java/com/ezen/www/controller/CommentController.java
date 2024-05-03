package com.ezen.www.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ezen.www.domain.CommentVO;
import com.ezen.www.domain.PagingVO;
import com.ezen.www.handler.PagingHandler;
import com.ezen.www.service.CommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/comment/*")
@RestController
public class CommentController {

	private final CommentService csv;
	
	//consumes 들어오는거 produces 나가는거
	@PostMapping(value = "/post", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> post(@RequestBody CommentVO cvo){
		log.info(">> cvo >>, {} ", cvo);
		int isOK = csv.post(cvo);
		return isOK > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) : 
			new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value = "/{bno}/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<List<CommentVO>> list(@PathVariable("bno") int bno, @PathVariable("page")int page){
	public ResponseEntity<PagingHandler> list(@PathVariable("bno") int bno, @PathVariable("page")int page){
		//pagingVO / PagingHandler
		PagingVO pgvo = new PagingVO(page, 5);	
//		List <CommentVO> list = csv.getList(bno);
		PagingHandler ph = csv.getList(bno, pgvo);
		return new ResponseEntity<PagingHandler>(ph, HttpStatus.OK);
//		return new ResponseEntity<List<CommentVO>>(list, HttpStatus.OK);
	}
//	@GetMapping(value = "/{bno}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<List<CommentVO>> list(@PathVariable("bno") int bno){
//		log.info(">> bno >>, {} ", bno);
//		List <CommentVO> list = csv.getList(bno);
//		return new ResponseEntity<List<CommentVO>>(list, HttpStatus.OK);
//	}
	
	//responseBody 방식
	@ResponseBody
	@PutMapping("/modify")
	public String modify(@RequestBody CommentVO cvo) {
		log.info(">> cvo >> {} ", cvo);
		int isOk = csv.modify(cvo);
		return isOk > 0? "1" : "0";
	}
	
//	@PutMapping(value = "/modify", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
//	public ResponseEntity<String> modify(@RequestBody CommentVO cvo){
//		log.info(">> cvo >> {} ", cvo);
//		int isOK = csv.modify(cvo);
//		return isOK > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) :
//			new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
//	}
	
	@ResponseBody
	@DeleteMapping("/{cno}")
	public String delete(@PathVariable("cno") int cno){
		log.info(">> cno >> {} ", cno);
		int isOK = csv.delete(cno);
		return isOK > 0 ? "1" : "0";

	}
}
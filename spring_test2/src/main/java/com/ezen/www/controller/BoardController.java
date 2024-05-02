package com.ezen.www.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.www.domain.BoardVO;
import com.ezen.www.domain.PagingVO;
import com.ezen.www.handler.PagingHandler;
import com.ezen.www.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/board/*")
@Slf4j
@RequiredArgsConstructor
@Controller
public class BoardController {
	private final BoardService bsv;
	
	@GetMapping("/register")
	public void register() {
	}
	
	@PostMapping("/insert")
	public String insert(BoardVO bvo) {
		log.info(">> bvo >> {} ", bvo);
		int isOK = bsv.insert(bvo);
		return "index";
	}
	
	@GetMapping("/list")
	public String list(Model m, PagingVO pgvo) {
		log.info(">> pagingVO >> {} ", pgvo);
		
		//페이징 처리 추가
		List<BoardVO> list = bsv.getList(pgvo);
//		List<BoardVO> list = bsv.getList();
		
		//totalCount 구해오기
		int totalCount = bsv.getTotal(pgvo);
		PagingHandler ph = new PagingHandler(pgvo, totalCount);
		
		//가져온 리스트 -> /board/list.jsp로 전달
		m.addAttribute("list", list); //Model m은 jsp로 보내는것
		m.addAttribute("ph", ph);

		return "/board/list"; //어디로 보내는지
	}
	
	@GetMapping({"/detail","/modify"})
	public void detail(@RequestParam("bno")int bno, Model m) {
		BoardVO bvo = bsv.getDetail(bno);
		m.addAttribute("bvo", bvo);
	}
	
	//RedirectAttributes : redirect로 보내는 객체
	@PostMapping("/modify")
	public String modify(BoardVO bvo, RedirectAttributes re) {
		int isOk = bsv.modify(bvo);
		re.addAttribute("bno", bvo.getBno());
		return "redirect:/board/detail";
	}
	
	@GetMapping("/remove")
	public String remove(@RequestParam("bno")int bno) {
		int isOK = bsv.remove(bno);
		return "redirect:/board/list";
	}
}

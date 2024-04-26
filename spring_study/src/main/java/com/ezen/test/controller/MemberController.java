package com.ezen.test.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.test.domain.MemberVO;
import com.ezen.test.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/member/*")
@Controller
public class MemberController {

	private final MemberService msv;
	
	@GetMapping("/register")
	public void register() {
	}
	
	@PostMapping("/register")
	public String register(MemberVO mvo) {
		log.info(">> mvo >> {} ", mvo);
		int isOK = msv.insert(mvo);
		return "index";
	}
	
	@GetMapping("/login")
	public void login() {	
	}
	
	@PostMapping("/login")
	public String login(MemberVO mvo, HttpServletRequest request, Model m) { //Model 객체 (데이터 보내는!!)
		log.info(">> mvo >> {} ", mvo);
		
		//mvo 객체가 DB의 값과 일치하는 객체 가져오기
		MemberVO loginMvo = msv.isUser(mvo);
		log.info(">> loginMvo >> {} ", loginMvo);
		
		if(loginMvo != null) {
			//로그인 성공 시
			HttpSession ses = request.getSession();
			ses.setAttribute("ses", loginMvo); //세션에 로그인 객체 저장
			ses.setMaxInactiveInterval(10*60); //로그인 유지 시간
		}else { //로그인 실패 시
			m.addAttribute("msg_login", "1");
		}
		return "index";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, Model m) {
		//라스트 로그인 업데이트 -> 세션 객체 삭제 -> 세션 끊기
		MemberVO mvo = (MemberVO)request.getSession().getAttribute("ses");
		msv.lastLoginUpdate(mvo.getId());
		
		request.getSession().removeAttribute("ses");
		request.getSession().invalidate();
		
		m.addAttribute("msg_logout", "1");
		return "index";
	}
	
	@GetMapping("/modify")
	public void modify() {
	}
	
	@PostMapping("/modify")
	public String modify(MemberVO mvo) {
		log.info(">> mvo >> {} ", mvo);
		msv.modify(mvo);
		return "redirect:/member/logout";
	}
	
	@GetMapping("/delete")
	public String delete(HttpServletRequest request, RedirectAttributes re) {
		MemberVO mvo = (MemberVO)request.getSession().getAttribute("ses");
		msv.delete(mvo.getId());
		
		re.addFlashAttribute("msg_delete", "1"); //flash는 일회성
		return "redirect:/member/logout"; //logout 페이지로 간다는게 아니라 logout 케이스로 간다는 것
	}
	
//	자주 쓰는 method 는 아래처럼 만들어서 메서드 안에서 사용(private로 작성하면 MemberController 내에서만 사용 가능
//	private String getId(HttpServletRequest request) {
//		MemberVO mvo = (MemberVO)request.getSession().getAttribute("ses");
//		return mvo.getId();
//	}
	
//	msv.delete(getId(request)); 로 위의 메서드 사용 가능(쓸때마다 3~4줄 쓸 필요없이 사용 가능)
	
	//form 태그 안에 method="post" 처리 하지 않은 이상 getmapping 처리
	//jsp에서 보내는 모든 경로는 컨트롤러로 감
	//컨트롤러에서 보내는 경로는 전부 jsp로 감(redirect 제외)
}

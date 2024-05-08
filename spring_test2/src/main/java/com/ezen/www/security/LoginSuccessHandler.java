package com.ezen.www.security;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import com.ezen.www.repository.UserDAO;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	
	@Setter
	@Getter
	private String authEmail;
	
	@Setter
	@Getter
	private String authUrl;
	
	//redirect 데이터를 가지고 리다이렉트 하는 역할
	private RedirectStrategy redstg = new DefaultRedirectStrategy();
	
	//로그인 정보, 경로
	private RequestCache reqCache = new HttpSessionRequestCache();
	
	//로그인 일자 기록 lastLogin
	@Inject
	private UserDAO udao;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// authentication 인증된 AuthUser 객체
		
		setAuthEmail(authentication.getName());
		setAuthUrl("/board/list");
		//로그인 일자 기록 lastLogin
		
		int isOK = udao.updateLastLogin(getAuthEmail());

		
		//세션 가져오기
		HttpSession ses = request.getSession();
		if(isOK == 0 || ses == null) {
			return;
		}else {
			//시큐리티에서 로그인에 시도하여 실패하면 로그인에 기록이 남게됨
			//이전에 시도한 시큐리티의 인증 실패 기록 삭제
			ses.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		}
		SavedRequest saveReq = reqCache.getRequest(request, response);
		redstg.sendRedirect(request, response, (saveReq != null ? saveReq.getRedirectUrl() : getAuthUrl()));
	}

}

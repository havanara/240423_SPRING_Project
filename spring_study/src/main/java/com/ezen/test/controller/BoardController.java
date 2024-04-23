package com.ezen.test.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.test.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/board/*")
@Slf4j
@Controller
public class BoardController {
	
	@Inject
	private BoardService bsv;
	
	
}

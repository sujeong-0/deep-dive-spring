package com.deepdive.studyspring.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 이 클래스는 메인 컨트롤러를 담당합니다.
 */
@RestController
@RequestMapping("/")
@Slf4j
public class MainController {

	@GetMapping("")
	public String sayHello(@RequestParam(defaultValue = "name") String name) {
		log.info("controller :::: sayHello()");
		return "Hello World!";
	}
}

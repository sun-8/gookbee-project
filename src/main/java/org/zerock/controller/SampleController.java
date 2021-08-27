package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;


@Controller
@RequestMapping("/sample/*")
/*@RequestMapping은 현재 클래스의 모든 메서드들의 기본적인 URL경로가 된다.
 * SampleController 클래스를 "/sample/*"이라는 경로로 지정했다면 다음과 같은 URL은 모두 SampleController에서 처리된다.
 * 		/sample/aaa
 * 		/sample/bbb
 * 이 어노테이션은 클래스의 선언과 메서드 선언에 사용할 수 있다.*/

@Log4j
public class SampleController {

	@RequestMapping("")
	public void basic() {
		log.info("basic------------------");
	}
}
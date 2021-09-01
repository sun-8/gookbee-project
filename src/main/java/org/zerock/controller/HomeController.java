package org.zerock.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		/*리턴 타입을 String으로 지정
		 * void타입과 더불어서 가장 많이 사용하는 타입이다.
		 * String 타입은 현재 프로젝트의 경우 JSP파일의 이름을 의미한다.
		 * home()메서드는 "home"이라는 문자열을 리턴했기 때문에 경로는 '/WEB-INF/views/home.jsp'경로가 된다.
		 * String에는 아래와 같은 특별한 키워드를 붙여서 사용할 수 있다.
		 * 		redirect - 리다이렉트 방식으로 처리하는 경우(redirect:/sample/ex04)
		 * 			기존 String은 뷰페이지를 반환하지만 redirect:/는 context경로를 반환한다.
		 * 			웹 브라우저에게 다른 페이지로 이동하라고 명령을 내리면 브라우저는 URL을 지시된 주소로 바꾸고 해당 주소로 이동한다.
		 * 			다른 웹 컨테이너에 있는 주소로 이동하며 새로운 페이지에서는 request와 response객체가 새롭게 생성된다.
		 * 				ex) 게시판을 작성하는 과정에서 사용자가 보낸 요청 정보를 이용하여 글쓰기 기능을 수행한다고 할 때
		 * 					redirect를 사용하여 응답 페이지를 부르면 사용자가 실수 혹은 고의로 글쓰기 응답 페이지에서 새로고침을 누른다고 하더라도
		 * 					처음의 요청 정보는 존재하지 않으므로 게시물이 여러번 등록되지 않는다.
		 * 					그렇기 때문에 시스템에 변화가 생기는 요청(회원가입,글쓰기 등)의 경우에 사용한다.
		 * 			최초 요청을 받은 URL1에서 클라이언트에게 redirect할 URL2를 반환하고, 클라이언트에서는 새로운 요청을 생성하여 URL2에게 다시 요청을 보낸다.
		 * 			클라이언트 -> request1 -> URL1 -> redirection -> 클라이언트 -> request2 -> URL2 -> response
		 * 
		 * 		forward - 포워드 방식으로 처리하는 경우(/sample/ex04)
		 * 			페이지의 이동만 존재하며 실제로 웹 브라우저는 다른 페이지로 이동했음을 알 수 없다.
		 * 			그렇기 때문에 웹 브라우저에는 최초에 호출한 URL이 표시되고, 이동한 페이지의 URL정보는 확인할 수 없다.
		 * 			또한 현재 실행중인 페이지와 forward에 의해 호출될 페이지는 Request객체와 Response객체를 공유한다.
		 * 			다시 말해서, forward는 다음으로 이동할 URL로 요청정보를 그대로 저장한다. 
		 * 			그렇기 때문에 사용자가 최초로 요정한 요청정보는 다음 URL에서도 유효하다.
		 * 				ex) 게시판을 작성하는 과정에서 사용자가 보낸 요청정보를 이용하여 글쓰기 기능을 수행할 때 
		 * 					forward를 사용하여 응답페이지를 부르면 문제가 발생한다.
		 * 					만약 사용자가 실수 혹은 고의로 글쓰기 응답페이지에서 새로구침을 누른다면, 
		 * 					요청정보가 그대로 살아있기 때문에 요청이 여러번 전달되어 동일한 게시물이 여러번 등록될 수 있다.
		 * 					그래서 게시판을 제작하는 과정에서는 시스템에 변화가 생기지 않는 단순 조회요청(글목록,검색)의 경우 forward로 응답하는 것이 바람직하다.
		 * 			클라이언트 -> request -> URL1 -> forward -> URL2 -> response -> 클라이언트
		 * */
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		//jsp파일에 ${serverTime}로 데이터를 전달한다.
		//getter/setter가 없으면 자동으로 화면에 전달이 안되기 때문에 Model을 사용해 전달한다.
		
		return "home";
	}
	
}

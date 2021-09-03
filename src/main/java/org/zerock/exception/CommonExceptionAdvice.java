package org.zerock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.log4j.Log4j;

//Controller의 Exception처리//
/*Controller를 작성할 때 예외 상황을 고려하면 처리해야 하는 작업이 엄청나게 늘어나는데, 이러한 작업을 아래와 같은 방식으로 처리할 수 있다.
 * - @ExceptionHandler와 @ControllerAdvice를 이용한 처리
 * - @ResponseEntity를 이용하는 예외 메시지 구성
 * */

@ControllerAdvice
/*@ControllerAdvice는 해당 객체가 스프링의 컨트롤러에서 발생하는 예외를 처리하는 존재임을 명시하는 용도로 사용한다.
 * 별도의 속성값이 없이 사용하면 모든 패키지 전역에 있는 컨트롤러를 담당하게 된다.
 * @ControllerAdvice는 Spring bean으로 명시적으로 선언되거나 클래스 경로 스캐닝을 통해 자동 감지될 수 있다.
 * 이 어노테이션을 적용한 후  @ExceptionHandler로 처리하고 싶은 예외를 잡아 처리하면 된다.
 * @ControllerAdvice는 AOP를 이용하는 방식이다.
 * AOP는 간단히 설명하자면 핵심적인 로직은 아니지만 프로그램에서 필요한 '공통적인 관심사(cross-concern)는 분리'하자는 개념이다.
 * AOP방식을 이용하면 공통적인 예외사항에 대해서는 별도로 @ControllerAdvice를 이용해서 분리하는 방식이다.
 * */

@Log4j
public class CommonExceptionAdvice {

	@ExceptionHandler(Exception.class)
	/*@ExceptionHandler는 해당 메서드가 () 들어가는 예외 타입을 처리한다.
	 * 이 어노테이션의 속성으로는 Exception클래스 타입을 지정할 수 있다. 
	 * 위와 같은 경우 Exception.class를 지정하였으므로 모든 예외에 대한 처리가 except()만을 이용해서 처리할 수 있다.
	 * 만약 특정한 타입의 예외를 다루고 싶다면 Exception.class 대신에 구체적인 예외의 클래스를 지정해야 한다.
	 * */
	public String except(Exception ex,Model model) {
		/*JSP 화면에서도 구체적인 메시지를 보고싶다면 Model을 이용해서 전달하는 것이 좋다.*/
		log.error("Exception.........."+ex.getMessage());
		model.addAttribute("exception",ex);
		log.error(model);
		return "error_page";
	}
	
	/*WAS의 구동 중 가장 흔한 에러와 관련된 HTTP 상태 코드는 404와 500 에러코드이다.
	 * 500 메시지는 'Internal Server Error'이므로 @ExceptionHandler를 이용해서 처리되지만,
	 * 잘못된 URL을 호출할 때 보이는 404 에러 메시지의 경우는 다르게 처리하는 것이 좋다.
	 * 	에러페이지를 따로 만드는 이유?
	 * 		개발자는 WAS에서 제공해주는 에러페이지를 보면 편하겠지만 사용자가 보기에는 거북할 수 있다.
	 * */
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	/*상태 코드(Status code)는 핸들러 메서드가 호출될 때 HTTP 응답에 적용되고 
	 * ResponseEntity또는 "redirect:"와 같은 다른 수단으로 설정된 상태 정보를 재정의한다.
	 * HttpStatus.NOT_FOUND는 404에러를 나타낸다.
	 * */
	public String handle404(NoHandlerFoundException ex) {
		return "custom404";
	}
}

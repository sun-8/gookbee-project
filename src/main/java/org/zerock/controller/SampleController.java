package org.zerock.controller;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
/*Controller를 작성할 때 가장 편리한 기능은 파라미터가 자동으로 수집되는 기능이다.
 * 매번 request.getParameter()를 이용하는 불편함을 없앨 수 있다.
 * Controller가 파라미터를 수집하는 방식은 파라미터 타입에 따라 자동으로 변환하는 방식을 이용한다.
 * */
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;
import org.zerock.domain.TodoDTO;
import org.zerock.domain.TodoDTO2;

import lombok.extern.log4j.Log4j;


@Controller
@RequestMapping("/sample/*")
/*@RequestMapping은 현재 클래스의 모든 메서드들의 기본적인 URL경로가 된다.
 * SampleController 클래스를 "/sample/*"이라는 경로로 지정했다면 다음과 같은 URL은 모두 SampleController에서 처리된다.
 * 		/sample/aaa
 * 		/sample/bbb
 * 이 어노테이션은 클래스의 선언과 메서드 선언에 사용할 수 있다.
 * */

@Log4j
public class SampleController {

	@RequestMapping(value="/basic", method= {RequestMethod.GET,RequestMethod.POST})
	public void basicGet() {
		log.info("basic------------------");
		//404 error - 뷰페이지가 없어서 생기는 에러인 것 같다.
	}
	/*@RequestMapping은 GET,POST방식 모두를 지원해야 하는 경우에 배열로 처리해서 지정할 수 있다.
	 * 일반적인 경우에는 GET,POST방식만 사용하지만 PUT,DELETE방식 등도 사용할 때가 있다.
	 * */
	
//RequestMapping의 변화//
	
	@GetMapping("/basicOnlyGet")
	public void basicGet2() {
		log.info("basic get only get----------------------");
	}
	/*@GetMapping의 경우 오직 GET방식에서만 사용할 수 있으므로, 간편하긴 하지만 기능에 대한 제한이 많다.
	 * */
	
//Controller의 파라미터 수집//
	
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {
		/*SampleController의 메서드가 SampleDTO를 파라미터로 사용하게 되면 자동으로 setter메서드가 동작하면서 파라미터를 수집하게 된다.*/
		/*@GetMapping이 사용되었으므로, 필요한 파라미터를 URL 뒤에 '?name=sun&age=123'과 같은 형태로 추가해서 호출할 수 있다.
		 * */
		log.info(dto);//SampleDTO(name=sun, age=123)
		return "ex01";
	}
	
	@GetMapping("/ex02")
	public String ex02(@RequestParam("eeroom") String name, @RequestParam("nai") int age) {
		/*@RequestParam은 파라미터로 사용된 변수의 이름과 전달되는 파라미터의 이름이 다른 경우에 유용하게 사용된다.
		 * 		?eeroom=문자열입력&nai=나이입력
		 * */
		log.info("name: "+name);//name: suns
		log.info("age: "+age);//age: 111
		return "ex02";
	}
	
	@GetMapping("/ex02List")
	public String ex02List(@RequestParam("list") ArrayList<String> alist) {
		/*동일한 이름의 파라미터가 여러 개 전달되는 경우에는 ArrayList<> 등을 이용해서 처리가 가능하다.
		 * 스프링은 파라미터의 타입을 보고 객체를 생성하므로 파라미터의 타입은 List<>와 같은 인터페이스 타입이 아닌
		 * 실제적인 클래스 타입으로 지정한다.
		 * 		?list=one&list=two&list=three
		 * */
		log.info("alist: "+alist);//alist: [one, two, three]
		return "ex02List";
	}
	
	@GetMapping("/ex02Array")
	public String ex02Array(String[] arr) {
		/*배열의 경우에도 동일하게 처리가 가능하다.
		 * 		?arr=one&arr=three&arr=five
		 * */
		log.info("array arr: "+Arrays.toString(arr));//array arr: [one, three, five]
		return "ex02Array";
	}
	
	@GetMapping("/ex02Bean")
	public String ex02Bean(SampleDTOList list) {
		/*SampleDTOList타입을 파라미터로 사용하는 메서드를 작성
		 * 즉 전달하는 데이터가 객체이고 여러개를 처리해야 할 때
		 * 파라미터는 '[인덱스]'와 같은 형식으로 전달해서 처리할 수 있다.
		 * 		?list[0].name=aaa&list[2].name=bbb
		 * 	400에러 발생!!
		 * 		요청 타겟에서 유효하지 않은 문자가 발견되었습니다. -> [ ] 기호 때문이다.
		 * 		[ ] 대신 %5B,%5D로 변경한다.
		 * 		?list%5B0%5D.name=aaa&list%5B1%5D.name=bbb
		 * 호출하면 여러 개의 SampleDTO 객체를 생성하는 것을 볼 수 있다. []안의 인덱스 번호에 맞게 객체의 속성값이 출력되었다.
		 * */
		log.info("list dtos: "+list);
		//list dtos: SampleDTOList(list=[SampleDTO(name=null, age=0), SampleDTO(name=bbb, age=0)])
		return "ex02Bean";
	}
	
//@InitBinder//
	
	/*binding(바인딩)이란 파라미터의 수집을 다른 용어로 정의한 것이다.
	 * 즉 사용자가 입력한 값을 애플리케이션에서 사용하는 도메인으로 매핑하는 기능이다.
	 * 바인딩이 필요한 이유?
	 * 	첫번째, xml설정 파일을 통해 <bean>의 <property>에 값을 넣는 것이다.
	 * 			하지만 xml자체가 문자열이기 때문에 해당 빈클래스의 property값이 기본형 타입이나 String이면 괜찮지만 
	 * 			그 외의 타입이라면 바인딩 하기 전에 적절한 변환이 필요하다.
	 * 	두번째, Http를 통해 전달되는 헤더,쿠키,파라미터 같은 정보인데 이런 것들도 전부 문자열로 전달되는 값이다.
	 * 			이 경우에도 특정 타입으로 매칭시키려면 바인딩 과정 중에 적절한 변환이 필요하다.
	 * */
	@InitBinder
	/*변수의 타입이java.util.Date타입인데 2021-01-01과 같이 들어오는 데이터를 변환하고자 할 때 문제가 발생한다. 
	 * 	@InitBinder는 이러한 문제의 해결방법이다.
	 * 만약 @InitBinder 처리가 되지 않는다면 브라우저에서는 400에러가 발생한다.
	 * 		400에러는 요청 구문(syntax)이 잘못되었다는 뜻이다.*/
	public void initBinder(WebDataBinder binder) {
		/*WebDataBinder는 HTTP 요청정보를 컨트롤러 메소드의 파라미터나 모델에 바인딩할 때 사용되는 바인딩 오브젝트이다.
		 *요청 파라미터의 문자열을 컨트롤러 메서드의 매개변수에 지정된 변수 타입과 같은 형태로 변환하는 기능이 있다.
		 * */
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		/*SimpleDateFormat 클래스
		 * 날짜를 원하는 형식으로 반환한다. y는 년도 M은 월 d는 일*/
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat,false));
		/*void registerCostomEditor(클래스<?>requiredType(필수유형),문자열propertyPath(속성경로),propertyEditor(속성편집기)) 형식이다.
		 * 특정한 유형 및 속성 또는 특정한 유형의 모든 속성에 대해 특정한 사용자 지정 속성 편집기를 등록한다.?
		 * 		requiredType- 속성의 유형.
		 * 		propertyPath- 속성의 경로(이름 또는 중첩 경로) 또는 null지정된 유형의 모든 속성에 대해 편집기를 등록하는 경우.
		 * 		propertyEditor - 등록할 편집자.
		 *CustomDateEditor 클래스는 java.util.Date 사용자 정의를 지원하는 것에 대한 속성 편집기이다.
		 * 사용자 정의 컨트롤러 코드 내에서 날짜 편집기로 사용하기 위한것이며 사용자가 입력한 숫자 문자열을 Bean의 Date속성으로 분석하고 UI형식으로 구현한다.
		 * 웹 MVC 코드에서 이 편집기는 일반적으로 binder.registerCustomEditor와 사용한다.
		 * 		CustomDateEditor(DateFormat dateFormat, boolean allowEmpty)
		 * 구문 분석 및 구현을 위해 지정된 DateFormat을 사용하여 새 CustomDateEditor 인스턴스를 만든다.
		 * "allowEmpty" 매개변수는 구문 분석을 위해 빈 문자열을 허용해야 하는지 여부를 나타낸다.  null 값으로 해석됨.
		 * 		dateFormat - 구문 분석 및 구현에 사용할 DateFormat
		 * 		allowEmpty - 빈 문자열을 허용해야 하는 경우
		 * */
	}
		
	@GetMapping("/ex03")
		public String ex03(TodoDTO todo) {
		/*?title=test&dueDate=2021-01-01 로 호출하면 서버는 정상적으로 파라미터를 수집해서 처리한다.
		 * */
		log.info("todo: "+todo);
		//todo: TodoDTO(title=test, dueDate=Fri Jan 01 00:01:00 KST 2021)
		return "ex03";
	}	
	
//@DateTimeFormat//

	@GetMapping("/ex033")
	public String ex033(TodoDTO2 todo2) {
		/*@InitBinder 어노테이션이 없어야 정상실행된다.
		 * ?title=test&dueDate=2021/01/01*/
		log.info("todo2: "+todo2);//todo2: TodoDTO2(title=test, dueDate=Fri Jan 01 00:00:00 KST 2021)
		return "ex033";
	}
	
/*Model - 데이터 전달자
 * Controller의 메서드를 작성할 때 Model이라는 타입을 파라미터로 지정할 수 있는데,
 * Model 객체는 JSP에 Controller에서 생성된 데이터를 담아서 전달해야 하는 역할을 한다.
 * 그래서 데이터를 JSP와 같은 View로 전달할 때 Model을 사용한다.
 * Controller 메서드의 파라미터에 Model 타입이 지정된 경우에 스프링은 특별히 Model타입의 객체를 만들어서 메서드에 주입한다.
 * Model은 request.setAttribute()와 유사한 역할을 한다.
 * 메서드의 파라미터를 Model 타입으로 선언하게 되면 자동으로 스프링 MVC에서 Model타입의 객체를 만들어 주기 때문에 
 * 	필요한 데이터를 담아주는 작업만으로 모든 작업이 완료된다.
 * Model을 사용해야 하는 경우는 주로 Controller에 전달된 데이터를 이용해서 추가적인 데이터를 가져와야 하는 상황이다.
 * 	- 리스트 페이지 번호를 파라미터로 전달받고, 실제 데이터를 View로 전달해야 하는 경우
 * 	- 파라미터들에 대한 처리 후 결과를 전달해야 하는 경우
 * 
 * 웹 페이지의 구조는 Request에 전달된 데이터를 가지고 필요하다면 추가적인 데이터를 생성해서 화면으로 전달하는 방식으로 동작한다.
 * Model의 경우 파라미터로 전달된 데이터는 존재하지 않지만 화면에서 필요한 데이터를 전달하기 위해 사용한다.
 * 	ex) 페이지 번호는 파라미터로 전달되지만(int page), 결과 데이터를 전달하려면 Model에 담아서 전달한다.(Model m , m.Attribute("page",page);
 * 
 * 스프링 MVC의 Controller는 기본적으로 Java Beans 규칙에 맞는 객체는 다시 화면으로 객체를 전달한다.
 * 	Java Beans의 규칙 - 생성자가 없거나 빈 생성자를 가져야 하며, getter/setter를 가진 클래스의 객체들을 의미
 * 전달될 대는 클래스명의 앞글자는 소문자로 처리된다.
 * 반면에 기본 자료형의 경우는 파라미터로 선언하더라도 기본적으로 화면까지 전달되지는 않는다.
 * */
	
	@GetMapping("/ex04")
	public String ex04(SampleDTO dto,int page) {
		/* SampleDTO 객체는 getter/setter를 가졌기 때문에 Java Beans의 규칙에 맞는 객체이다.
		 * 하지만 page 매개변수는 Java Beans의 규칙에 맞지 않기 때문에 화면까지 전달되지 않는다.
		 * 		?name=sun&age=111&page=9
		 * */
		log.info("dto: "+dto);//dto: SampleDTO(name=sun, age=111)
		log.info("page: "+page);//page: 9
		return "/sample/ex04";
	}
	
//@ModelAttribute//
	
	@GetMapping("/ex044")
	public String ex044(SampleDTO dto,@ModelAttribute("page") int page) {
		/*@ModelAttribute는 전달받은 파라미터를 강제로 Model에 담아서 화면에 전달하도록 할 때 필요한 어노테이션이다.
		 * 이 어노테이션을 적용한 파라미터는 타입에 관계없이 무조건 Model에 담아서 전달되므로, 파라미터로 전달된 데이터를 다시 화면에서 사용해야 할 때 유용하다.
		 * 기본 자료형에 @ModelAttribute를 적용할 경우에는 반드시 @ModelAttribute("page")와 같이 value값을 지정해야 한다.*/
		log.info("dto: "+dto);//dto: SampleDTO(name=sun, age=111)
		log.info("page: "+page);//page: 9
		return "/sample/ex04";
	}
	
/*RedirectAttributes - Model 타입과 비슷하게 스프링 MVC가 자동으로 전달해주는 타입
 * 일회성으로 데이터를 전달하는 용도로 사용한다.
 * response.sendRedirect()와 동일한 용도로 사용한다.
 * Model과 같이 파라미터로 선언해서 사용하고, 
 * 	Redirect Attributes rttr, rttr.addFlashAttribute("이름",값) 메서드를 이용해서
 * 	화면에 한 번만 사용하고 다음에는 사용되지 않는 데이터를 전달하기 위해서 사용한다.
 * */
	
/*Controller의 리턴 타입
 * - void : 호출하는 URL과 동일한 이름의 jsp를 의미한다.
 * - String : jsp를 이용하는 경우 jsp파일의 경로와 파일이름을 나타내기 위해서 사용한다.
 * 				상황에 따라 다른 화면을 보여줄 필요가 있을 경우 유용하게 사용한다.(if~else와 같은 처리가 필요한 상황)
 * - VO,DTO 타입 : 주로 JSON 타입의 데이터를 만들어서 반환하는 용도로 사용한다.
 * - ResponseEntity 타입 : response 할 때 Http 헤더 정보와 내용을 가공하는 용도로 사용한다.
 * - Model,ModelAndView : Model로 데이터를 반환하거나 화면까지 같이 지정하는 경우에 사용한다.
 * 		(최근에는 많이 사용하고 있지 않다.)
 * - HttpHeaders : 응답에 내용 없이 Http 헤더 메시지만 전달하는 용도로 사용한다.
 * */
	
	@GetMapping("/ex05")
	public void ex05() {
		/*리턴 타입을 void로 지정.
		 * 해당 URL의 경로를 그대로 jsp파일의 이름으로 사용하게 된다.
		 * 	/sample/ex05
		 * View에 위의 경로로 저장된 jsp가 없으면  '[/WEB-INF/views/sample/ex05.jsp]을(를) 찾을 수 없습니다.' 라는 404에러를 발생시킨다.
		 * 이것은 servlet-context.xml의 InternalResourceViewResolver 설정과 같이 맞물려 URL경로를 View로 처리하기 때문에 생기는 결과이다.*/
		log.info("/ex05.............");
	}
	
	@GetMapping("/ex06")
	public @ResponseBody SampleDTO ex06() {
		/*리턴 타입을 객체 타입으로 지정
		 * VO(Value Object)나 DTO(Data Transfer Object)타입 등 복합적인 데이터가 들어간 객체 타입으로 지정할 수 있다.
		 * 주로 JSON 데이터를 만들어 내는 용도로 사용하는데 이를 위해서 pom.xml에 jackson-databind 라이브러리를 추가해야 한다.
		 * 자동으로 브라우저에 JSON 타입으로 객체를 변환해서 전달하고
		 * 개발자 도구를 통해서 살펴보면 서버에서 전송하는 MINE타입이 'application/json'으로 처리되는 것을 볼 수 있다.
		 * 만약 jackson-databind 라이브러리가 포함되지 않으면 'No converter found for return value of type: 클래스경로'
		 * 라는 500에러 페이지가 뜬다.
		 * 
		 * @ResponseBody 어노테이션은 jsp파일을 만들지 않고도 브라우저에 키,값 쌍의 JSON 데이터를 쉽게 만들 수 있다.
		 * JSON의 키는 리턴 타입의 변수명이 된다.
		 * */
		log.info("/ex06.............");
		SampleDTO dto=new SampleDTO();
		dto.setName("sun");
		dto.setAge(111);
		return dto;
		//{"name":"sun","age":111}
	}
	
	@GetMapping("/ex07")
	public ResponseEntity<String> ex07(){
		/*리턴타입을 ResponseEntity 타입으로 지정
		 * 스프링 MVC는 HttpServletRequest나 HttpServletResponse를 직접 핸들링 하지 않아도 이런 작업이 가능하도록 작성되어 있기 때문에
		 * 이러한 처리를 위해 ResponseEntity를 사용한다.
		 * HttpHeader 객체를 같이 전달할 수 있고, 원하는 HTTP 헤더 메시지를 가공하는 것이 가능하다.
		 * 이 메서드의 경우 브라우저에는 JSON타입이라는 헤더 메시지와 200 OK라는 상태 코드를 전송한다.
		 * 		개발자 도구 -> Network -> ctrl+r -> ex07 눌러서 확인
		 * */
		log.info("/ex07.............");
		String msg="{\"name\": \"서니\"}";//{name: 서니}
		HttpHeaders header=new HttpHeaders();
		//새 헤더 밑 해당 값을 HttpHeaders 컬렉션에 삽입한다.
		header.add("Content-Type", "application/json;charset=utf-8");
		/*지정된 헤더 및 헤더 값을 HttpHeades 컬렉션에 추가한다.
		 * void add(String name, String value)
		 * 	name - 컬렉션에 추가할 헤더.
		 * 	value - 헤더의 내용.
		 * 지정된 헤더가 없으면 add메서드는 이름/값 쌍 목록에 새 헤더를 삽입한다.
		 * 지정된 헤더가 있다면 value는 헤더와 연결된 값의 쉼표로 구분된 목록에 추가된다.
		 * */
		return new ResponseEntity<>(msg,header,HttpStatus.OK);
	}
}
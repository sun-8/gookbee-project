package org.zerock.controller;
import java.util.ArrayList;
import java.util.Arrays;

/*Controller를 작성할 때 가장 편리한 기능은 파라미터가 자동으로 수집되는 기능이다.
 * 매번 request.getParameter()를 이용하는 불편함을 없앨 수 있다.
 * Controller가 파라미터를 수집하는 방식은 파라미터 타입에 따라 자동으로 변환하는 방식을 이용한다.
 * */
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;

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
}
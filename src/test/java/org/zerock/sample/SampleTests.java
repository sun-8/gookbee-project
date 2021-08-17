package org.zerock.sample;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
//테스트 코드는 항상 RunWith(SpringJUnit4ClassRunner.class)를 작성해주어야 한다.
//현재 테스트 코드가 스프링을 실행하는 역할을 할 것이라는 의미이다.

@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
//@ContextConfiguration은 지정된 클래스나 문자열을 이용해서 필요한 객체들을 스프링 내에 객체로 등록하게 한다.(스프링 빈으로 등록된다고도 한다.) (?)
//@ContextConfiguration에 사용하는 문자열을 'classpath:'나 'file:'을 이용할 수 있으므로, root-context.xml의 경로를 지정할 수 있다.

@Log4j
//@Log4j 어노테이션은 로그 객체를 생성하게 된다. (?)
//@Log4j는 lombok을 이용해서 로그를 기록하는 Logger를 변수로 생성한다.
//Logger 객체의 선언이 없어도 Log4j 라이브러리와 설정이 존재한다면 바로 사용할 수 있다.
//'Spring Legacy Project'로 생성하는 경우 기본으로 Log4j와 해당 설정이 완료되는 상태이기 때문에 별도의 처리 없이도 사용이 가능하다.

public class SampleTests {
	//SampleTests 클래스는 spring-test 모듈을 이용해서 간단하게 스프링을 가동시키고, 동작하게 한다.
	
	@Setter(onMethod_ = {@Autowired})
	//@Setter는 자동으로 setRestaurant()를 컴파일 시 생성한다.
	//@Setter에서 사용된 onMethod속성은 생성되는 setRestaurant()에 @Autowired어노테이션을 추가하도록 한다. (?)
	private Restaurant restaurant;
	
	@Test
	//Junit에서 테스트 대상을 표시하는 어노테이션이다.
	
	public void testExist() {
		assertNotNull(restaurant);//restaurant 변수가 null이 아니어야만 테스트가 성공한다는 것을 의미한다 (?)
		
		//@Log4j를 작성해야 사용이 가능하다.
		log.info(restaurant);
		log.info("-------------------");
		log.info(restaurant.getChef());
	}

}
//결과에서 주목해야 할 것
/* 1. new Restaurant()와 같이 Restaurant 클래스에서 객체를 생성한 적이 없는데도 객체가 만들어졌다.
 * 		스프링은 관리가 필요한 객체(Bean)를 어노테이션 등을 이용해서 객체를 생성하고 관리하는 일종의 '컨테이너'나 '팩토리'의 기능을 가지고 있다.
 * 2. Restaurant 클래스의 @Data 어노테이션으로 Lombok을 이용해서 여러 메서드가 만들어졌다.
 * 		스프링은 생성자 주입 혹은 setter주입을 이용해서 동작한다.
 * 		Lombok을 통해서 getter/setter 등을 자동으로 생성하고 'onMethod'속성을 이용해서 작성된 setter에 @Autowired 어노테이션을 추가한다.
 * 3. Restaurant 객체의 Chef 인스턴스 변수(멤버변수)에 Chef 타입의 객체가 주입되어 있다.
 * 		스프링은 @Autowired와 같은 어노테이션을 이용해서 개발자가 직접 객체들과의 관계를 관리하지 않고, 자동으로 관리하도록 한다.
 * */

//핵심
/* [1] 테스트 코드가 실행되기 위해서 스프링 프레임워크가 동작했다.
 * [2] 동작하는 과정에서 필요한 객체들이 스프링에 등록되었다.
 * [3] 의존성 주입이 필요한 객체는 자동으로 주입이 이루어졌다.
 * */

//테스트 작업은 프로젝트 초기에 설정해 두고 사용하는 습관을 가지는 것이 좋다고 한다.
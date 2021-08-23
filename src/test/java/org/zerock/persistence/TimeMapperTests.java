package org.zerock.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.mapper.TimeMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class TimeMapperTests {

	@Setter(onMethod_ = {@Autowired})
	private TimeMapper timeMapper;
	
	@Test
	public void testGetTime2() {
		log.info("getTime2");
		log.info(timeMapper.getTime2());
	}
	
	@Test
	public void testGetTime() {
		log.info(timeMapper.getClass().getName());
		//timeMapper.getClass().getName()은 실제 동작하는 클래스의 이름을 확인해 준다.
		//실행 결과를 보면 개발 시 인터페이스만 만들어 주었는데 내부적으로 적당한 클래스가 만들어진 것을 확일할 수 있다.
		//com.sun.proxy.$Proxy22
		log.info(timeMapper.getTime());
	}
}
/* TimeMapperTests 클래스는 TimeMapper가 정상적으로 사용이 가능한지를 알아보기 위한 테스트 코드이다.
 * 이 코드가 정상적으로 동작한다면 스프링 내부에는 TimeMapper 타입으로 만들어진 스프링 객체(빈)가 존재한다는 뜻이다.
 * 여기서는 스프링이 인터페이스를 이용해서 객체를 생성한다는 사실에 주목한다.
 * 이 코드는 어노테이션 방식으로 MyBatis를 이용해서 SQL을 처리할 때 어노테이션을 이용하는 방식이 압도적으로 편리하다.
 * 하지만 SQL이 복잡하거나 길어지는 경우에는 어노테이션 보다는 XML을 이용하는 방식을 더 선호한다.
 * */
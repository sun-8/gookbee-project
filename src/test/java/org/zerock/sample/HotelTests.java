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
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class HotelTests {

	@Setter(onMethod_ = { @Autowired })
	private SampleHotel hotel;
	//@Setter는 자동으로 setSampleHotel()를 컴파일 시 생성한다.
	//@Setter에서 사용된 onMethod속성은 생성되는 setSampleHotel()에 @Autowired어노테이션을 추가하도록 한다.
	
	@Test
	public void testExist() {
		assertNotNull(hotel);//hotel 변수가 null이 아니어야만 테스트가 성공한다는 것을 의미한다.
		
		//@Log4j를 작성해야 사용이 가능하다.
		log.info(hotel);
		log.info("---------------------------");
		log.info(hotel.getChef());
	}
}

package org.zerock.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Setter;

@Component //스프링에게 해당 클래스가 스프링에서 관리해야하는 대상임을 표시하는 어노테이션
@Data //lombok 라이브러리
public class Restaurant {

	@Setter(onMethod_ = @Autowired)
	//@Setter는 자동으로 setChef()를 컴파일 시 생성한다. @Setter에서 사용된 onMethod속성은 생성되는 setChef()에 @Autowired어노테이션을 추가하도록 한다. (?)
	//@Autowired는 해당 인스턴스 변수(chef)가 스프링으로부터 자동으로 주입해 달라는 표시이다.
	//Spring의 의존성 주입은 크게 생성자 주입과 Setter주입을 사용한다.
	
	private Chef chef;
}
//이 코드가 의미하는 것은 Restaruant 객체는 Chef타입의 객체를 필요로 한다는 것이다.

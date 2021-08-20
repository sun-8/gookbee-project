package org.zerock.sample;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
//@Component는 스프링에게 해당 클래스가 스프링에서 관리해야 하는 대상임을 표시하는 어노테이션이다.

@Data 
//Lombok라이브러리에서 자주 사용되는 어노테이션이다.
//@Data는 @ToString, @EqualsAndHashCode,	@Getter/Setter, @RequiredArgsConstructor를 모두 결합한 형태로 한 번에 자주 사용되는 모든 메서드들을 생성할 수 있다.	
//@ToString (?)
//@EqualsAndHashCode (?)

/*@RequiredArgsConstructor
 * 여러 개의 인스턴스 변수들 중에서 특정한 변수에 대해서만 생성자를 작성하고 싶다면 이 어노테이션을 추가한다.
 * @NonNull이나 final이 붙은 인스턴스 변수에 대한 생성자를 만들어낸다.
 * */


public class Chef {
	//Restaurant 클래스에 주입할 Chef클래스
	//일반적으로 Spring에서 의존성 주입은 Chef를 클래스가 아닌 인터페이스로 설계하는 것이 좋다. (?)
	//즉 주입할 객체를 인터페이스로 설계하는 것이 좋다는 뜻이다.(레스토랑 <- 요리사 / 큰 틀 <- 작은 요소)
}

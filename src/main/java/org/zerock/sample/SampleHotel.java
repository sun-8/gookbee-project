package org.zerock.sample;

import org.springframework.stereotype.Component;

//import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Component
@ToString // (?)
@Getter
//@AllArgsConstructor
////오버로딩 된 생성자를 작성하지 않고 어노테이션을 추가해주는 방법이다.
////인스턴스 변수로 선언된 모든 것을 파라미터로 받는 생성자를 작성하게 된다.(오버로딩된 생성자)
////컴파일된 결과를 보면 생성자로 Chef를 받도록 만들어져있다.

@RequiredArgsConstructor
//여러 개의 인스턴스 변수들 중에서 특정한 변수에 대해서만 생성자를 작성하고 싶다면 이 어노테이션을 추가한다.
//@NonNull이나 final이 붙은 인스턴스 변수에 대한 생성자를 만들어낸다.

public class SampleHotel {
	
	@NonNull
	private Chef chef;
	
//	public SampleHotel(Chef chef) {
//		this.chef=chef;
//	}//오버로딩 된 생성자
}

//기존의 코드와 달리 생성자를 선언하고 Chef를 주입하도록 작성했다.
//기존과 다른점은 @Autowired 어노테이션이 없이 처리되고 있다는 점이다.

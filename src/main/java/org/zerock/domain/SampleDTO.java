package org.zerock.domain;

import lombok.Data;

@Data
//getter/setter, equals(), toString() 등의 메서드를 자동생성
public class SampleDTO {
	/*DTO의 뜻
	 * Data Transfer Object는 데이터 전송 객체라는 의미이다.
	 * 같은 시스템에서 사용되는 것이 아닌 다른 시스템(외부 시스템)으로 전달하는 작업을 처리하는 객체이다.
	 * 로직을 갖고있지 않은 순수한 데이터 객체이며, getter/setter 메서드만을 갖는다.
	 * 
	 *VO의 뜻
	 * Value Object는 값 객체라는 의미이다.
	 * 데이터 그 자체로 의미 있는 것을 담고 있는 객체이다.
	 * 관계데이터베이스의 레코드에 대응되는 자바클래스이다.
	 * VO의 핵심역할은 equals(),hashcode()를 오버라이딩 하는 것이다.
	 * getter/setter를 가질 수 있으며 테이블 내에 있는 속성(필드) 외에 추가적인 속성을 가질 수 있다.
	 * 
	 * DTO : 데이터의 전송을 위한 객체이다.
	 * VO : 사용되는 값이 객체로 표현되며, 값 변경이 없는 경우
	 * */

	private String name;
	private int age;
}

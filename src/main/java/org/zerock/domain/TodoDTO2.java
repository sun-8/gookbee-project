package org.zerock.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class TodoDTO2 {

	private String title;
	
	@DateTimeFormat(pattern="yyyy/MM/dd")
	private Date dueDate;
	/*@InitBinder를 이용해서 날짜를 변환 할 수도 있지만, 파라미터로 사용되는 인스턴스 변수에 @DateTimeFormat을 적용해도 변환이 가능하다.
	 * @DateTimeFormat을 이용하는 경우 @InitBinder는 필요하지 않다.*/
	
}
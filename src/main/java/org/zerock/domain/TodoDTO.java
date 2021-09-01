package org.zerock.domain;

import java.util.Date;

import lombok.Data;

@Data
public class TodoDTO {

	private String title;
	private Date dueDate;
	/*dueDate의 타입이 java.util.Date타입
	/* 만일 사용자가 2021-01-01과 같이 들어오는 데이터를 변환하고자 할 때 문제가 발생한다.*/
}
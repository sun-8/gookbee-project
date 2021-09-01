package org.zerock.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class SampleDTOList {//SampleDTO의 리스트를 포함하는 SampleDTOList 클래스 생성

	private List<SampleDTO> list;
	
	public SampleDTOList() {
		list=new ArrayList<>();
	}
}

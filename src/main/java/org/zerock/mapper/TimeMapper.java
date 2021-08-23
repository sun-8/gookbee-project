package org.zerock.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {

	@Select("SELECT sysdate FROM dual")
	public String getTime();
	//MyBatis의 어노테이션을 이용해서 SQL을 메서드에 추가한다.
	
	public String getTime2();
	//xml을 이용해서 처리
	//Mybatis의 어노테이션이 존재하지 않고 SQL역시 존재하지 않는다.
}

package org.zerock.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
//테스트 코드는 항상 RunWith(SpringJUnit4ClassRunner.class)를 작성해주어야 한다.
//현재 테스트 코드가 스프링을 실행하는 역할을 할 것이라는 의미이다.

@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
//@ContextConfiguration은 지정된 클래스나 문자열을 이용해서 필요한 객체들을 스프링 내에 객체로 등록하게 한다.(스프링 빈으로 등록된다고도 한다.) (?)
//@ContextConfiguration에 사용하는 문자열을 'classpath:'나 'file:'을 이용할 수 있으므로, root-context.xml의 경로를 지정할 수 있다.

@Log4j
//@Log4j 어노테이션은 로그 객체를 생성하게 된다. (?)
//@Log4j는 lombok을 이용해서 로그를 기록하는 Logger를 변수로 생성한다.
//Logger 객체의 선언이 없어도 Log4j 라이브러리와 설정이 존재한다면 바로 사용할 수 있다.
//'Spring Legacy Project'로 생성하는 경우 기본으로 Log4j와 해당 설정이 완료되는 상태이기 때문에 별도의 처리 없이도 사용이 가능하다.
public class DataSourceTests {
	
	@Setter(onMethod_ = {@Autowired})
	//@Setter는 자동으로 setDataSource를 컴파일 시 생성한다.
	//@Setter에서 사용된 onMethod속성은 생성되는 dataSource에 @Autowired어노테이션을 추가하도록 한다. (?)
	private DataSource dataSource;
	
	@Setter(onMethod_ = {@Autowired})
	//@Setter는 자동으로 setSqlSessionFactory를 컴파일 시 생성한다.
	//@Setter에서 사용된 onMethod속성은 생성되는 sqlSessionFactory에 @Autowired어노테이션을 추가하도록 한다. (?)
	private SqlSessionFactory sqlSessionFactory;
	
	@Test
	public void testMyBatis() {
		try(SqlSession session=sqlSessionFactory.openSession(); Connection con=session.getConnection();){
			log.info(session);
			log.info(con);
		}catch(Exception e) {
			fail(e.getMessage());
		}
	}
	/*testMyBatis()는 설정된 SqlSessionFactory 인터페이스 타입의 SqlSessionFactoryBean을 이용해서 생성하고, 이를 이용해서 Connection까지 테스트한다.*/
	
	@Test
	public void testConnection() {
		try(Connection con = dataSource.getConnection()){
			log.info(con);
		}catch(Exception e) {
			fail(e.getMessage());
		}
	}
}

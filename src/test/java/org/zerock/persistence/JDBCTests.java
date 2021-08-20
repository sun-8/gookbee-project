package org.zerock.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class JDBCTests {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testConnection() {
		String driver = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String user = "night";
		String pwd = "night";
		
		try(Connection con = DriverManager.getConnection(driver,user,pwd)){
			log.info(con);
		}catch(Exception e) {
			fail(e.getMessage());
		}
	}
}

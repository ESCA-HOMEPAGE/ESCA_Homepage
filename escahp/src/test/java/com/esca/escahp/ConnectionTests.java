package com.esca.escahp;

import java.sql.Connection;
import org.apache.ibatis.session.SqlSessionFactory;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class ConnectionTests {
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Test
	public void testConnection() {
		try(Connection con = sqlSessionFactory.openSession().getConnection()){
			System.out.println("Connection Success!");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}

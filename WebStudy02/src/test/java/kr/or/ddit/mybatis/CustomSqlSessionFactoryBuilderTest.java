package kr.or.ddit.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;

class CustomSqlSessionFactoryBuilderTest {

	private SqlSessionFactory factory;

	@Test
	void testGetSqlSessionFactory() {
		factory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
		System.out.println(factory);
		try(
			SqlSession session = factory.openSession();
		){
			System.out.println(session);
		}
	}
}

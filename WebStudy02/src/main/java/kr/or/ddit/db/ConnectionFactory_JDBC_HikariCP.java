package kr.or.ddit.db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


/**
 *	Factory Object[Method] Pattern
 *	: 객체의 생성을 전담하는 객체를 별도 운영하는 구조.
 *	 
 *	
 */
public class ConnectionFactory_JDBC_HikariCP {
	private static String url;
	private static String user;
	private static String password;
	private static String driverClassName;
	
	private static DataSource dataSource;
	
	static{
		try (
			InputStream is = ConnectionFactory_JDBC_HikariCP.class.getResourceAsStream("./DbInfo.properties");
		){
			Properties props = new Properties();
			props.load(is);
			
			//HikariConfig 객체 생성
			HikariConfig config = new HikariConfig();
			
			Class.forName(props.getProperty("driverClassName"));
			driverClassName = props.getProperty("driverClassName");
			url = props.getProperty("url");
			user = props.getProperty("user");
			password = props.getProperty("password");
			
			config.setDriverClassName(driverClassName);
			config.setJdbcUrl(url);
			config.setUsername(user);
			config.setPassword(password);
			
			
			//풀링정책
			//쓰지 않아도 자동으로 되지만 mybatis와 차이점을 보기위해
			config.setAutoCommit(true);
			
			
			config.setMaximumPoolSize(5);
			
			//최초의 만드는 connection갯수 지정
			config.setMinimumIdle(3);
			
			//나눠줄 수 있는 connection이 없을 때 2초간 기다리고 그후 exception 발생
			config.setConnectionTimeout(2000);
			
			//정상적으로 connection이 잘 연결되었는지 확인하는 코드
			config.setConnectionTestQuery("SELECT SYSDATE FROM DUAL");
			
			
			dataSource = new HikariDataSource(config); 
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		} //constant pool에 로딩됨.
	}
	public static Connection getConnection() throws SQLException {
		
		Connection conn = dataSource.getConnection();
		return conn;
	}
}





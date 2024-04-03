package kr.or.ddit.db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;
import javax.sql.PooledConnection;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;

/**
 *	Factory Object[Method] Pattern
 *	: 객체의 생성을 전담하는 객체를 별도 운영하는 구조.
 *	 
 *	
 */
public class ConnectionFactory_JDBC_Ver3 {
	private static String url;
	private static String user;
	private static String password;
	
	private static DataSource dataSource;
	private static PooledConnection pc;
	
	static{
		try (
			InputStream is = ConnectionFactory_JDBC_Ver3.class.getResourceAsStream("./DbInfo.properties");
		){
			Properties props = new Properties();
			props.load(is);
			
			//오라클 db에서만 사용할 수 있음(결합력이 높아짐)
			OracleConnectionPoolDataSource ocpds = new OracleConnectionPoolDataSource();
			dataSource = ocpds;
			
			Class.forName(props.getProperty("driverClassName"));
			url = props.getProperty("url");
			user = props.getProperty("user");
			password = props.getProperty("password");
			
			ocpds.setURL(url);
			ocpds.setUser(user);
			ocpds.setPassword(password);
			
			//기본으로 10개의 커넥션을 만들고 돌려막기로 써먹음
			//한개만 100번 사용됫을것. 100번을 돌리든 10000번을 돌리든 하나만 사용하기때문에 부하가 걸리지않음.
			pc = ocpds.getPooledConnection();
			pc.getConnection();
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		} //constant pool에 로딩됨.
	}
	public static Connection getConnection() throws SQLException {
		
		Connection conn = pc.getConnection();
		return conn;
	}
}

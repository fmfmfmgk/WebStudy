package kr.or.ddit.member.dao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.MemberVO;

public class MemberDAOImpl implements MemberDAO {
	
	private SqlSessionFactory factory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	private MemberDAO generateProxy(SqlSession sqlsession)	{
		return (MemberDAO)Proxy.newProxyInstance(MemberDAO.class.getClassLoader(), new Class[] {MemberDAO.class}, new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				// session.selectList("kr.or.ddit.member.dao.MemberDAO.selectMemberList");
				String namespace = method.getDeclaringClass().getName();
				String id = method.getName();
				String statement = namespace+"."+id;
				Object argument = null;
				
				if( args!=null && args.length >0) {
					argument = args[0];
				}
						
				if(method.getReturnType().equals(List.class)) {
					return sqlsession.selectList(statement, argument);
					
				}else {
					return sqlsession.selectOne(statement, argument);
				}
			}
		});
	}
	
	
   @Override
   public int insertMember(MemberVO member) {
		try (
			SqlSession session = factory.openSession();
		){
//			int rowcnt = session.insert("kr.or.ddit.member.dao.MemberDAO.insertMember", member);
			int rowcnt = session.getMapper(MemberDAO.class).insertMember(member);
			
			if(rowcnt >=0) {
				session.commit();
			}
			return rowcnt;
		}
   }

   @Override
   public List<MemberVO> selectMemberList() {
	   try(
			SqlSession session = factory.openSession();
		){
//		   return session.selectList("kr.or.ddit.member.dao.MemberDAO.selectMemberList");
//		   return generateProxy(session).selectMemberList();
		   MemberDAO mapperProxy = session.getMapper(MemberDAO.class);
		   return mapperProxy.selectMemberList();
	   }
   }

   @Override
   public MemberVO selectMember(String memId) {
	   try(		
			SqlSession session = factory.openSession(); 
		){
//		   return session.selectOne("kr.or.ddit.member.dao.MemberDAO.selectMember", memId);
//		   return generateProxy(session).selectMember(memId);
		   return session.getMapper(MemberDAO.class).selectMember(memId);
	   }
   }

   @Override
   public int updateMember(MemberVO member) {
	   try(
			SqlSession session = factory.openSession(); 
		){
//		   	int rowcnt = session.update("kr.or.ddit.member.dao.MemberDAO.updateMember", member);
		   	int rowcnt = session.getMapper(MemberDAO.class).updateMember(member);
		   	
			if(rowcnt > 0) {
				session.commit();
			}
			return rowcnt;
	   }
   }
   
   @Override
   public int deleteMember(String memId) {
	   try(
			SqlSession session = factory.openSession(); 
		){
//		   	int rowcnt = session.delete("kr.or.ddit.member.dao.MemberDAO.deleteMember", memId);
		   	int rowcnt = session.getMapper(MemberDAO.class).deleteMember(memId);
		   	
			if(rowcnt > 0) {
				session.commit();
			}
			return rowcnt;
	   }
   }


	@Override
	public MemberVO selectMemberForAuth(String memId) {
		try(
			SqlSession sqlSession = factory.openSession();
		){
			return sqlSession.getMapper(MemberDAO.class).selectMemberForAuth(memId);
		}
	}
}

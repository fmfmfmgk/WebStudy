package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.ProdVO;


public class ProdDAOImpl implements ProdDAO{
	private SqlSessionFactory factory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public int insertProd(ProdVO prod) {
		//1.session객체 만들기
		try (
			SqlSession session = factory.openSession();
		){
			int cnt = session.getMapper(ProdDAO.class).insertProd(prod);
			if(cnt >0) {
				session.commit();
			}
			return cnt;
		}
	}

	@Override
	public List<ProdVO> selectProdList() {
		//1.session객체 만들기
		try (
			SqlSession session = factory.openSession();
		){
//			2. 프록시 이용전
//			List<ProdVO> list = session.selectList("kr.or.ddit.prod.dao.ProdDAO.selectProdList");
			
//			2. 프록시 이용후
			List<ProdVO> list = session.getMapper(ProdDAO.class).selectProdList();
			return list;
		}
	}
	
	@Override
	public ProdVO selectProd(String prodId) {
		try (
			SqlSession session = factory.openSession();
		){
			ProdVO prod = session.getMapper(ProdDAO.class).selectProd(prodId);
			return prod;
		}
	}

	@Override
	public int updateProd(ProdVO prod) {
		// TODO Auto-generated method stub
		return 0;
	}
}

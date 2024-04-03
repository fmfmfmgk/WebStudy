package kr.or.ddit.prod.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import kr.or.ddit.vo.ProdVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class ProdDAOTest {
	private ProdDAO dao = new ProdDAOImpl();
	@Test
	void testInsertProd() {
		ProdVO prod =dao.selectProd("P101000001");
		prod.setProdId(null);
		int cnt = dao.insertProd(prod);
		assertEquals(1, cnt);
	}

	@Test
	void testSelectProdList() {
		List<ProdVO> prodList = dao.selectProdList();
		log.info("list : {}", prodList);
	}

	@Test
	void testSelectProd() {
		String prodId = "P101000001";
		ProdVO prod = dao.selectProd(prodId);
		log.info("vo : {}", prod);
	}

	@Test
	void testUpdateProd() {
		fail("Not yet implemented");
	}

}

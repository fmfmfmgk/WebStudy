package kr.or.ddit.prod.dao;

import java.util.List;

import kr.or.ddit.vo.ProdVO;

/**
 *	상품관리(CRUD)
 *	 
 *
 */
public interface ProdDAO {
	/**
	 * 상품 등록
	 * @param prod
	 * @return
	 */
	public int insertProd(ProdVO prod);
	/**
	 * 상품 다건 조회(상품코드, 거래처코드, 분류코드, 상품명, 구매가, 판매가, 마일리지, 입고일)
	 * @return
	 */
	public List<ProdVO> selectProdList();
	/**
	 * 상품 단건 조회 
	 * 해당조건이 없으면 null이 반환될 수 있음
	 * @return
	 */
	public ProdVO selectProd(String prodId);
	/**
	 * 상품 수정
	 * @param prod
	 * @return
	 */
	public int updateProd(ProdVO prod);

}

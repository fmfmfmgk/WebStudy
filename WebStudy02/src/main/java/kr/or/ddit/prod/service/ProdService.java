package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.PkNotFoundException;
import kr.or.ddit.vo.ProdVO;

public interface ProdService {
	/**
	 * 상품 추가
	 * @param prod
	 * @return OK, FAIL
	 */
	public ServiceResult createProd(ProdVO prod);
	/**
	 * 
	 * @return
	 */
	public List<ProdVO> retrieveProdList();
	/**
	 * 
	 * @param prodId
	 * @return
	 * @throws PkNotFoundException 해당상품이 존재하지 않으면 500에러를 표시
	 */
	public ProdVO retrieveProd(String prodId)throws PkNotFoundException;
	/**
	 * 
	 * @param prod
	 * @return OK, FAIL
	 */
	public ServiceResult modifyProd(ProdVO prod);
}

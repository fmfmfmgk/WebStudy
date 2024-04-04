package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DataMapper[ORM]를 사용해서 multi entity를 조회하는 단계
 * 1. 사용할 entity 결정, LPROD(1), BUYER(N)
 * 2. ENTITY 하나당 하나의 vo 모델링
 * 3. ENTITY 간의 관계를 파악, MAIN ENTITY를 1로 두고 파악.
 * 		1:1 1:N N:N 관계
 * 4. VO간의 관계를 ENTITY간의 관계를 반영하여 모델링.
 * 		1:1(Has a) 1:N(Has Many)
 * 5. join 쿼리 작성
 * 6. resultType 대신 resultMap으로 조회 결과를 바인딩.
 * 		1:1 - association으로 바인드
 * 		1:N - collection으로 바인드
 * @author PC-20
 *
 */
@Data
@EqualsAndHashCode(of = "lprodGu")
public class LprodVO implements Serializable{
	private String lprodId;
	private String lprodGu;
	private String lprodNm;
	
	private List<BuyerVO> buyerList;
}

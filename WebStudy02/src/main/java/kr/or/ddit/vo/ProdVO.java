package kr.or.ddit.vo;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString.Exclude;

@Data
@EqualsAndHashCode(of="prodId")
//@ToString(exclude = "prodDetail")
public class ProdVO {
	private String    prodId;
	private String    prodName;
	private String    prodLgu;
	private String    prodBuyer;
	private Long      prodCost;
	private Long      prodPrice;
	private Long      prodSale;
	private String    prodOutline;
	@Exclude          
	private String prodDetail;
	private String prodImg;
	private Long   prodTotalstock;
	private LocalDate prodInsdate;
	private Long   prodProperstock;
	private String prodSize;
	private String prodColor;
	private String prodDelivery;
	private String prodUnit;
	private Long   prodQtyin;
	private Long   prodQtysale;
	private Long   prodMileage;
	
	private BuyerVO buyer; // Has a 관계 (1:1), PROD(1) : BUYER(1) --> ProdVO has a BuyerVO
	private LprodVO lprod; // Has a 관계 (1:1), PROD(1) : LPROD(1) --> ProdVO has a LprodVO
	private List<CartVO> cartList; // Has Many 관계 
	
	
	
}

package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString.Exclude;

@Data
@EqualsAndHashCode(of = "buyerId")
public class BuyerVO implements Serializable{
	private String buyerId;
	private String buyerName;
	private String buyerLgu;
	private String buyerBank;
	@Exclude
	@JsonIgnore
	private transient String buyerBankno;
	private String buyerBankname;
	private String buyerZip;
	private String buyerAdd1;
	private String buyerAdd2;
	private String buyerComtel;
	private String buyerFax;
	private String buyerMail;
	private String buyerCharger;
	private String buyerTelext;
	
	//jpa의 구조를 반영해서 비슷하게 사용하려고
	private LprodVO lprod; 
	private int prodCount;
	
	private List<ProdVO> prodList; //--> Has Many, BUYER(1) : PROD(N) --> BuyerVO has many ProdVO
}

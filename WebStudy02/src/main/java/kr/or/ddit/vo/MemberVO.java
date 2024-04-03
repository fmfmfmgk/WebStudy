package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 	회원관리를 위한 Domain Layer 
 */
@Data
@ToString(exclude = {"memPass","memRegno1","memRegno2"})
@EqualsAndHashCode(of = "memId")
public class MemberVO implements Serializable{
	private String memId;
	@JsonIgnore
	private String memPass;
	private String memName;
	@JsonIgnore
	private String memRegno1;
	@JsonIgnore
	private String memRegno2;
	private String memBir;
	private String memZip;
	private String memAdd1;
	private String memAdd2;
	private String memHometel;
	private String memComtel;
	private String memHp;
	private String memMail;
	private String memJob;
	private String memLike;
	private String memMemorial;
	private String memMemorialday;
	private Long memMileage;
	private boolean memDelete;
	
	//구매기록 set(중복을 허용하지 않겠다)
	private Set<CartVO> cartList; // Has Many
}




















package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "lprodId")
public class LprodVO implements Serializable{
	private String lprodId;
	private String lprodGu;
	private String lprodNm;
}

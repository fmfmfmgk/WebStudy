package kr.or.ddit.vo;

import java.io.Serializable;

/**
 *	VO(Value Object), DTO(Data Transfer Object), Model, Bean (자바 Bean규약)
 *	1. 값을 저장할 수 이는 프로퍼티.
 *	2. 캡슐화
 *	3. 캡슐화된 프로퍼티에 대한 인터페이스 제공.
 *	4. 상태 비교 방법 제공. 
 *		--,equals(*)
 *	5. 상태 확인 방법 제공.
 *	6. 직렬화 가능.
 */
public class BtsVO implements Serializable, Comparable<BtsVO>{
	
	public BtsVO() {
		super();
	}
	
	
	public BtsVO(String code, String name, String path, int count) {
		super();
		this.code = code;
		this.name = name;
		this.path = path;
		this.count = count;
	}

	private String code;
	private String name;
	private transient String path;
	private int count;
	
	
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BtsVO other = (BtsVO) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BtsVO [code=" + code + ", name=" + name + ", count=" + count + "]";
	}


	@Override
	public int compareTo(BtsVO o) {
		return -(this.count - o.getCount());
	}
	
}

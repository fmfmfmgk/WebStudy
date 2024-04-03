package kr.or.ddit.person.dao;

import java.util.List;

import kr.or.ddit.vo.PersonVO;

/**
 * Persistent Layer : persistece 영역에 저장된 raw data를 domain 객체로 매핑하는 
 * 					  역할을 담당하는 객체. 
 */
public interface PersonDAO {

	public List<PersonVO> selectPersonList();

	public PersonVO selectPerson(String id);

}
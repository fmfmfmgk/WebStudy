package kr.or.ddit.person.service;

import java.util.List;

import kr.or.ddit.vo.PersonVO;

/**
 * 	Business Losic Layer : 하나의 어플리케이션이 가진 특징적인 로직이 구현되는 객체.
 *
 *	ex) 급여 명세서를 수현한다면??
 *
 *	실행환경과는 독립된 model layer를 먼저 설계한다.
 *	사원 + 근태 -> Domain layer 설계
 *	사원 데이터와 근태기록 조회(select) : Persistence Layer 구현.
 *	사원데이터와 근태기록을 토대로 급여 정보 계산 : Business Logic Layer 구현.
 *	
 *	실행환경이 종속되는 레이어 설계 : Controller layer, View layer
 *
 *
 */
public interface PersonService {
	public List<PersonVO> retrievePersonList();
	public PersonVO retrievePerson(String id);
	
}

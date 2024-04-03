package kr.or.ddit.person.service;

import java.util.List;

import kr.or.ddit.person.dao.PersonDAO;
import kr.or.ddit.person.dao.PersonDAOImpl;
import kr.or.ddit.person.exception.PersonNotFoundException;
import kr.or.ddit.vo.PersonVO;

public class PersonServiceImpl implements PersonService {
	private PersonDAO dao = new PersonDAOImpl();
	
	@Override
	public List<PersonVO> retrievePersonList() {
		List<PersonVO> people = dao.selectPersonList();
		for(PersonVO once : people) {
			System.out.printf("%s 조회함.\n", once.getName());
		}
		return people;
	}

	@Override
	public PersonVO retrievePerson(String id) {
		PersonVO person = dao.selectPerson(id);
		if(person==null) {
			throw new PersonNotFoundException(id);
		}
		return person;
	}
	
}

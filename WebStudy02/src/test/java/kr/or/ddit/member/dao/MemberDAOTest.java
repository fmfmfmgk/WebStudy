package kr.or.ddit.member.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.junit.jupiter.api.Test;

import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class MemberDAOTest {
	MemberDAO dao = new MemberDAOImpl();
	
	@Test
	void testInsertMember() {
	      MemberVO member = new MemberVO();
	      assertThrows(PersistenceException.class, ()->dao.insertMember(member));
	      member.setMemId("a004");
	      member.setMemPass("java");
	      member.setMemName("테스터");
	      member.setMemZip("00000");
	      member.setMemAdd1("대전 오류");
	      member.setMemAdd2("대덕인재개발원");
	      member.setMemMail("aa@naver.com");
	      member.setMemBir("2024-01-01");
	      int rowcnt = dao.insertMember(member);
	      assertEquals(1, rowcnt);
	}
	
	@Test
	void testSelectMemberList() {
		List<MemberVO> memberList = dao.selectMemberList();
		assertNotNull(memberList);//절대 null이어서는 안될때
		assertNotEquals(0, memberList.size());
		log.info("list : {}",memberList);
	}

	@Test
	void testSelectMember() {
		String memId = "a001";
		MemberVO member = dao.selectMember(memId);
		assertNotNull(member);//null이 아니라고 확인할때
		System.out.println(member);
	}
	
	@Test
	void testUpdateMember() {
		assertThrows(PersistenceException.class, ()->dao.updateMember(null));
		String memId = "a004";
	      MemberVO member = dao.selectMember(memId);
	      member.setMemPass("fseaf");
	      member.setMemName("테스터");
	      member.setMemZip("00000");
	      member.setMemAdd1("대전 오류");
	      member.setMemAdd2("대덕인재개발원");
	      member.setMemMail("aa@naver.com");
	      member.setMemBir("2024-01-01");
	      int rowcnt = dao.updateMember(member);
	      assertEquals(1, rowcnt);
	}
	
	@Test
	void testDeleteMember() {
		String memId = "a004";
		int rowcnt = dao.deleteMember(memId);
		assertEquals(1, rowcnt);
	}
}

package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.PkNotFoundException;
import kr.or.ddit.vo.MemberVO;

/**
 * 회원 관리(CRUD)를 위한 Business Logis Layer(Service Layer)
 *
 */
public interface MemberService {
	/**
	 * 회원 가입 
	 * @param member
	 * @return pkduplicated, ok, fail
	 */
	public ServiceResult createMember(MemberVO member);
	/**
	 * 회원 목록 조회
	 * @return
	 */
	public List<MemberVO> retrieveMemberList();
	
	/**
	 * 회원 정보 상세 조회
	 * @param memId
	 * @return
	 * @throws PkNotFoundException(500) 존재하지 않는경우 예외 발생.
	 */
	public MemberVO retrieveMember(String memId) throws PkNotFoundException;
	
	/**
	 * 회원 정보 수정
	 * @param member
	 * @return INVALIDPASSWORD, OK, FAIL
	 * @throws PkNotFoundException(500) 존재하지 않는경우 예외 발생.
	 */
	public ServiceResult modifyMember(MemberVO member) throws PkNotFoundException;
	
	/**
	 * 회원 정보 삭제
	 * @param inputData(인증용 아이디, 비밀번호)
	 * @return INVALIDPASSWORD, OK, FAIL
	 * @throws PkNotFoundException(500) 존재하지 않는경우 예외 발생.
	 */
	public ServiceResult removeMember(MemberVO inputData) throws PkNotFoundException;
}

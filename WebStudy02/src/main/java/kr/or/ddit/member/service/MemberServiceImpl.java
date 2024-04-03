package kr.or.ddit.member.service;

import java.util.List;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.PkNotFoundException;
import kr.or.ddit.login.AuthenticateException;
import kr.or.ddit.login.BadCredentialExecption;
import kr.or.ddit.login.service.AuthenticateService;
import kr.or.ddit.login.service.AuthenticateServiceImpl;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;

public class MemberServiceImpl implements MemberService {
	private MemberDAO dao = new MemberDAOImpl(); 
	private AuthenticateService authService = new AuthenticateServiceImpl();
	
	private void encryptMember(MemberVO member) {
		String plain = member.getMemPass();
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		String encoded = encoder.encode(plain);
		member.setMemPass(encoded);
	}
	
	@Override
	public ServiceResult createMember(MemberVO member) {
		ServiceResult result = null;
		if(dao.selectMember(member.getMemId())==null) {
			encryptMember(member);
			int rowcnt = dao.insertMember(member);
			result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		}else {
			result = ServiceResult.PKDUPLICATED;
		}
		return result;
	}

	@Override
	public List<MemberVO> retrieveMemberList() {
		return dao.selectMemberList();
	}

	@Override
	public MemberVO retrieveMember(String memId) throws PkNotFoundException {
		MemberVO vo = dao.selectMember(memId);
		
		if(vo ==null) {
			throw new PkNotFoundException(500);
		}
		
		return vo;
	}
	
	@Override
	public ServiceResult modifyMember(MemberVO member) throws PkNotFoundException {
		try {
			authService.authenticate(member);
			return dao.updateMember(member) > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		}catch (BadCredentialExecption e) {
			return ServiceResult.INVALIDPASSWORD;
		}
	}

	@Override
	public ServiceResult removeMember(MemberVO inputData) throws PkNotFoundException {
		try {
			authService.authenticate(inputData);
			return dao.deleteMember(inputData.getMemId()) > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		}catch (BadCredentialExecption e) {
			return ServiceResult.INVALIDPASSWORD;
		}
	}
}

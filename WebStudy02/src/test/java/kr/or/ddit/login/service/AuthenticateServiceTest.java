package kr.or.ddit.login.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import kr.or.ddit.login.BadCredentialExecption;
import kr.or.ddit.login.UserNotFoundException;
import kr.or.ddit.vo.MemberVO;

class AuthenticateServiceTest {
	AuthenticateService service = new AuthenticateServiceImpl();
	
	@Test
	void testAuthenticateUserNotFound() {
		final MemberVO member = new MemberVO();
		member.setMemId("a001as");
		assertThrows(UserNotFoundException.class, new Executable() {
			
			@Override
			public void execute() throws Throwable {
				service.authenticate(member);
				
			}
		});
	}
	
	@Test
	void testAuthenticateBadCredential() {
		final MemberVO member = new MemberVO();
		member.setMemId("b001");
		member.setMemPass("excvbgxc");
		assertThrows(BadCredentialExecption.class, new Executable() {
			
			@Override
			public void execute() throws Throwable {
				service.authenticate(member);
				
			}
		});
	}
	
	@Test
	void testAuthenticate() {
		final MemberVO member = new MemberVO();
		member.setMemId("b001");
		member.setMemPass("1004");
		assertNotNull(service.authenticate(member));
	}
}








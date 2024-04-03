package kr.or.ddit.crypto;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class passwordEncoderTest {
	PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

	@Test
	void testEncrypt() {
//		비밀번호
		String plain = "java";
		
//		암호화를 시키는데 시간데이터를 추가해서 매번 다른 결과가 나오도록 한다.
		String encoded = encoder.encode(plain);
		
		log.info("encoded : {}",encoded);
	}
	
	@Test
	void testMatches() {
		String savedPass = "{bcrypt}$2a$10$J28ZqOBvnMwCNQhJeIkaZenoKtPsVp3YRv0qb72fI558O1.q.xAuy";
		String inputPass = "javaa";
		
		log.info("인증 성공 여부 : {}",encoder.matches(inputPass, savedPass));
	}
}
//가입절차를 수정
//인증절차 로직을 수정 ()
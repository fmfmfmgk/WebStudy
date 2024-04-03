package kr.or.ddit.login;

/**
 *	 비밀번호 인증 실패나 이중 인증 실패를 표현할 예외
 *
 */
public class BadCredentialExecption extends AuthenticateException{

	public BadCredentialExecption() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BadCredentialExecption(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public BadCredentialExecption(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public BadCredentialExecption(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public BadCredentialExecption(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}

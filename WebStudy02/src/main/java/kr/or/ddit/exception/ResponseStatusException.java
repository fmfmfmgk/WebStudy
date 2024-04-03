package kr.or.ddit.exception;

public class ResponseStatusException extends RuntimeException{
	private int status;
	
	public ResponseStatusException(int status, String message) {
		super(message);
		this.status = status;
	}
	
	
	public ResponseStatusException(int status) {
		this(status, String.format("%d 상태코드 결정을 위한 예외",status));
	}
	
	public int getStatus() {
		return status;
	}
	
}

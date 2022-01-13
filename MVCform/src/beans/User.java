package beans;

// JAVA Bean으로 만들 클래스 (필드변수, 기본생성자(생략가능), get/set 메소드 필요)
public class User {
	private String email ="";
	private String password ="";
	private String message="";
	
	public User() {
		
	}
	
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}
	public String getMessage() {
		return message;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	// 입력된 필드변수들의 값이 유효한지 유효성검사
	public boolean validate() {
//		if(email == null) {
//			message = "Invalid email";
//			return false;
//		}
//		if(password == null) {
//			message = "Invalid password";
//			return false;
//		}
		// 정규표현식 \ 사용하려면 \\ 입력해야함 ,\w 모든문자(숫자포함) 
		if(!email.matches("\\w+@\\w+\\.\\w+")) { // 이메일 양식 XXX@XXX.XXX 인지 체크
			message = "Invalid email";
			return false;
		}
		if(password.length() < 8) {
			message = "패스워드는 8자 이상";
			return false;
		}else if (password.matches("\\w*\\s+\\w*")) {// \s 스페이스
			message = "패스워드에 스페이스가 포함되면 안됩니다.";
			return false;
		}
		// 위의 조건들을 다 통과하면 유효성메소드 값 true를 return한다.
		return true;
	}
}

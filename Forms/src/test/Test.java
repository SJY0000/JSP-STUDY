package test;

import beans.User;

public class Test {

	public static void main(String[] args) {
		User user = new User("abcd22@naver.com", "abcd1234");
		
		if(user.validate()) {
			System.out.println("검사 성공!");
		} else {
			System.out.println("오류 메시지 : " + user.getMessage());
		}

	}

}

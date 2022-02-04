package utills;

import model.Contact;
// 제이슨 형태로 보낼 클래스
public class ContactJson { 
	
	private boolean status;  //상태 (성공/실패)
	private String message;  //메세지 입력
	private Contact contact; //연락처 객체를 하나 입력
	
	
	public boolean getStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Contact getContact() {
		return contact;
	}
	
	public void setContact(Contact contact) {
		this.contact = contact;
	}

}

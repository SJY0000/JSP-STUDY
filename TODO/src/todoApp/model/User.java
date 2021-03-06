package todoApp.model;

import java.util.List;

public class User {
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	
	
//	private Todo todo; // 테이블 조인에서 1:1의 관계
	private List<Todo> todolist; // 테이블 조인에서 1:N의 관계(1에다가 설정)
	private Integer todoCount;
	
	public User() {} // 기본 생성자는 JAVA bean용으로 필요
	
	public User(String firstName, String lastName, String userName, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public List<Todo> getTodolist() {
		return todolist;
	}

	public void setTodolist(List<Todo> todolist) {
		this.todolist = todolist;
	}

	public Integer getTodoCount() {
		return todoCount;
	}

	public void setTodoCount(Integer todoCount) {
		this.todoCount = todoCount;
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName + ", password="
				+ password + ", todolist=" + todolist + ", todoCount=" + todoCount + "]";
	}
}

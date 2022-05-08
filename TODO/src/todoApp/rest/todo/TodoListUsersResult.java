package todoApp.rest.todo;

import java.util.List;

import todoApp.model.User;

public class TodoListUsersResult {

	private boolean hasResult;
	private int totalCount;
	private List<User> userlist; // 조인결과로 user가 todoList를 포함함
	
	public boolean isHasResult() {
		return hasResult;
	}
	public void setHasResult(boolean hasResult) {
		this.hasResult = hasResult;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public List<User> getUserlist() {
		return userlist;
	}
	public void setUserlist(List<User> userlist) {
		this.userlist = userlist;
	}
	
	@Override
	public String toString() {
		return "TodoListUsersResult [hasResult=" + hasResult + ", totalCount=" + totalCount + ", userlist=" + userlist
				+ "]";
	}

}

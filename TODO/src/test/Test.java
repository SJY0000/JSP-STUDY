package test;

import java.time.LocalDate;
import java.util.List;

import todoApp.dao.TodoDaoImpl;
import todoApp.model.Todo;

public class Test {

	public static void main(String[] args) {
		TodoDaoImpl dao = new TodoDaoImpl();
//		Todo todo = new Todo(1L, "할일1", "asd", "첫번째 할일", LocalDate.parse("2022-01-21"), false); 
//		Todo todotest = new Todo(2L, "할일2", "asd", "첫번째 할일", LocalDate.parse("2022-02-22"), false); 
		
//		dao.insertTodo(todo);
//		dao.insertTodo(todotest);
		
//		Todo t1 = dao.selectTodo(1);
//		System.out.println(t1.toString());
//		System.out.println(t1);
//		System.out.println(dao.selectTodo(1));
		
//		Todo todoup = new Todo(1L, "할일1011", "asd", "1011번째 할일", LocalDate.parse("2022-01-21"), false); 
//		dao.updateTodo(todoup);
		
//		dao.deleteTodo(4);
		
		List<Todo> todos = dao.selectAllTodos();
		
		for(Todo todo : todos) {
			System.out.println(todo.toString());
		}
		
		
		
	}

}

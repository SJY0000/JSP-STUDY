package com.example.retrofit_study;

import java.util.Map;

import com.exaple.domain.User;
import com.exaple.domain.UserListResult;
import com.exaple.domain.UserOneResult;
import com.exaple.domain.UserResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

// Retrofit 객체의 create 메소드에 의해 자동으로 구현 객체를 만들 수 있음
// 구현된 서비스 객체의 용도는 GET, POST, PUT, DELETE 요청을 담당하는 메소드를 가짐
public interface UserService {

	@GET("todo/api/user/") // 필수 Parameter가 있다면 같이 넣어줘야함
	Call<UserOneResult> getUser(
			// 동적으로 변경해야하는 Parameter는 @Query 어노테이션을 이용해서 메소드를 호출 할 때 마다 값을 넘겨받는다	
				@Query("category") String category, 
				@Query("userName") String userName // @Query->변수명
				);
//		(@Query("category") = String category) = (category = 값) parameter 받을 떄 주소에 넣는 것 처럼

//	@GET("TODO/api/user/")
//	Call<UserOneResult> getUser(@QueryMap Map<String, String> options);
	
	@GET("todo/api/user/")
	Call<UserListResult> getUsers(@Query("category") String category);
	
	@POST("todo/api/user/")
	Call<UserResult> createUser(@Body User user); // @Body->문자열을 json타입으로 변환시켜서 전송
	
	@PUT("todo/api/user/")
	Call<UserResult> updateUser(@Body User user);
	
	@DELETE("todo/api/user/")
	Call<UserResult> deleteUser(@Query("userName") String userName);
}

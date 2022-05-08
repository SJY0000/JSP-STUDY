package com.example.retrofit_study;

import com.exaple.domain.User;
import com.exaple.domain.UserListResult;
import com.exaple.domain.UserOneResult;
import com.exaple.domain.UserResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EX5 {

	public static void main(String[] args) {
		// delete user
		Retrofit retrofit = new Retrofit.Builder()
										.baseUrl("http://localhost:8090/")
										.addConverterFactory(GsonConverterFactory.create())
										.build();
		UserService userService = retrofit.create(UserService.class);
		
		
		// 네트워크로 요청할 정보객체
		Call<UserResult> call = userService.deleteUser("qwe");
	
		call.enqueue(new Callback<UserResult>() {
			// 익명메소드
			// 네트워크 요청을 보냄, 서버로부터 응답이 오면 Callback객체의 onResponse 또는 onFailure가 호출됨
			public void onResponse(Call<UserResult> call, Response<UserResult> response) {
				// isSuccessful 메소드로 200번대 정상 응답이 아닌 경우 (응답코드가 300, 400, 500번대 인 경우)
				if (response.isSuccessful() == false) {
					System.out.println("onResponse 에러");
					return;
				}
				// isSuccessful 메소드로 200번대 정상 응답이 아닌 경우
				System.out.println("onResponse 성공");
				UserResult userResult = response.body();
				System.out.println("응답결과 :" + userResult.toString());
				
			}

			public void onFailure(Call<UserResult> call, Throwable throwable) {
				System.out.println("onFailure 에러 : " + throwable.getMessage());
				
			}
		});
	}// main
}

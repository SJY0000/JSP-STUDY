package todoApp.rest.todo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/displayImage", loadOnStartup = 1)
public class DisplayImageServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// (경로가 포함된) 이미지 파일명을 파라미터로 가져오기
		String filename = request.getParameter("filename"); // leaf.png
		//  / 나 \\ 사용해야 잘못 인식하지 않는다.
		File file = new File("C:/java502/Image", filename); // C:\java502\Image/leaf.png
		
		if (!file.exists()) { // file 없으면
			System.out.println("요청한 이미지 파일이 해당 경로에 존재하지 않습니다.");
			return;
		}
		
		// 이미지 파일을 전송할것이기 때문에 image/png , image/gif 등 파일의 이미지 확장자가 출력됨
		String contentType = Files.probeContentType(file.toPath());
		
		response.setContentType(contentType);
		
		// 입력스트림 객체 준비
		BufferedInputStream is = new BufferedInputStream(new FileInputStream(file));
		// 출력스트림 객체 준비
		BufferedOutputStream os = new BufferedOutputStream(response.getOutputStream());
		
		int data;
		while ((data = is.read()) != -1) { // -1이면 데이터 있음, 아니면 없음
			os.write(data);
		}// while
		os.flush(); // 출력버퍼 비우기, 객체를 닫을 꺼면 없어도 됨
		
		// 스트림 객체 닫기
		is.close();
		os.close();
		
		
	} // doGet
}

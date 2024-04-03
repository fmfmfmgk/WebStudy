package kr.or.ddit.person.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.person.service.PersonService;
import kr.or.ddit.person.service.PersonServiceImpl;
import kr.or.ddit.vo.PersonVO;

@WebServlet("/people.do")
public class PersonDetailServlet extends HttpServlet{
	private PersonService service = new PersonServiceImpl();
	
	/*
	 * 	servlet을 만드는 순서
	 * 
	 * 	0. 클라이언트로부터 받은 정보
	 * 		1)URL : 어노테이션으로 설정
	 * 		2)Method : 'doGET'메소드로 설정
	 * 		3)QueryString 정보 : request.getParameter로 가져옴
	 * 
	 * 	1.Service와의 의존관계 생성
	 * 	2.parameter값 가져오기
	 * 	3.parameter값이 제대로 되어있는지 검증을 거침
	 * 	4.service를 통해 데이터 가져오기
	 * 	5.가져온 값을 공유할 수 있도록 세팅
	 * 	6.view 레이어의 경로를 설정 (forward방식)
	 * 
	 */
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("who");
		if(id==null||id.isEmpty()) {
			resp.sendError(400,"필수 파라미터 누락");
			return;
		}
		
		PersonVO person = service.retrievePerson(id);
		req.setAttribute("person", person);
		req.getRequestDispatcher("/WEB-INF/views/person/detail.jsp").forward(req, resp);
	}
}

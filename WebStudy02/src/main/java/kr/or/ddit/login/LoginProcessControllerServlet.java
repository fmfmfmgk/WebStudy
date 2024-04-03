package kr.or.ddit.login;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.exception.ResponseStatusException;
import kr.or.ddit.login.service.AuthenticateService;
import kr.or.ddit.login.service.AuthenticateServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/login/loginProcess.do")
public class LoginProcessControllerServlet extends HttpServlet{
	private AuthenticateService service = new AuthenticateServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session =  req.getSession();
		if(session.isNew()) {
			resp.sendError(400,"로그인을 하려면 로그인 폼이 먼저 최초의 요청으로 전송되었어야 함.");
			return;
		}
		
//		1. body 영역의 디코딩에 사용할 charset 설정
		req.setCharacterEncoding("UTF-8");
//		2. 필요 파라미터 확보
//		3. 파라미터 검증(null인지 아닌지, 화이트스페이스인지 아닌지)
//			- 검증 통과
		try {
			String memId = Optional.of(req.getParameter("memId"))
								   .filter(id->!id.isEmpty())
								   .orElseThrow(()->new ResponseStatusException(400,"아이디 누락"));
			
			String memPass = Optional.of(req.getParameter("memPass"))
									 .filter(pa->!pa.isEmpty())
									 .orElseThrow(()->new ResponseStatusException(400,"비밀번호 누락"));
			String viewName = null;
			
			
//				4. 인증 여부 판단
			try {
				MemberVO inputData = new MemberVO();
				inputData.setMemId(memId);
				inputData.setMemPass(memPass);
				
				
				MemberVO authMember = service.authenticate(inputData);
				
//				인증된 사용자임을 증명하는 상태정보 생성 및 유지
				session.setAttribute("authMember", authMember);
//				- 성공 : 웰컴 페이지로 이동 - redirect
				viewName = "redirect:/";
			}catch (AuthenticateException e) {
//				- 실패 : 로그인 페이지로 이동 - forward
				session.setAttribute("message",e.getMessage());
//				req.getRequestDispatcher("/login/loginForm.jsp").forward(req, resp);
				viewName = "redirect:/login/loginForm.jsp";
			}
			
			if(viewName.startsWith("redirect:")) {
				String location = viewName.replace("redirect:", req.getContextPath());
				resp.sendRedirect(location);
			}else {
				req.getRequestDispatcher(viewName).forward(req, resp);
			}

		}catch (ResponseStatusException e) {
//			- 검증 미통과 : 상태코드 400
			resp.sendError(e.getStatus(),e.getMessage());
		}
		
				
	}
}



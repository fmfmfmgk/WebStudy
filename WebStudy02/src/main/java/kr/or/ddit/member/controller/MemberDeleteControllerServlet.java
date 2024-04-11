package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberDelete.do")
public class MemberDeleteControllerServlet extends HttpServlet{
	MemberService service = new MemberServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String memId = req.getUserPrincipal().getName();
		HttpSession session = req.getSession();
		String viewName = null;
		
		String memPass = req.getParameter("password");
		if(StringUtils.isBlank(memPass)) {
			resp.sendError(400);
			return;
		}
//		3.로직 사용
		MemberVO inputData = new MemberVO();
		inputData.setMemId(memId);
		inputData.setMemPass(memPass);
		ServiceResult result = service.removeMember(inputData);
		
//		4.로직으로부터 확보한 모델을 공유
		switch (result) {
		case INVALIDPASSWORD:
			session.setAttribute("message", "비밀번호 오류");
			viewName = "redirect:/mypage";
			break;
		case FAIL:
			session.setAttribute("message", "서버오류");
			viewName = "redirect:/mypage";
			break;
		default:
			viewName = "forward:/login/logout.do";
			break;
		}
		
//			5. 뷰 선택
//			6. 뷰로 이동

		
	}
}

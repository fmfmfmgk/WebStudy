package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.utils.PopulateUtils;
import kr.or.ddit.utils.ValidateUtils;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

/**
 *	1. 요청 접수, 분석
 *	2. 검증
 *	3. 로직 사용(model 확보 )
 *	4. scope를 이용해 model 공유
 *	5. view 결정
 *	6. view로 이동(flow control)
 *
 */
@Slf4j
@WebServlet("/member/memberInsert.do")
public class MemberInsertControllerServlet extends HttpServlet{
	private MemberService service = new MemberServiceImpl();
	private PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String viewName = "member/memberForm";
		new ViewResolverComposite().resolveView(viewName, req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		* 1. 요청 접수, 분석
		MemberVO member = new MemberVO(); // command Object
		
		req.setAttribute("member", member);
		Map<String, String[]> parameterMap = req.getParameterMap();
		System.out.println("파라미터===>"+parameterMap);
		PopulateUtils.populate(member, parameterMap);
		
//		2. 검증
		Map<String, List<String>> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		
		boolean valid = ValidateUtils.validate(member, errors, InsertGroup.class);
		System.out.println("검사결과 ===>"+valid);
		String viewName = null;
		
		if(errors.isEmpty()) {
//		 	3. 로직 사용(model 확보)
			ServiceResult result = service.createMember(member);
			switch (result) {
			case PKDUPLICATED:
				req.setAttribute("message", "아이디 중복, 바꾸셈.");
				viewName = "/WEB-INF/views/member/memberForm.jsp";
				break;
			case FAIL:
				req.setAttribute("message", "서버 오류, 잠시 뒤 다시 가입하세요.");
				viewName = "/WEB-INF/views/member/memberForm.jsp";
				break;
			default:
				viewName = "redirect:/";
				break;
			}
//		 * 4. scope 를 이용해 model 공유
		}else {
			viewName = "member/memberForm";
		}
//		 * 5. view 결정
//		 * 6. view 로 이동(flow control)
		new ViewResolverComposite().resolveView(viewName, req, resp);
	}
}

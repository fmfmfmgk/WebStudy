package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.ProdVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet("/prod/prodList.do")
public class prodListConrollerServlet extends HttpServlet{
	ProdService service = new ProdServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		1. service 객체 만들기
		List<ProdVO> prodList = service.retrieveProdList();
		log.info("prodList : {}",prodList);
		
//		2. scope에 저장
		req.setAttribute("prodList", prodList);
		
//		3. view로 이동
		String viewName = "prod/prodList";
		new ViewResolverComposite().resolveView(viewName, req, resp);
	}
}

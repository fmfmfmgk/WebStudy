package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.exception.ResponseStatusException;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.ProdVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet("/prod/prodDetail.do")
public class ProdDetailControllerServlet extends HttpServlet{
	ProdService service = new ProdServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		1.파라미터 받기
//		1.1 파라미터 검증
		String prodId = Optional.of(req.getParameter("what"))
								.filter(prod->!prod.isEmpty())
								.orElseThrow(()->new ResponseStatusException(400,"입력 누락"));
		
//		1. service 객체 만들기
		ProdVO prod = service.retrieveProd(prodId);
		
//		2. scope에 저장
		req.setAttribute("prod", prod);
		
//		3. view로 이동
		String viewName = "/WEB-INF/views/prod/prodDetail.jsp";
		
		if(viewName.startsWith("redirect:")) {
			String location = viewName.replace("redirect:", req.getContextPath());
			resp.sendRedirect(location);
		}else if(viewName.startsWith("forward:")){
			String path = viewName.substring("forward:".length()); 
			req.getRequestDispatcher(path).forward(req, resp);
		}else {
			req.getRequestDispatcher(viewName).forward(req, resp);
		}
	}
}

package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.ProdVO;

@WebServlet("/prod/prodInsert.do")
public class ProdInsertControllerServlet extends HttpServlet{
	private ProdService service = new ProdServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String viewName = "/WEB-INF/views/prod/prodForm.jsp";
		
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
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		* 1. 요청 접수, 분석
		req.setCharacterEncoding("UTF-8");
		ProdVO prod = new ProdVO(); // command Object
		
		req.setAttribute("member", prod);
		Map<String, String[]> parameterMap = req.getParameterMap();
		
		try {
			BeanUtils.populate(prod, parameterMap);
		} catch (Exception e) {
//			throw new RuntimeException(e);
			String insdate = req.getParameter("prodInsdate");
			LocalDate prodInsdate = LocalDate.parse(insdate);
			prod.setProdInsdate(prodInsdate);
		}
		
//		2. 검증
		Map<String, String> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		boolean valid = validate(prod, errors);
		String viewName = null;
		
		if(errors.isEmpty()) {
//		 	3. 로직 사용(model 확보)
			ServiceResult result = service.createProd(prod);
			switch (result) {
			case FAIL:
				req.setAttribute("message", "서버 오류, 잠시 뒤 다시 가입하세요.");
				viewName = "/WEB-INF/views/member/memberForm.jsp";
				break;
			default:
				viewName = "redirect:/prod/prodDeail.do?what="+prod.getProdId();
				break;
			}
//		 * 4. scope 를 이용해 model 공유
		}else {
			viewName = "/WEB-INF/views/prod/prodForm.jsp";
		}
//		 * 5. view 결정
//		 * 6. view 로 이동(flow control)
		if(viewName.startsWith("redirect:")) {
			String location = viewName.replace("redirect:", req.getContextPath());
			resp.sendRedirect(location);
		}else {
			req.getRequestDispatcher(viewName).forward(req, resp);
		}
	}
	
	private boolean validate(ProdVO prod, Map<String, String> errors) {
		boolean valid = true;
		if (StringUtils.isBlank(prod.getProdId())) {
			valid = false;
			errors.put("prodId", "상품코드 누락");
		}
		if (StringUtils.isBlank(prod.getProdName())) {
			valid = false;
			errors.put("prodName", "상품명 누락");
		}
		if (StringUtils.isBlank(prod.getProdLgu())) {
			valid = false;
			errors.put("prodLgu", "상품분류 누락");
		}
		if (StringUtils.isBlank(prod.getProdBuyer())) {
			valid = false;
			errors.put("prodBuyer", "거래처 누락");
		}
		if (prod.getProdCost()==null) {
			valid = false;
			errors.put("prodCost", "구매가 누락");
		}
		if (prod.getProdPrice()==null) {
			valid = false;
			errors.put("prodPrice", "판매가 누락");
		}
		if (prod.getProdSale()==null) {
			valid = false;
			errors.put("prodSale", "세일가 누락");
		}
		if (StringUtils.isBlank(prod.getProdOutline())) {
			valid = false;
			errors.put("prodOutline", "요약정보 누락");
		}
		if (StringUtils.isBlank(prod.getProdImg())) {
			valid = false;
			errors.put("prodImg", "이미지 누락");
		}
		if (prod.getProdTotalstock()==null) {
			valid = false;
			errors.put("prodTotalstock", "총재고 누락");
		}
		if (prod.getProdProperstock()==null) {
			valid = false;
			errors.put("prodProperstock", "적정재고 누락");
		}
		return valid;
	}
}

package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.AbstractConverter;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.lprod.controller.OthersControllerAdvice;
import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.utils.PopulateUtils;
import kr.or.ddit.utils.ValidateUtils;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.ProdVO;

@WebServlet("/prod/prodInsert.do")
public class ProdInsertControllerServlet extends HttpServlet{
	private ProdService service = new ProdServiceImpl();
	private OthersControllerAdvice advice = new OthersControllerAdvice();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		advice.addLprodList(req);
		advice.addBuyerList(req);
		
		String viewName = "prod/prodForm";
		new ViewResolverComposite().resolveView(viewName, req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		advice.addLprodList(req);
		advice.addBuyerList(req);
		
		//		* 1. 요청 접수, 분석
		req.setCharacterEncoding("UTF-8");
		ProdVO prod = new ProdVO(); // command Object
		
		req.setAttribute("member", prod);
		Map<String, String[]> parameterMap = req.getParameterMap();
		
//		새로 추가함.
		PopulateUtils.populate(prod, parameterMap);
		
//		2. 검증
		Map<String, List<String>> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		boolean valid = ValidateUtils.validate(prod, errors, InsertGroup.class);
		String viewName = null;
		
		if(errors.isEmpty()) {
//		 	3. 로직 사용(model 확보)
			ServiceResult result = service.createProd(prod);
			switch (result) {
			case FAIL:
				req.setAttribute("message", "서버 오류, 잠시 뒤 다시 가입하세요.");
				viewName = "member/memberForm";
				break;
			default:
				viewName = "redirect:/prod/prodDetail.do?what="+prod.getProdId();
				break;
			}
//		 * 4. scope 를 이용해 model 공유
		}else {
			viewName = "prod/prodForm";
		}
//		 * 5. view 결정
//		 * 6. view 로 이동(flow control)
		new ViewResolverComposite().resolveView(viewName, req, resp);
	}
}

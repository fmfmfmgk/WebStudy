<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 로그인 여부를 판단하고,
로그인이 된 경우, 해당 사용자의 이름을 출력하고,
로그인 전이라면, 로그인 페이지로 이동할 수 있는 a태그를 추가할것. -->
<h4>Principal : ${pageContext.request.userPrincipal}</h4>
<c:choose>
	<c:when test="${not empty sessionScope.authMember}">
		<a href="<c:url value='/mypage.do'/>?memId=${authMember.memId}">${authMember.memName}님 환영합니다.</a>
		<form id="logoutForm" method="post"></form>
		<a href="<c:url value='/login/logout.do'/>" class="logoutBtn" data-target-form="#logoutForm">로그아웃</a>
		<script type="text/javascript">
/* 		get방식을 post방식으로 바꿔서 보내기위해 */
			let method = document.querySelector("a[data-target-form]").addEventListener("click", (e)=>{
				e.preventDefault();
				let aTag = e.target;
				let formSelector = aTag.dataset.targetForm;
				let formTag = document.querySelector(formSelector);
				if(formTag){
					formTag.action = aTag.href;
					formTag.requestSubmit();
				}
			});
		</script>
	</c:when>
	<c:otherwise>
		<a href="<c:url value='/login/loginForm.jsp'/>">로그인폼</a>
		<a href="<c:url value='/member/memberInsert.do'/>">가입하기</a>
	</c:otherwise>
</c:choose>
</body>
</html>












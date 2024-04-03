<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%-- 웹에서 기본 컨텐츠 타입으로 사용되는 HTML 컨텐츠를 생성하기 위한 view layer --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#result {
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
}
</style>
</head>
<body>
<c:set value="${pageContext.request.contextPath}" var="cPath" scope="application"></c:set>
<form name="personForm" id="personForm" action="${cPath}/people.do" method="post">
<input type="text" name="who"/>
<table>
	<thead>
		<tr>
			<th>아이디</th>
			<th>이름</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="pp" items="${people}">
			<tr>
				<td>${pp['id']}</td>
				<td><a href="javascript:;" onclick="sendData('${pp['id']}')" data-member-id="${pp['id']}">${pp['name']}</a></td>
			</tr>
		</c:forEach>
		<tr></tr>
	</tbody>
</table>
</form>
<div id="result"></div>
<script>
	function sendData(id) {
	    fetch('<%=request.getContextPath()%>/people.do', {
	        method: "POST",
	        body: 'who=' + id,
	        headers: {
	            'Content-Type': 'application/x-www-form-urlencoded'
	        } 
	    })
	    .then(response => response.text())
	    .then(result => {
	        document.getElementById("result").innerHTML = result;
	    })
	    .catch(error => console.error("Error:", error));
	}
</script>
</body>
</html>
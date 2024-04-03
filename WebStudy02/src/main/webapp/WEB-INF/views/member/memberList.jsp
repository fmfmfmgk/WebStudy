<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/WEB-INF/includee/prescript.jsp"/>
<style>
	tr[data-mem-id]{
		cursor: pointer;
	}
	tr[data-mem-id]:hover{
		background-color: green;	
	}
</style>
</head>
<body data-path="${pageContext.request.contextPath}">
<a href="${pageContext.request.contextPath}/member/memberInsert.do">회원가입하기</a>
<table class="table table-bordered table-striped">
	<thead class="table-dark">
		<tr>
			<th>회원명</th>
			<th>기본주소</th>
			<th>상세주소</th>
			<th>핸드폰번호</th>
			<th>메일주소</th>
			<th>마일리지</th>
		</tr>
		
	</thead>
	<tbody id="tbody">
	<c:if test="${not empty memberList}">
       <c:forEach items="${memberList}" var="member"> 
       		<c:set value="${member.memId eq lastCreated.memId ? 'active' : ''}" var="clzvalue"/>
          <tr class="${clzvalue}" data-mem-id="${member.memId}" data-bs-toggle="modal" data-bs-target="#exampleModal">
              <td>${member.memName}</td>
              <td>${member.memAdd1}</td>
              <td>${member.memAdd2}</td>
              <td>${member.memHp}</td>
              <td>${member.memMail}</td>
              <td>${member.memMileage}</td>
          </tr>
       </c:forEach>
     </c:if>
     <c:if test="${empty memberList}">
     	<tr>
     		<td colspan="6">회원정보 없음.</td>
     	</tr>
     </c:if>
     <c:remove var="lastCreated" scope="session"/>
	</tbody>
</table>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body" id="result">
      	<table>
			<tr>
				<th>회원번호</th>
				<td id="memId"></td>
			</tr>
			<tr>
				<th>회원명</th>
				<td id="memName"></td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td id="memBir"></td>
			</tr>
			<tr>
				<th>우편번호</th>
				<td id="memZip"></td>
			</tr>
			<tr>
				<th>기본주소</th>
				<td id="memAdd1"></td>
			</tr>
			<tr>
				<th>상세주소</th>
				<td id="memAdd2"></td>
			</tr>
			<tr>
				<th></th>
				<td id="memHometel"></td>
			</tr>
			<tr>
				<th></th>
				<td id="memComtel"></td>
			</tr>
			<tr>
				<th>핸드폰번호</th>
				<td id="memHp"></td>
			</tr>
			<tr>
				<th>메일주소</th>
				<td id="memMail"></td>
			</tr>
			<tr>
				<th>직업</th>
				<td id="memJob"></td>
			</tr>
			<tr>
				<th>취미</th>
				<td id="memLike"></td>
			</tr>
			<tr>
				<th>기념일 종류</th>
				<td id="memMemorial"></td>
			</tr>
			<tr>
				<th>기념일자</th>
				<td id="memMemorialday"></td>
			</tr>
			<tr>
				<th>마일리지</th>
				<td id="memMileage"></td>
			</tr>
			<tr>
				<th>상태코드</th>
				<td id="memDelete"></td>
			</tr>
		</table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
       
      </div>
    </div>
  </div>
</div>
<script src="${pageContext.request.contextPath}/resources/js/app/member/memberList.js"></script>
<jsp:include page="/WEB-INF/includee/postScript.jsp"/>
</body>
</html>
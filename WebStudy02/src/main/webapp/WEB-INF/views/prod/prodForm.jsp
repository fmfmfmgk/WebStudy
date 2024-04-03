<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" enctype="application/x-www-form-urlencoded">
	<table>
		<tr>
			<th>상품명</th>
			<td><input type="text" name="prodName" required
				value="${prod.prodName}" class="form-control"><span
				class="text-danger">${error.prodName} </span></td>
		</tr>
		<tr>
			<th>상품분류</th>
			<td>
				<select name="prodLgu">
					<option value="P101">전자제품</option>
				</select>
				<span class="text-danger">${error.prodLgu} </span>
			</td>
		</tr>
		<tr>
			<th>거래처</th>
			<td>
				<select name="prodBuyer">
					<option value="P10101">삼성전자</option>
				</select>
			<span class="text-danger">${error.prodBuyer} </span>
			</td>
		</tr>
		<tr>
			<th>구매가</th>
			<td><input type="number" name="prodCost" required
				value="${prod.prodCost}" class="form-control"><span
				class="text-danger">${error.prodCost} </span></td>
		</tr>
		<tr>
			<th>판매가</th>
			<td><input type="number" name="prodPrice" required
				value="${prod.prodPrice}" class="form-control"><span
				class="text-danger">${error.prodPrice} </span></td>
		</tr>
		<tr>
			<th>세일가</th>
			<td><input type="number" name="prodSale" required
				value="${prod.prodSale}" class="form-control"><span
				class="text-danger">${error.prodSale} </span></td>
		</tr>
		<tr>
			<th>요약정보</th>
			<td><input type="text" name="prodOutline" required
				value="${prod.prodOutline}" class="form-control"><span
				class="text-danger">${error.prodOutline} </span></td>
		</tr>
		<tr>
			<th>상세정보</th>
			<td><input type="text" name="prodDetail"
				value="${prod.prodDetail}" class="form-control"><span
				class="text-danger">${error.prodDetail} </span></td>
		</tr>
		<tr>
			<th>이미지</th>
			<td><input type="text" name="prodImg" required
				value="${prod.prodImg}" class="form-control"><span
				class="text-danger">${error.prodImg} </span></td>
		</tr>
		<tr>
			<th>총재고</th>
			<td><input type="number" name="prodTotalstock" required
				value="${prod.prodTotalstock}" class="form-control"><span
				class="text-danger">${error.prodTotalstock} </span></td>
		</tr>
		<tr>
			<th>입고일</th>
			<td><input type="date" name="prodInsdate"
				value="${prod.prodInsdate}" class="form-control"><span
				class="text-danger">${error.prodInsdate} </span></td>
		</tr>
		<tr>
			<th>적정재고</th>
			<td><input type="number" name="prodProperstock" required
				value="${prod.prodProperstock}" class="form-control"><span
				class="text-danger">${error.prodProperstock} </span></td>
		</tr>
		<tr>
			<th>크기</th>
			<td><input type="text" name="prodSize" value="${prod.prodSize}"
				class="form-control"><span class="text-danger">${error.prodSize}
			</span></td>
		</tr>
		<tr>
			<th>색상</th>
			<td><input type="text" name="prodColor"
				value="${prod.prodColor}" class="form-control"><span
				class="text-danger">${error.prodColor} </span></td>
		</tr>
		<tr>
			<th>배송방법</th>
			<td><input type="text" name="prodDelivery"
				value="${prod.prodDelivery}" class="form-control"><span
				class="text-danger">${error.prodDelivery} </span></td>
		</tr>
		<tr>
			<th>단위</th>
			<td><input type="text" name="prodUnit" value="${prod.prodUnit}"
				class="form-control"><span class="text-danger">${error.prodUnit}
			</span></td>
		</tr>
		<tr>
			<th>입고량</th>
			<td><input type="number" name="prodQtyin"
				value="${prod.prodQtyin}" class="form-control"><span
				class="text-danger">${error.prodQtyin} </span></td>
		</tr>
		<tr>
			<th>출고량</th>
			<td><input type="number" name="prodQtysale"
				value="${prod.prodQtysale}" class="form-control"><span
				class="text-danger">${error.prodQtysale} </span></td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td><input type="number" name="prodMileage"
				value="${prod.prodMileage}" class="form-control"><span
				class="text-danger">${error.prodMileage} </span></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="등록"/>
			</td>
		</tr>
	</table>
</form>
</body>
</html>
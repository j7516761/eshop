<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>產品清單</title>
<link rel="stylesheet" type="text/css" href="styles.css">
<!-- 引入樣式 -->
</head>
<body>
	<h1>產品清單</h1>

	<!-- 新增下拉選單篩選商品分類 	-->
	<form action="product/list" method="GET">
		<label for="category">選擇商品分類:</label> <select id="category"
			name="category">
			<option value="">所有分類</option>
			<c:forEach var="category" items="${categories}">
				<option value="${category.id}">${category.name}</option>
			</c:forEach>
		</select> <input type="submit" value="篩選">
	</form>

	<table border="1">
		<thead>
			<tr>
				<th>商品ID</th>
				<th>商品名稱</th>
				<th>商品價格</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="product" items="${productList}">
				<tr>
					<td>${product.id}</td>
					<td>${product.name}</td>
					<td>${product.price}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<!-- 分頁 -->
	<div class="pagination">
		<c:if test="${currentPage > 1}">
			<a href="product/list?page=${currentPage - 1}">上一頁</a>
		</c:if>
		<c:forEach begin="1" end="${totalPages}" var="i">
			<c:if test="${i == currentPage}">
				<strong>${i}</strong>
				<!-- 當前頁面顯示為粗體 -->
			</c:if>
			<c:if test="${i != currentPage}">
				<a href="product/list?page=${i}">${i}</a>
			</c:if>
		</c:forEach>
		<c:if test="${currentPage < totalPages}">
			<a href="product/list?page=${currentPage + 1}">下一頁</a>
		</c:if>
	</div>
</body>
</html>
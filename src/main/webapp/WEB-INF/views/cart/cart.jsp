<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Shopping Cart</title>
</head>
<body>
    <h1>Shopping Cart</h1>

    <c:if test="${not empty cartItems}">
        <table border="1">
            <tr>
                <th>Product</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Subtotal</th>
                <th>Action</th>
            </tr>
            <c:forEach var="item" items="${cartItems}">
                <tr>
                    <td>${item.product.name}</td>
                    <td>${item.quantity}</td>
                    <td>${item.product.price}</td>
                    <td>${item.product.price * item.quantity}</td>
                    <td>
                        <a href="removeItem.action?productId=${item.product.id}">Remove</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <c:if test="${empty cartItems}">
        <p>Your cart is empty.</p>
    </c:if>

    <a href="${pageContext.request.contextPath}/product/list">Continue Shopping</a>
</body>
</html>
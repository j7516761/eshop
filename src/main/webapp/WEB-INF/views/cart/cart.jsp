<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Shopping Cart</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        .cart-container {
            max-width: 1200px;
            margin: 2rem auto;
            padding: 0 15px;
        }
        .cart-item {
            border: 1px solid #dee2e6;
            border-radius: 8px;
            padding: 1rem;
            margin-bottom: 1rem;
            background: #f8f9fa;
            display: flex;
            align-items: center;
        }
        .cart-image {
            width: 100px; /* 設定圖片寬度 */
            height: auto; /* 自動調整高度 */
            margin-right: 1rem; /* 右邊距 */
        }
        .cart-title {
            font-size: 1.5rem;
            flex-grow: 1; /* 使標題擴展以填滿空間 */
        }
        .cart-price {
            color: #dc3545;
            font-size: 1.25rem;
        }
        .specs-list {
            list-style: none;
            padding-left: 0;
        }
        .spec-item {
            padding: 0.5rem 1rem;
            background: #ffffff;
            margin-bottom: 0.5rem;
            border-radius: 4px;
        }
    </style>
</head>
<body>
    <div class="container-fluid cart-container">
        <h1>Shopping Cart</h1>

        <c:if test="${not empty cartItems}">
            <c:forEach var="item" items="${cartItems}">
                <div class="cart-item">
                    <img src="${pageContext.request.contextPath}/resources/images/products/${item.product.imageUrl}" class="cart-image" alt="${item.product.name}"/> <!-- 正確的圖片路徑 -->
                    <div class="cart-details">
                        <h2 class="cart-title">${item.product.name}</h2>
                        <div class="d-flex justify-content-between align-items-center mb-3">
                            <span class="cart-price">$${item.product.price}</span>
                            <form action="updateQuantity.action" method="post" class="d-flex align-items-center">
                                <input type="number" name="quantity" value="${item.quantity}" min="1" class="form-control" style="width: 100px; margin-right: 1rem;"/>
                                <input type="hidden" name="productId" value="${item.product.id}"/>
                                <button type="submit" class="btn btn-success">Update</button>
                            </form>
                        </div>
                        <a href="removeItem.action?productId=${item.product.id}" class="btn btn-danger">Remove</a>
                    </div>
                </div>
            </c:forEach>
        </c:if>
        <c:if test="${empty cartItems}">
            <p>Your cart is empty.</p>
        </c:if>

        <a href="${pageContext.request.contextPath}/product/list" class="btn btn-primary">Continue Shopping</a>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
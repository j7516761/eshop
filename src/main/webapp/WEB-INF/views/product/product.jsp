<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>電子商城 - 商品列表</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .card-img-custom {
            height: 200px;
            object-fit: cover;
        }
        .price-tag {
            font-size: 1.25rem;
            color: #dc3545;
        }
    </style>
</head>
<body class="bg-light">
<div class="container py-5">
    <h1 class="mb-4 text-center">熱門商品推薦</h1>
    
    <!-- 商品篩選表單 -->
    <div class="row mb-4">
        <div class="col-md-6">
            <form action="<s:url action='searchProducts'/>" method="get" class="input-group">
                <input type="text" name="keyword" class="form-control" placeholder="搜尋商品名稱..." 
                       value="<s:property value='keyword'/>">
                <button type="submit" class="btn btn-outline-primary">搜尋</button>
            </form>
        </div>
    </div>

    <!-- 商品列表 -->
    <div class="row row-cols-1 row-cols-md-3 g-4">
        <s:iterator value="products" status="stat">
            <div class="col">
                <div class="card h-100 shadow-sm">
                    <img src="<s:property value='imageURL'/>" 
                         class="card-img-top card-img-custom" 
                         alt="<s:property value='productName'/>">
                    <div class="card-body">
                        <h5 class="card-title"><s:property value="productName"/></h5>
                        <p class="card-text text-muted"><s:property value="description"/></p>
                        <div class="d-flex justify-content-between align-items-center">
                            <span class="price-tag">$<s:property value="price"/></span>
                            <s:if test="stock > 0">
                                <span class="badge bg-success">庫存充足</span>
                            </s:if>
                            <s:else>
                                <span class="badge bg-danger">缺貨中</span>
                            </s:else>
                        </div>
                    </div>
                    <div class="card-footer bg-transparent">
                        <a href="<s:url action='addToCart'><s:param name='productId' value='id'/></s:url>" 
                           class="btn btn-primary w-100">
                            <i class="bi bi-cart-plus"></i> 加入購物車
                        </a>
                    </div>
                </div>
            </div>
        </s:iterator>
    </div>

    <!-- 分頁導航 -->
    <nav class="mt-5" aria-label="Page navigation">
        <ul class="pagination justify-content-center">
            <!-- 上一頁 -->
            <li class="page-item <s:if test="currentPage <= 1">disabled</s:if>">
                <s:url var="prevPage" action="list">
                    <s:param name="currentPage" value="currentPage-1"/>
                    <s:param name="keyword" value="keyword"/>
                </s:url>
                <a class="page-link" href="<s:property value='#prevPage'/>" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            
            <!-- 頁碼 -->
            <s:iterator begin="1" end="totalPages" status="page">
                <li class="page-item <s:if test='currentPage == #page.count'>active</s:if>">
                    <s:url var="pageUrl" action="list">
                        <s:param name="currentPage" value="#page.count"/>
                        <s:param name="keyword" value="keyword"/>
                    </s:url>
                    <a class="page-link" href="<s:property value='#pageUrl'/>">
                        <s:property value="#page.count"/>
                    </a>
                </li>
            </s:iterator>
            
            <!-- 下一頁 -->
            <li class="page-item <s:if test="currentPage >= totalPages">disabled</s:if>">
                <s:url var="nextPage" action="list">
                    <s:param name="currentPage" value="currentPage+1"/>
                    <s:param name="keyword" value="keyword"/>
                </s:url>
                <a class="page-link" href="<s:property value='#nextPage'/>" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
    </nav>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
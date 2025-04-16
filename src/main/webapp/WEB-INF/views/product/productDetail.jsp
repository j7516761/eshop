<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <title><s:property value="product.name"/> - 商品詳情</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        /* 繼承product.jsp樣式 */
        .product-detail-container {
            max-width: 1200px;
            margin: 2rem auto;
            padding: 0 15px;
        }
        .detail-img {
            height: 400px;
            object-fit: contain;
            background: #f8f9fa;
            border-radius: 8px;
            padding: 1rem;
        }
        .specs-list {
            list-style: none;
            padding-left: 0;
        }
        .spec-item {
            padding: 0.5rem 1rem;
            background: #f8f9fa;
            margin-bottom: 0.5rem;
            border-radius: 4px;
        }
        .btn-cart {
            width: 100%;
            padding: 1rem;
            font-size: 1.2rem;
            transition: transform 0.2s;
        }
        .btn-cart:hover {
            transform: translateY(-2px);
        }
    </style>
</head>
<body>
    <div class="container-fluid">
        <!-- 麵包屑導航 -->
        <nav aria-label="breadcrumb" class="mt-3">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="<s:url action='listProducts'/>">商品列表</a></li>
                <li class="breadcrumb-item active" aria-current="page"><s:property value="product.name"/></li>
            </ol>
        </nav>

        <div class="product-detail-container">
            <div class="row g-4">
                <!-- 商品圖片區 -->
                <div class="col-md-6">
                    <img src="<s:property value='product.imageURL'/>" 
                         class="detail-img img-fluid" 
                         alt="<s:property value='product.name'/>">
                </div>

                <!-- 商品資訊區 -->
                <div class="col-md-6">
                    <div class="card h-100">
                        <div class="card-body">
                            <h1 class="card-title mb-4"><s:property value="product.name"/></h1>
                            
                            <div class="d-flex justify-content-between align-items-center mb-4">
                                <h3 class="text-danger">¥<s:property value="product.price"/></h3>
                                <span class="badge bg-success">
                                    庫存 <s:property value="product.stock"/> 件
                                </span>
                            </div>

                            <s:form action="addToCart" theme="simple">
                                <s:hidden name="productId" value="%{product.id}"/>
                                <button type="submit" class="btn btn-primary btn-cart">
                                    <i class="bi bi-cart-plus"></i> 加入購物車
                                </button>
                            </s:form>

                            <hr class="my-4">

                            <!-- 商品規格 -->
                            <h5 class="mb-3">商品規格</h5>
                            <ul class="specs-list">
                                <li class="spec-item">型號：<s:property value="product.model"/></li>
                                <li class="spec-item">顏色：<s:property value="product.color"/></li>
                                <li class="spec-item">重量：<s:property value="product.weight"/>g</li>
                            </ul>
                        </div>
                    </div>
                </div>

                <!-- 商品描述 -->
                <div class="col-12 mt-4">
                    <div class="card">
                        <div class="card-header">
                            <h5 class="mb-0">商品詳情</h5>
                        </div>
                        <div class="card-body">
                            <div class="product-description">
                                <s:property value="product.description"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- 共用腳本 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        // 圖片縮放功能
        document.querySelector('.detail-img').addEventListener('click', function() {
            this.classList.toggle('zoomed');
        });
    </script>
</body>
</html>
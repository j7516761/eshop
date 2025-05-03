<%@ page import="com.example.pojo.entity.User"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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

        /* 右下角語言選單的樣式 */
        .language-switcher {
            position: fixed;
            bottom: 20px;
            right: 20px;
            z-index: 1000; /* 確保在其他內容之上 */
        }
    </style>
</head>
<body class="bg-light">
    <!-- 新增頂部導航 -->
    <nav class="navbar navbar-expand-lg navbar-light navbar-custom">
        <div class="container-fluid">
            <!-- 將剩餘空間推到右邊 -->
            <div class="ms-auto d-flex align-items-center">
                <!-- 將按鈕放在右邊 -->
                <s:url var="loginUrl" value="/login.jsp" />
                <s:url var="cartUrl" value="/cart/viewCart" />
                <s:url var="logoutUrl" value="/logout" />
                <!-- 購物車區塊 -->
                <div class="cart-section me-3">
                    <s:if test="#session['session_user'] != null">
                        <span class="me-2"><s:text name="welcome.message" />, <s:property value="#session['session_user'].name" /></span>

                        <a href="${cartUrl}" class="btn btn-secondary btn-sm">
                            <i class="bi bi-cart"></i> <s:text name="cart.button" />
                        </a>
                        <!-- 新增登出按鈕 -->
                        <form action="${logoutUrl}" method="post" style="display: inline;">
                            <button type="submit" class="btn btn-danger btn-sm ms-2">
                                <s:text name="login.logout" />
                            </button>
                        </form>
                    </s:if>
                </div>

                <!-- 登入/註冊區塊 -->
                <div class="auth-section">
                    <s:if test="#session['session_user'] == null">
                        <a href="${loginUrl}" class="btn btn-primary btn-sm">
                            <i class="bi bi-box-arrow-in-right"></i> <s:text name="login.login" />
                        </a>
                    </s:if>
                </div>
            </div>
        </div>
    </nav>

    <!-- 語言切換下拉選單放在右下角 -->
    <div class="language-switcher dropdown">
        <button class="btn btn-secondary dropdown-toggle" type="button" id="languageDropdown" data-bs-toggle="dropdown" aria-expanded="false">
            <s:text name="language.select" />
        </button>
        <ul class="dropdown-menu" aria-labelledby="languageDropdown">
            <s:url var="zhLink" value="">
                <s:param name="request_locale">zh_CN</s:param>
            </s:url>
            <s:url var="enLink" value="">
                <s:param name="request_locale">en_US</s:param>
            </s:url>
            <li><a class="dropdown-item" href="${zhLink}"><s:text name="language.tw" /></a></li>
            <li><a class="dropdown-item" href="${enLink}"><s:text name="language.en" /></a></li>
        </ul>
    </div>

    <div class="container py-5">
        <h1 class="mb-4 text-center">
            <s:text name="product.hot" />
        </h1>

        <!-- 商品篩選表單 -->
        <div class="row mb-4">
            <div class="col-md-6">
                <form action="<s:url action='searchProducts'/>" method="get" class="input-group">
                    <input type="text" name="keyword" class="form-control" placeholder="搜尋商品名稱..." value="<s:property value='keyword'/>">
                    <button type="submit" class="btn btn-outline-primary"><s:text name="login.search" /></button>
                </form>
            </div>
        </div>

        <!-- 商品列表 -->
        <div class="row row-cols-1 row-cols-md-3 g-4">
            <s:iterator value="products" status="stat">
                <div class="col">
                    <div class="card h-100 shadow-sm">
   
                        <s:url value="/resources/images/products/%{imageUrl}" var="dynamicImg" />
                        <a href="<s:url action='detail' namespace='/product'><s:param name='productId' value='id'/></s:url>">
                            <img src="${dynamicImg}" alt="<s:property value='product.name' />" width="225" height="225">
                        </a>
   
                        <div class="card-body">
                            <h5 class="card-title">
                                <s:property value="name" />
                            </h5>
                            <p class="card-text text-muted">
                                <s:property value="description" />
                            </p>
                            <div class="d-flex justify-content-between align-items-center">
                                <span class="price-tag">
                                    $<fmt:formatNumber value="${price}" type="number" pattern="#,##0.00"/>
                                </span>
                                <s:if test="stock > 0">
                                    <span class="badge bg-success"><s:text name="product.sufficient" /></span>
                                </s:if>
                                <s:else>
                                    <span class="badge bg-danger"><s:text name="product.outOfStock" /></span>
                                </s:else>
                            </div>
                        </div>
                        <div class="card-footer bg-transparent">
                            <a href="javascript:void(0);" class="btn btn-primary w-100" onclick="addToCart('<s:url action='cart/addToCart'><s:param name='productId' value='id'/></s:url>')">
                                <i class="bi bi-cart-plus"></i>
                                <s:text name="product.addToCart" />
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
                        <s:param name="currentPage" value="currentPage-1" />
                        <s:param name="keyword" value="keyword" />
                    </s:url> 
                    <a class="page-link" href="<s:property value='#prevPage'/>" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>

                <!-- 頁碼 -->
                <s:iterator begin="1" end="totalPages" status="page">
                    <li class="page-item <s:if test='currentPage == #page.count'>active</s:if>">
                        <s:url var="pageUrl" action="list">
                            <s:param name="currentPage" value="#page.count" />
                            <s:param name="keyword" value="keyword" />
                        </s:url> 
                        <a class="page-link" href="<s:property value='#pageUrl'/>">
                            <s:property value="#page.count" />
                        </a>
                    </li>
                </s:iterator>

                <!-- 下一頁 -->
                <li class="page-item <s:if test="currentPage >= totalPages">disabled</s:if>">
                    <s:url var="nextPage" action="list">
                        <s:param name="currentPage" value="currentPage+1" />
                        <s:param name="keyword" value="keyword" />
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

    <!-- 添加模态框 -->
    <div class="modal fade" id="successModal" tabindex="-1" aria-labelledby="successModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-body text-center">
                    <p><s:text name="cart.addSuccess" /></p>
                </div>
            </div>
        </div>
    </div>

    <!-- 添加 JavaScript 脚本 -->
    <script>
        function addToCart(url) {
            const cartAddFailMessage = "<s:text name='cart.addFail' />";
            // 使用 Fetch API 或 AJAX 调用 addToCart URL
            fetch(url)
                .then(response => {
                    if (response.ok) {
                        // 显示成功模态框
                        const successModal = new bootstrap.Modal(document.getElementById('successModal'), {
                            backdrop: 'static',
                            keyboard: false
                        });
                        successModal.show();

                        // 1秒后关闭模态框
                        setTimeout(() => {
                            successModal.hide();
                        }, 1000);
                    } else {
                        console.error(cartAddFailMessage);
                    }
                })
                .catch(error => console.error('Fetch error:', error));
        }
    </script>
</body>
</html>
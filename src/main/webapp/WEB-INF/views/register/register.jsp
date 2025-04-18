<%@ page import="com.example.pojo.entity.User" contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
    // 獲取當前會話中的用戶物件
    User user = (User) session.getAttribute("user");
%>

<!DOCTYPE html>
<html>
<head>
    <title>用戶註冊 - 電子商城</title>
    <style>
        .error { color: red; }
        .form-group { margin-bottom: 15px; }
    </style>
    <script>
        function validateForm() {
            const password = document.getElementById("password").value;
            const confirmPassword = document.getElementById("confirmPassword").value;
            if (password !== confirmPassword) {
                alert("密碼與確認密碼不一致！");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
    <h2>用戶註冊</h2>
    <s:actionerror cssClass="error"/>
    <s:form action="save" method="post" onsubmit="return validateForm()">
        <div class="form-group">
            <s:textfield name="user.id" label="用戶名" required="true"/>
            <s:fielderror fieldName="user.id" cssClass="error"/>
        </div>
        <div class="form-group">
            <s:password name="user.password" label="密碼" required="true"/>
            <s:fielderror fieldName="user.password" cssClass="error"/>
        </div>
        <div class="form-group">
            <s:password name="confirmPassword" label="確認密碼" required="true"/>
        </div>
        <div class="form-group">
            <s:textfield name="user.email" label="電子郵件" required="true"/>
            <s:fielderror fieldName="user.email" cssClass="error"/>
        </div>
        <div class="form-group">
            <s:submit value="註冊" cssClass="btn-primary"/>
        </div>
    </s:form>
    <p>已有帳號？<a href="<s:url action='login'/>">立即登入</a></p>
</body>
</html>
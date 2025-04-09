<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
    <s:form action="register/register" method="post" onsubmit="return validateForm()">
        <div class="form-group">
            <s:textfield name="user.username" label="用戶名" required="true"/>
            <s:fielderror fieldName="user.username" cssClass="error"/>
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
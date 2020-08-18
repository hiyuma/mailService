<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="css/bootstrap.min.css" rel="stylesheet" />
<link href="css/style.css" rel="stylesheet" />
<title>備品管理</title>
</head>
<body id="loginPage">
<div class="container">
  <div class="row">
    <div class="col-md-offset-3 col-md-6">
      <div class="panel panel-default">
        <div class="panel-heading">備品管理システム ログイン</div>
        <div class="panel-body">
          <c:if test="${not empty error}">
          <div class="alert alert-danger">
            ログインIDかパスワードが正しくありません。
          </div>
          </c:if>
          <form action="" method="post">
            <div class="form-group">
              <c:if test="${not empty loginIdError}">
              <div class="error-message">
                <c:out value="${loginIdError}" />
              </div>
              </c:if>
              <input type="text" name="loginId" placeholder="ログインID" class="form-control" value="<c:out value="${loginId}" />" />
            </div>
            <div class="form-group">
              <c:if test="${not empty loginPassError}">
              <div class="error-message">
                <c:out value="${loginPassError}" />
              </div>
              </c:if>
              <input type="password" name="loginPass" placeholder="パスワード" class="form-control" />
            </div>
            <div class="form-group">
              <input type="submit" class="btn btn-primary btn-block" value="ログイン" />
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>
<script src="js/jquery-1.10.2.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>

<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="css/bootstrap.min.css" rel="stylesheet" />
<link href="css/style.css" rel="stylesheet" />
<title>備品管理</title>
</head>
<body>
<div class="container">
  <c:import url="parts/header.jsp" />
  <h1>備品登録</h1>
  <div class="row">
    <div class="col-md-12">
      <form action="" method="post">
        <div class="form-group">
          <label for="formName">品名（必須）</label>
          <c:if test="${not empty nameError}">
          <div class="error-message">
            <c:out value="${nameError}" />
          </div>
          </c:if>
          <input type="text" name="name" id="formName" class="form-control" value="<c:out value="${name}" />" />
        </div>
        <div class="form-group">
          <label for="formAmount">数量（必須）</label>
          <c:if test="${not empty amountError}">
          <div class="error-message">
            <c:out value="${amountError}" />
          </div>
          </c:if>
          <input type="text" name="amount" id="formAmount" class="form-control" value="<c:out value="${amount}" />" />
        </div>
        <div class="form-group">
          <label for="formLocation">場所（必須）</label>
          <select name="locationId" id="formLocation" class="form-control">
          <c:forEach items="${locationList}" var="location">
            <option value="<c:out value="${location.id}" />" <c:if test="${location.id == locationId}">selected</c:if>>
              <c:out value="${location.name}" />
            </option>
          </c:forEach>
          </select>
        </div>
        <div class="form-group">
          <label for="formNote">備考</label>
          <textarea name="note" id="formNote" class="form-control"><c:out value="${note}" /></textarea>
        </div>
        <div class="form-group">
          <input type="submit" class="btn btn-primary" value="登録" />
          <a href="listItem" class="btn btn-default">キャンセル</a>
        </div>
      </form>
    </div>
  </div>
</div>
<script src="js/jquery-1.10.2.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>

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
  <h1>備品リスト</h1>
  <div class="row">
    <div class="col-md-12">
      <table class="table table-bordered">
        <tr>
          <th>品名</th>
          <th>数量</th>
          <th>場所</th>
          <th>備考</th>
          <th>登録日</th>
          <th>更新日</th>
          <th>編集</th>
          <th>削除</th>
        </tr>
        <c:forEach items="${itemList}" var="item">
          <tr>
            <td><c:out value="${item.name}" /></td>
            <td><c:out value="${item.amount}" /></td>
            <td><c:out value="${item.locationName}" /></td>
            <td><c:out value="${item.note}" /></td>
            <td><c:out value="${item.registered}" /></td>
            <td><c:out value="${item.updated}" /></td>
            <td><a href="editItem?id=<c:out value="${item.id}" />" class="btn btn-default btn-sm">編集</a></td>
            <td><a href="deleteItem?id=<c:out value="${item.id}" />" class="btn btn-danger btn-sm">削除</a></td>
          </tr>
        </c:forEach>
      </table>
      <a href="addItem" class="btn btn-primary">備品の追加</a>
    </div>
  </div>
</div>
<script src="js/jquery-1.10.2.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>

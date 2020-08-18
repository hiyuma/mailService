<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<header>
  <nav class="navbar navbar-default">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse"
        data-target="#mynavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">備品管理システム</a>
    </div>
    <div class="collapse navbar-collapse" id="mynavbar">
      <ul class="nav navbar-nav navbar-right">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">
            <span class="glyphicon glyphicon-user"></span>
            <c:out value="${userName}" />
            <span class="caret"></span>
          </a>
          <ul class="dropdown-menu">
            <li><a href="logout">ログアウト</a></li>
          </ul>
        </li>
      </ul>
    </div>
  </nav>
</header>

<%@ page language="java" contentType="text/html; charset=UTF-16"
         pageEncoding="UTF-16"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal" var="principal"/>
</sec:authorize>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">Navbar</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <c:choose>
                <c:when test="${empty principal}">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="/auth/loginForm">로그인</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/auth/joinForm">회원가입</a>
                        </li>
                    </ul>
                </c:when>
                <c:otherwise>
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="/board/saveForm">글쓰기</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/user/updateForm">회원정보</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/logout">로그아웃</a>
                        </li>
                    </ul>
                </c:otherwise>
            </c:choose>
            <form class="d-flex">
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
        </div>
    </div>
</nav>
<c:forEach var="board" items="${boards.content}">
    <div class="card" style="width: 400px;">
        <div class="card-body">
            <h5 class="card-title">${board.title}</h5>
            <a href="/board/${board.id}" class="btn btn-primary">상세보기</a>
        </div>
    </div>
</c:forEach>

<ul class="pagination justify-content-center">
<%--    flex 정렬--%>
    <c:choose>
        <c:when test="${boards.first}">
            <li class="page-item disabled"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
        </c:when>
    <c:otherwise>
        <li class="page-item"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
    </c:otherwise>
    </c:choose>
<%--block 속성 화면 끝까지 다차지함--%>

    <c:choose>
        <c:when test="${boards.last}">
            <li class="page-item disabled"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
        </c:when>
        <c:otherwise>
            <li class="page-item"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
        </c:otherwise>
    </c:choose>
</ul>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</body>
</html>
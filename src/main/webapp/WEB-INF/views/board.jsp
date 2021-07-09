<%@ page import="com.hannahj.springBoard.domain.*"%>
<%@ page import="com.hannahj.springBoard.repository.*" %>
<%@ page import="com.hannahj.springBoard.service.*" %>

<%@ page import="java.util.*" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
  <meta charset="UTF-8">
  <c:set var='path' value='${pageContext.request.contextPath}'/>
  <link href="${path}/resources/post.css" rel="stylesheet" type="text/css">
  <title>게시판</title>
  <script
  src="https://code.jquery.com/jquery-3.6.0.min.js"
  integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
  crossorigin="anonymous"></script>
  <script>
  $(function(){
    $(".top").load("${path}/resources/top.jsp");
  })
  </script>
</head>
  <body>
  <div class="top"></div>
  <h1>${board.title}</h1>
    <table>
      <tr>
        <th style="width:10%">번호</th>
        <th style="width:50%">제목</th>
        <th style="width:15%">작성자</th>
        <th style="width:15%">작성일</th>
      </tr>
<%--       <c:set var='postPage' value="${postPage}"/> --%>
 <c:forEach var='post' items='${postPage.content}' varStatus="status">
      <tr class="post" onClick="location.href='${path}/post/${post.id}'">
<%--         <td>${status.index}</td> --%>
        <td>${postPage.totalElements - ( postPage.number ) * postPage.size - status.index}</td>
        <td>${post.title}</td>
      </tr>
    </c:forEach>
  </table>

    <button onClick="location.href='${path}/write?board=${board.id}'">글쓰기</button>
    
    
    
  <!-- 페이징 영역 시작 -->
  <div class="text-xs-center">
    <ul class="pagination justify-content-center">
      <!-- 이전 -->
      
      <c:choose>
        <c:when test="${postPage.first}"></c:when>
        <c:otherwise>
          <li class="page-item"><a class="page-link"
            href="${path}/board?id=${board.id}&page=1">처음</a></li>
          <li class="page-item"><a class="page-link"
            href="${path}/board?id=${board.id}&page=${postPage.number-1}">&larr;</a></li>
        </c:otherwise>
      </c:choose>
      <!-- 페이지 그룹 -->
      <c:forEach begin="${startBlockPage}" end="${endBlockPage}" var="i">
        <c:choose>
          <c:when test="${postPage.pageable.pageNumber+1 == i}">
            <li class="page-item disabled"><a class="page-link"
              href="${path}/board?id=${board.id}&page=${i+1}">${i}</a></li>
          </c:when>
          <c:otherwise>
            <li class="page-item"><a class="page-link"
              href="${path}/board?id=${board.id}&page=${i}">${i}</a></li>
          </c:otherwise>
        </c:choose>
      </c:forEach>
      <!-- 다음 -->
      <c:choose>
        <c:when test="${postPage.last}">
        </c:when>
        <c:otherwise>
          <li class="page-item "><a class="page-link"
            href="${path}/board?id=${board.id}&page=${postPage.number+2}">&rarr;</a></li>
          <li class="page-item "><a class="page-link"
            href="${path}/board?id=${board.id}&page=${postPage.totalPages}">마지막</a></li>
        </c:otherwise>
      </c:choose>
    </ul>
  </div>
  <!-- 페이징 영역 끝 -->

  </body>
</html>
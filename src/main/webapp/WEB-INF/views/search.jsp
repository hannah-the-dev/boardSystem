<%@ page import="com.hannahj.springBoard.domain.*"%>
<%@ page import="com.hannahj.springBoard.repository.*"%>
<%@ page import="com.hannahj.springBoard.service.*"%>

<%@ page import="java.util.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
  <meta charset="UTF-8">
  <c:set var='path' value='${pageContext.request.contextPath}' />
  <link href="${path}/resources/post.css" rel="stylesheet" type="text/css">
  <title>${keyword} 검색결과</title>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"
  integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"
></script>
<script>
  $(function() {
    $(".top").load("${path}/resources/top.jsp");
  })
</script>
</head>
  <body>
  <nav class="top"></nav>
  <div class="title">
  <h1>"${keyword}" 검색 결과</h1></div>
    <table>
      <tr>
        <th style="width:15%">게시판 이름</th>
        <th style="width:10%">번호</th>
        <th style="width:45%">제목</th>
        <th style="width:15%">작성자</th>
        <th style="width:15%">작성일</th>
      </tr>
 <c:forEach var='post' items='${result.content}' varStatus="status">
      <tr class="post" onClick="location.href='${path}/post/${post.id}'">
<%--         <td>${status.index}</td> --%>
        <td>${post.board.id}</td>
        <td>${result.totalElements - ( result.number ) * result.size - status.index}</td>
        <td>${post.title}</td>
        <td>${post.username}</td>
        <fmt:parseDate value="${ post.createdDate }" pattern="yyyy-MM-dd'T'HH:mm:ss" var="created" type="both" />
        <td> <fmt:formatDate value="${created}" pattern="yyyy-MM-dd"/></td>
        
      </tr>
    </c:forEach>    
    </table>

  <!-- 페이징 영역 시작 -->
  <div class="text-xs-center">
    <ul class="pagination justify-content-center">
      <!-- 이전 -->
      
      <c:choose>
        <c:when test="${result.first}"></c:when>
        <c:otherwise>
          <li class="page-item"><a class="page-link"
            href="${path}/search?keywords=${keyword}&page=1">처음</a></li>
          <li class="page-item"><a class="page-link"
            href="${path}/search?keywords=${keyword}&page=${result.number-1}">&larr;</a></li>
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
              href="${path}/search?keywords=${keyword}&page=${i}">${i}</a></li>
          </c:otherwise>
        </c:choose>
      </c:forEach>
      <!-- 다음 -->
      <c:choose>
        <c:when test="${result.last}">
        </c:when>
        <c:otherwise>
          <li class="page-item "><a class="page-link"
            href="${path}/search?keywords=${keyword}&page=${result.number+2}">&rarr;</a></li>
          <li class="page-item "><a class="page-link"
            href="${path}/search?keywords=${keyword}&page=${result.totalPages}">마지막</a></li>
        </c:otherwise>
      </c:choose>
    </ul>
  </div>
  <!-- 페이징 영역 끝 -->
  </body>
</html>
<%@ page import="com.hannahj.springBoard.domain.*"%>
<%@ page import="com.hannahj.springBoard.repository.*"%>
<%@ page import="com.hannahj.springBoard.service.*"%>

<%@ page import="java.util.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:set var='path' value='${pageContext.request.contextPath}' />
<link href="${path}/resources/post.css" rel="stylesheet" type="text/css">
<title>게시판 모음</title>
<!--   우선 JSP로 만들고 Thymeleaf로 바꾸자 -->
</head>
<body>
  <h1>게시판 모음</h1>
  <table>
    <tr>
      <th>게시판 번호</th>
      <th style="width: 80%">게시판 이름</th>
    </tr>
    <c:forEach var='board' items='${boardPage.content}'>
<%--       <tr class="post" onClick="location.href='/board?id=${board.id}'"> --%>
      <tr class="post" onClick="location.href='${path}/board?id=${board.id}'">
        <td><div class="numbers">${board.id}</div></td>
        <td>${board.title}</td>
      </tr>
    </c:forEach>
  </table>
  <!-- 페이징 영역 시작 -->
  <div class="text-xs-center">
    <ul class="pagination justify-content-center">
      <!-- 이전 -->
      
      <c:choose>
        <c:when test="${boardPage.first}"></c:when>
        <c:otherwise>
          <li class="page-item"><a class="page-link"
            href="${path}/?page=1">처음</a></li>
          <li class="page-item"><a class="page-link"
            href="${path}/?page=${boardPage.number-1}">&larr;</a></li>
        </c:otherwise>
      </c:choose>
      <!-- 페이지 그룹 -->
      <c:forEach begin="${startBlockPage}" end="${endBlockPage}" var="i">
        <c:choose>
          <c:when test="${boardPage.pageable.pageNumber+1 == i}">
            <li class="page-item disabled"><a class="page-link"
              href="${path}/?page=${i+1}">${i}</a></li>
          </c:when>
          <c:otherwise>
            <li class="page-item"><a class="page-link"
              href="${path}/?page=${i}">${i}</a></li>
          </c:otherwise>
        </c:choose>
      </c:forEach>
      <!-- 다음 -->
      <c:choose>
        <c:when test="${boardPage.last}">
        </c:when>
        <c:otherwise>
          <li class="page-item "><a class="page-link"
            href="${path}/?page=${boardPage.number+2}">&rarr;</a></li>
          <li class="page-item "><a class="page-link"
            href="${path}/?page=${boardPage.totalPages}">마지막</a></li>
        </c:otherwise>
      </c:choose>
    </ul>
  </div>
  <!-- 페이징 영역 끝 -->

</body>
</html>
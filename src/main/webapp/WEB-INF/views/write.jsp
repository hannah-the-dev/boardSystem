<%@ page import="com.hannahj.springBoard.domain.*"%>
<%@ page import="com.hannahj.springBoard.repository.*"%>
<%@ page import="com.hannahj.springBoard.service.*"%>

<%@ page import="java.util.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <c:set var='path' value='${pageContext.request.contextPath}' />
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
  <nav class="top"></nav>
  <div class="main">
  <div class="title">
  <h1>글 작성</h1></div>
   <form method=post>
    <table>
      <tr>
        <th style="width:10%">게시판</th>
        <td> <select name="board" >
            <option value="" selected>게시판을 선택하세요</option>
          <c:forEach var='board' items='${boards}'>
              <option value='${board.id}'
                <c:if test="${board.id eq id}">
                  selected </c:if>
                 
              class="selectable"> ${board.title}</option>
          </c:forEach>
          </select>
      </tr>
      <tr>  
        <th style="width:10%">글 번호</th>
        <td> 새 글 </td>
      </tr>
      <tr>
        <th>글 제목</th>
        <td><input type="text"  name="title" required></td>
      </tr>
      <tr>
        <th>작성자</th>
        <td> <input type="text" name="username" class='editable' required></td>
      </tr>
      <tr>
        <th>작성일</th>
        <td> <jsp:useBean id="now" class="java.util.Date"/><fmt:formatDate value="${now}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
      </tr>
      <tr style="vertical-align: top">
        <th>내용</th>
        <td height="auto"> <textarea name="content" class='editable' required></textarea></td>
      </tr>
    </table>
    <input type="button" class="button" onClick='location.href="${path}/board?id=${post.board.id}"' value="목록">
    <input type="reset" class="button">
    <input type="submit" class="button" formaction=${path}/post/writer>
  </form>
  </div>
  </body>
</html>
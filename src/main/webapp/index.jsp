<%@ page import="com.hannahj.bbs.domain.*"%>
<%@ page import="com.hannahj.bbs.dao.*" %>
<%@ page import="com.hannahj.bbs.service.*" %>

<%@ page import="java.util.*" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
  <meta charset="UTF-8">
  <link href="post.css" rel="stylesheet" type="text/css">
  <title>게시판 모음</title>
</head>
  <body>
  <h1>게시판 모음</h1>
    <table>
      <tr>
        <th style="width:10%">게시판 번호</th>
        <th style="width:50%">게시판 이름</th>
      </tr>
    <%
    BoardInfo[] boards = BoardInfo.values();
    for (BoardInfo board: boards) { %>
      <tr class="post" onClick="location.href='board.jsp?board=<%= board.idx %>'">
        <td> <%= board.idx %></td>
        <td> <%= board.alias %></td>
      </tr>      
    <% 
    }     
    %>
    </table>
  </body>
</html>
<%@ page import="com.hannahj.bbs.domain.*"%>
<%@ page import="com.hannahj.bbs.dao.*" %>
<%@ page import="com.hannahj.bbs.service.*" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%@ page import="java.util.*" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
  <meta charset="UTF-8">
  <link href="post.css" rel="stylesheet" type="text/css">
  <title>게시판</title>
</head>
  <body>
    <%
    request.setCharacterEncoding("UTF-8");
    int boardIdx = Integer.parseInt(request.getParameter("board"));
    int idx = Integer.parseInt(request.getParameter("idx"));
//     String userName = request.getParameter("userName");
//     String title = request.getParameter("title");
//     String content = request.getParameter("content");
    int parentIdx = 0;
    if (request.getParameter("parentIdx") != null ) {
      parentIdx = Integer.parseInt(request.getParameter("parentIdx"));
    }

    PostServiceImpl selected = new PostServiceImpl();
    selected.delete(boardIdx, idx);
    
    %>
    <script>
      <% if (request.getParameter("parentIdx") != null) {
        %>
        // 댓글 삭제 후에는 원글로 이등
      location.href="read.jsp?board=<%= boardIdx %>&idx=<%= parentIdx%>"
      <%
      } else {
        %>
        // 게시글 삭제 후에는 작성한 게시판으로 이동
      location.href="board.jsp?board=<%= boardIdx %>"
      <%
      }
      %>
    </script>
  </body>
</html>
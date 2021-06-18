<%@ page import="com.hannahj.noticeBoard.domain.Post"%>
<%@ page import="com.hannahj.noticeBoard.dao.PostDaoImpl" %>
<%@ page import="java.util.*" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
</head>
  <body>
    <%
    PostDaoImpl notice = new PostDaoImpl();
                    List<Post> boards = notice.list();
                    for (Post board : boards) {
                     out.println(board.getIdx());
                     out.println("<br>");
                    }
                //     NoticeBoard board = new NoticeBoard();
                //     board.setUserName("이름");
                //     board.setPw("비번");
                //     board.setTitle("제목");
                //     board.setContent("컨텐츠");
                    
                //     notice.write(board);
    %>
  </body>
</html>
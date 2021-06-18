<%@ page import="com.hannahj.noticeBoard.domain.*"%>
<%@ page import="com.hannahj.noticeBoard.dao.*" %>
<%@ page import="com.hannahj.noticeBoard.service.*" %>

<%@ page import="java.util.*" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
  <meta charset="UTF-8">
  <style>
    tr,
    td,
    th {
      border: 1px solid black;
      padding: 5px 10px 5px 10px;
    }
    
    tr.post:hover {
      cursor: pointer;
    }

    table {
      border-collapse: collapse;
	  margin: 10px auto;
      width: 800px;
    }
  </style>
  <title>Insert title here</title>
</head>
  <body>
  <h1><%=BoardInfo.NOTICE.alias %></h1>
    <table>
      <tr>
        <th style="width:10%"><%=PostColumns.IDX.alias %></th>
        <th style="width:50%"><%=PostColumns.TITLE.alias %></th>
        <th style="width:15%"><%=PostColumns.USER_NAME.alias %></th>
        <th style="width:15%"><%=PostColumns.DATETIME.alias %></th>
      </tr>
    <%
    PostServiceImpl notice = new PostServiceImpl();
    List<Post> posts = notice.list();
    for (Post post : posts) { %>
      <tr class="post" onClick="location.href='read.jsp?idx=<%= post.getIdx() %>'">
        <td> <%= post.getIdx() %></td>
        <td> <%= post.getTitle() %></td>
        <td> <%= post.getUserName() %></td>
        <td> <%= post.getDateTime() %></td>
      </tr>      
    <% 
    }     
    %>
    </table>
  </body>
</html>
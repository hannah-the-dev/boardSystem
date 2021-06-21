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

  <title>게시판</title>
</head>
  <body>
    <%
    request.setCharacterEncoding("UTF-8");
    int boardIdx = Integer.parseInt(request.getParameter("board"));
    %>
  <h1><%=BoardInfo.getBoardInfo(boardIdx).alias%></h1>
  <form >
    <div class="search" style="width:500px"><input type="text" name="keyword" placeholder="여기에서 검색하세요"></div>
    <div class="search"><input type="submit" formaction="search.jsp"></div>
  </form>
    <table>
      <tr>
        <th style="width:10%"><%=PostColumns.IDX.alias %></th>
        <th style="width:50%"><%=PostColumns.TITLE.alias %></th>
        <th style="width:15%"><%=PostColumns.USER_NAME.alias %></th>
        <th style="width:15%"><%=PostColumns.DATETIME.alias %></th>
      </tr>
    <%
    PostServiceImpl notice = new PostServiceImpl();
        List<Post> posts = notice.getList(boardIdx);
        for (Post post : posts) {
    %>
      <tr class="post" onClick=
      "location.href='read.jsp?board=<%=post.getBoardIdx()%>&idx=<%= post.getIdx() %>'">
        <td> <%= post.getIdx() %></td>
        <td> <%= post.getTitle() %></td>
        <td> <%= post.getUserName() %></td>
        <td> <%= post.getDateTime() %></td>
      </tr>      
    <% 
    }     
    %>
    </table>
    <button onClick="location.href='write.jsp?board=<%=boardIdx%>'">글쓰기</button>
  </body>
</html>
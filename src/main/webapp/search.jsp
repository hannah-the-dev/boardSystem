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
    String keyword = null;
    int pager = 1;
    try {
        keyword = request.getParameter("keyword");
        pager = (request.getParameter("page") == null) ? 1 : Integer.parseInt(request.getParameter("page"));
    } catch (Exception e) {
    	out.println("<html><body><h1>잘못된 접근입니다.</h1></body></html>");
        return;
    }
    %>
  <h1>"<%=keyword %>" 검색 결과</h1>
    <table>
      <tr>
        <th style="width:15%"><%=PostColumns.BOARD_IDX.alias %></th>
        <th style="width:10%"><%=PostColumns.IDX.alias %></th>
        <th style="width:45%"><%=PostColumns.TITLE.alias %></th>
        <th style="width:15%"><%=PostColumns.USER_NAME.alias %></th>
        <th style="width:15%"><%=PostColumns.DATETIME.alias %></th>
      </tr>
    <%
    PostServiceImpl search = new PostServiceImpl();
        List<Post> posts = search.search(keyword, pager);
        String regexp = search.getRegExp(keyword);
        int startIdx = search.getSearchStartIdx(pager, regexp);
        int startPage = ((startIdx -1) / BoardItemDaoImpl.BOARDSIZE) + 1;
        int maxPage = search.getSearchCount(regexp) / BoardItemDaoImpl.LISTSIZE +1;
        int endPage = (startPage * BoardItemDaoImpl.LISTSIZE > maxPage) ? 
            maxPage : startPage * BoardItemDaoImpl.LISTSIZE +1;
        for (Post post : posts) {
    %>
      <tr class="post" onClick=
      "location.href='read.jsp?board=<%=post.getBoardIdx()%>&idx=<%= post.getIdx() %>'">
        <td> <%= BoardInfo.getBoardInfo(post.getBoardIdx()).alias %></td>
        <td> <%= post.getIdx() %></td>
        <td> <%= post.getTitle() %></td>
        <td> <%= post.getUserName() %></td>
        <td> <%= post.getDateTime() %></td>
      </tr>      
    <% 
    }     
    %>
    </table>
    <ul>
      <%     
      if(startPage > 1) { 
      %>
      <li> <a href="search.jsp?keyword='<%=keyword %>'&page=
      <%= startPage -1%>"> &#12298; </a> 
        </li>
      <% }
      for (int i = startPage; i <= endPage; i++) { %>
      <li> <a href="search.jsp?keyword=<%=keyword %>&page=<%= i %>"><%= i %></a> </li> 
      <% } 
       if (maxPage != endPage) {%>
      <li> <a href="search.jsp?keyword=<%=keyword %>&page=<%=endPage%>"> &#12299; </a> </li>
       <% }%>
    </ul>
  </body>
</html>
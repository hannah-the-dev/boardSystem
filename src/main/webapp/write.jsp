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
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    request.setCharacterEncoding("UTF-8");
    int boardIdx = Integer.parseInt(request.getParameter("board"));
        
    PostServiceImpl newPost = new PostServiceImpl();
//         int nextId = newPost.getNextId();
    %>
    
  <h1>글 작성</h1>
  <form method="post">
    <table>
      <tr>
        <th style="width:10%">게시판</th>
        <td> 
        <select name="board" required>
          <option value="">게시판을 선택하세요</option>
          <%
          BoardInfo[] boards = BoardInfo.values();
          for (BoardInfo board: boards) { %>
            <option value="<%=board.idx %>" 
            <% if(board.idx ==boardIdx ) {
              out.println("selected"); 
            }%>><%= board.alias %></option>      
           <% } %>
        </select>
        </td>
      </tr>
      <tr>
        <th><%=PostColumns.IDX.alias %></th>
<%--         <td> <input type="number" value="<%= nextId %>" readonly> </td> --%>
        <td>새 글</td>
      </tr>
      <tr>
        <th><%=PostColumns.TITLE.alias %></th>
        <td> <input type="text"  name="title" required> </td>
      </tr>
      <tr>
        <th><%=PostColumns.USER_NAME.alias %></th>
        <td> <input type="text" name="userName" required></td>
      </tr>
      <tr>
        <th><%= PostColumns.DATETIME.alias %></th>
        <td><%= sdf.format(cal.getTime()) %></td>
      </tr>
      <tr style="height:500px; vertical-align: top">
        <th><%=PostColumns.CONTENT.alias %></th>
        <td> <textarea name='content' cols=95 rows=50 wrap="hard" required></textarea> </td>
      </tr>
    </table>
        <input type="hidden" name=board value=<%=boardIdx %>>
        <button formaction="board.jsp">취소</button>
        <input type="submit" value="쓰기" formaction="register.jsp"></input>
    </form>

  </body>
</html>
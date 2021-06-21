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
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    
    PostServiceImpl notice = new PostServiceImpl();
    Post post = notice.read(boardIdx, idx);
    %>
        
  <h1><%=idx%> 번 게시글</h1>
  <form>
    <table>
      <tr>
        <th style="width:10%"><%=PostColumns.BOARD_IDX.alias %></th>
        <td> <select name="board" >
            <option value="" disabled='disabled'>게시판을 선택하세요</option>
            <%
            BoardInfo[] boards = BoardInfo.values();
            for (BoardInfo board : boards) {
            %>
            <option value="<%=board.idx%>"
              <%if (board.idx == boardIdx) {
	             out.println("selected");
              } else { %>
        	  <%="disabled='disabled'"%>
         <%
              }%>>   <%=board.alias%></option>
            <%
            }
            %>
          </select>
      </tr>
      <tr>
        <th style="width:10%"><%=PostColumns.IDX.alias %></th>
        <td> <input type="text" name="idx" value=<%= post.getIdx() %> readonly> </td>
      </tr>
      <tr>
        <th><%=PostColumns.TITLE.alias %></th>
        <td> <input type="text" name="title" value=<%= notice.getClean(post.getTitle()) %> readonly></td>
      </tr>
      <tr>
        <th><%=PostColumns.USER_NAME.alias %></th>
        <td> <input type="text" name="userName" value=<%= notice.getClean(post.getUserName()) %> readonly></td>
      </tr>
      <tr>
        <th><%=PostColumns.DATETIME.alias %></th>
        <td> <input type="text" name="date" value=<%= post.getDateTime() %> readonly></td>
      </tr>
      <tr style="vertical-align: top">
        <th><%=PostColumns.CONTENT.alias %></th>
        <td height="60px"> <textarea name="content" readonly><%= notice.getClean(post.getContent()) %></textarea></td>
      </tr>
    </table>
    <button formaction="board.jsp">목록</button>
    <button formaction="edit.jsp">수정</button>
  </form>
    
  <h3>댓글</h3>
  <form>
    <table>
      <% List<Post> comments = post.getComment();
      if (comments.size() == 0) {
       out.println("<p>등록된 댓글이 없어요! 이 글에 첫번째 댓글을 달아보세요.</p>"); 
      } else {
        for (Post comment : comments) { %>
<!--     <tr> -->
<%--         <th style="width:10%"><%=PostColumns.IDX.alias %></th> --%>
<%--         <td> <%= post.getIdx() %></td> --%>
<!--       </tr> -->
      <tr>
        <th><%=PostColumns.TITLE.alias %></th>
        <td> <%= notice.getClean(comment.getTitle()) %></td>
      </tr>
      <tr>
        <th><%=PostColumns.USER_NAME.alias %></th>
        <td> <%= notice.getClean(comment.getUserName()) %></td>
      </tr>
      <tr>
        <th><%=PostColumns.DATETIME.alias %></th>
        <td> <%= comment.getDateTime() %></td>
      </tr>
      <tr>
        <th><%=PostColumns.CONTENT.alias %></th>
        <td> <%= notice.getClean(comment.getContent()) %></td>
      </tr>
      <tr class="sep"><td class="sep" ></td><td class="sep" ></td></tr>
      
    <% 
        }         
    }
    %>
<!--  새 댓글 등록 -->
      <tr class="sep">
        <td colspan=2 class="sep" height="30px">새 댓글 작성</td>
      </tr>
      <tr>
        <th><%=PostColumns.TITLE.alias %></th>
        <td> <input type="text" name="title"> </td>
      </tr>
      <tr>
        <th><%=PostColumns.USER_NAME.alias %></th>
        <td> <input type="text" name="userName"></td>
      </tr>
      <tr>
        <th><%= PostColumns.DATETIME.alias %></th>
        <td><%= sdf.format(cal.getTime()) %></td>
      </tr>
      <tr style="height:100px; vertical-align: top">
        <th><%=PostColumns.CONTENT.alias %></th>
        <td> <textarea name='content' cols=95 rows=5 wrap="hard"></textarea> </td>
      </tr>
    </table>
        <input type="hidden" name=board value="<%=post.getBoardIdx()%>">
        <input type="hidden" name="parentIdx" value="<%= post.getIdx() %>">
        <button formaction="board.jsp">취소</button>
        <input type="submit" value="댓글쓰기" formaction="register.jsp"></input>
    </form>
  </body>
</html>
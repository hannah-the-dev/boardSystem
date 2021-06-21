<%@ page import="com.hannahj.bbs.domain.*"%>
<%@ page import="com.hannahj.bbs.dao.*"%>
<%@ page import="com.hannahj.bbs.service.*"%>
<%@ page import="java.text.SimpleDateFormat"%>

<%@ page import="java.util.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
  PostServiceImpl edited = new PostServiceImpl();
  request.setCharacterEncoding("UTF-8");
  int boardIdx = Integer.parseInt(request.getParameter("board"));
  int idx = Integer.parseInt(request.getParameter("idx"));
  String userName = edited.getClean(request.getParameter("userName"));
  String title = edited.getClean(request.getParameter("title"));
  String content = edited.getClean(request.getParameter("content"));
  
  
//   int thisId = newPost.getNextId();
//   Post post = new Post(boardIdx, idx, userName, title, content);
//   int parentIdx = 0;
//   if (request.getParameter("parentIdx") != null) {
//   	parentIdx = Integer.parseInt(request.getParameter("parentIdx"));
//   	post.setParentIdx(parentIdx);
//   }
//   edited.write(post);
  
  %>
  <h1><%=idx%>
    번 게시글 수정
  </h1>
  <form>
    <table>
      <tr>
        <th style="width: 10%"><%=PostColumns.BOARD_IDX.alias%></th>
        <td>
          <select name="board">
            <option value="">게시판을 선택하세요</option>
            <%
            BoardInfo[] boards = BoardInfo.values();
            for (BoardInfo board : boards) {
            %>
            <option value="<%=board.idx%>"
              <%if (board.idx == boardIdx) {
	             out.println("selected");
              } else { %>
            	  <%="disabled='disabled'"%>
              <% }
              %>>   <%=board.alias%></option>
            <%
            }
            %>
          </select>
      </tr>
      <tr>
        <th style="width: 10%"><%=PostColumns.IDX.alias%></th>
        <td>
          <input type="text" name="idx" value=<%=idx%> readonly>
        </td>
      </tr>
      <tr>
        <th><%=PostColumns.TITLE.alias%></th>
        <td>
          <input type="text" name="title" value=<%=title%>>
        </td>
      </tr>
      <tr>
        <th><%=PostColumns.USER_NAME.alias%></th>
        <td>
          <input type="text" name="userName" value=<%=userName%>>
        </td>
      </tr>
      <tr>
        <th><%=PostColumns.DATETIME.alias%></th>
        <td>
          <input type="text" value=<%=sdf.format(cal.getTime())%>>
        </td>
      </tr>
      <tr style="vertical-align: top">
        <th><%=PostColumns.CONTENT.alias%></th>
        <td height="500px">
          <textarea name="content"><%=content%></textarea>
        </td>
      </tr>
    </table>
    <button formaction="board.jsp">취소</button>
    <button formaction="update.jsp">업데이트</button>
    <button formaction="delete.jsp">삭제</button>
  </form>

</body>
</html>
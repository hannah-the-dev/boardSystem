<%@ page import="com.hannahj.springBoard.domain.*"%>
<%@ page import="com.hannahj.springBoard.repository.*"%>
<%@ page import="com.hannahj.springBoard.service.*"%>

<%@ page import="java.util.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
  <meta charset="UTF-8">
  <c:set var='path' value='${pageContext.request.contextPath}' />
  <link href="${path}/resources/post.css" rel="stylesheet" type="text/css">
  <title>${board.title} 게시판</title>
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
    <c:choose>
      <c:when test="${post.board eq null}"><h1 class="warning">해당 게시글이 존재하지 않아요!</h1>
      <button onclick="history.back(-1);" class="button">뒤로 가기</button> 
      </c:when>
    <c:otherwise><div class="title"><h1>${post.id}번 게시글</h1></div>
    <form>
      <table>
        <tr>
          <th style="width:10%">게시판</th>
          <td> <select name="board" >
              <option value="" disabled='disabled'>게시판을 선택하세요</option>
            <c:forEach var='board' items='${boards}'>
                <option value='${board.id}'
                <c:choose>
                  <c:when test="${board.id eq post.board.id}">
                    "selected" </c:when>
                   <c:otherwise> disabled="disabled" </c:otherwise>
                   </c:choose>
                class="selectable"> ${board.title}</option>
            </c:forEach>
            </select>
        </tr>
        <tr>  
          <th>글 번호</th>
          <td> <input type="text" name="id" value='${post.id}' readonly> </td>
        </tr>
        <tr>
          <th>글 제목</th>
          <td> <input type="text" name="title" class='editable' value='${post.title}' readonly></td>
        </tr>
        <tr>
          <th>작성자</th>
          <td> <input type="text" name="username" class='editable' value='${post.username}' readonly></td>
        </tr>
        <tr>
          <th>작성일</th>
          <fmt:parseDate value="${ post.createdDate }" pattern="yyyy-MM-dd'T'HH:mm:ss" var="created" type="both" />
          <fmt:parseDate value="${ post.modifiedDate }" pattern="yyyy-MM-dd'T'HH:mm:ss" var="edited" type="both" />
          <td> <fmt:formatDate value="${created}" pattern="yyyy-MM-dd hh:mm:ss"/> 
          <c:if test="${post.modifiedDate ne post.createdDate}"> (수정: <fmt:formatDate value="${edited}" pattern="yyyy-MM-dd hh:mm:ss"/> )</c:if>
          </td>
        </tr>
        <tr style="vertical-align: top">
          <th>내용</th>
          <td height="auto"> <textarea name="content" class='editable' readonly>${post.content}</textarea></td>
        </tr>
      </table>
      <input type="button" class="button" onClick='location.href="${path}/board?id=${post.board.id}"' value="목록">
      <input type="button" class="button" onClick='editable(this);' value="수정">
      <button type="submit" class="button" id="save" formmethod="post" formaction="${path}/post/edit" style="display:none">저장</button>
      <button type="submit" class="button" id="delete" formmethod="post" formaction="${path}/post/delete" style="display:none">삭제</button>
    </form>
      
    <h3>댓글(${post.commentSize})</h3>
  
  <!--     <table class="comments"> -->
  <!--       <tr> -->
  <!--         <th>작성자</th> -->
  <!--         <th>작성시간</th> -->
  <!--         <th>내용</th> -->
  <!--       </tr> -->
      <div class=comments style="width:800px">
      <dl class=exist>
      <dt>등록된 댓글</dt>
      
      <c:choose>
        <c:when test="${empty comments.content}">
          <dd>등록된 댓글이 없어요! 이 글에 첫번째 댓글을 달아보세요.</dd> 
        </c:when>
        <c:otherwise>
        
        <c:forEach var='comment' items='${comments.content}'>
        <dd>
          <div style="margin: 1em;">
            <div style="display: inline-block">${comment.username}</div>
            <div style="display: inline-block">(${comment.createdDate})
            <c:if test="${comment.modifiedDate ne comment.createdDate}"> (수정: ${comment.modifiedDate})</c:if></div>
            <div>${comment.content}</div>
          </div>
        </dd>
        </c:forEach>
        </c:otherwise>
      </c:choose>
  <!--     </table> -->
      </dl>
        <form>
      <div class="new">
      <dl>
        <dt>새 댓글 등록</dt>
    <!--       <tr> -->
    <!--         <th>작성자</th> -->
    <!--         <th>작성시간</th> -->
    <!--         <th>내용</th> -->
    <!--       </tr> -->
        <dd>
    <%--         <th><%=PostColumns.USER_NAME.alias %></th> --%>
            <div> <input type="text" name="username" required placeholder="이름">
            <input type="hidden" name="board" value="${post.board.id}">
            <input type="hidden" name="parentId" value="${post.id}">
            </div>
            <div>현재시간</div>
            <div> 
            <textarea name='content' cols=auto rows=auto wrap="hard" required placeholder="댓글 내용을 입력해 주세요"></textarea> 
            </div>
        </dd>
      </dl>
          <input type="reset" class="button">
          <input type="submit" class="button" value="댓글쓰기" formaction="${path}/post/writer" formmethod="post"></input>
      </div>
      </form>
      </div>
      
      </c:otherwise>
    </c:choose>
    </div>
    <script type="text/javascript">
	function editable(btn) {
    $('.editable').removeAttr('readonly');
    $('.selectable').removeAttr('disabled');
    $('#save').show();
    $('#delete').show();
    $(btn).attr('disabled', true);
	}
    </script>
  </body>
</html>
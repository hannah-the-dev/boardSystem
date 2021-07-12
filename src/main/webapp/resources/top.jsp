<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:set var='path' value='${pageContext.request.contextPath}' />
<link href="${path}/resources/top.css" rel="stylesheet" type="text/css">
<link href="${path}/resources/post.css" rel="stylesheet" type="text/css">


</head>
<body>
  <div id="header" style="display: flex">
    <div id="logo" onClick="location.href='/springBoard/'" style="
    font-family: 'Fredericka the Great', cursive;">Herstories</div>
<!--     <div onClick="location.href='/springBoard/'">Home</div> -->
    <div class="search">
      <form>
        <div id="searchbox">
          <input type="text" name="keywords" placeholder="여기에서 검색하세요" style="width: 80%" required>
        </div>
        <div id="searchbutton">
          <input type="submit" class="headerbutton" formaction="search" value="검색">
        </div>
      </form>
    </div>
    <div onClick="location.href='/springBoard/'">로그인</div>

  </div>
  
</body>
</html>
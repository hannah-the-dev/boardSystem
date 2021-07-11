<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
/* https://coolors.co/233d4d-994636-cdcdcd-e6ebe0 */

body {
margin: 0;
padding: 0;

}


#header, .search {
  display: flex;
  flex-direction: row;
  align-items: center;
}
#header div:not(.search) {
  display: inline-block;
}

#header > div:not(.search) {
  padding: .5rem 1em;
}

#header {
  background-color: #233d4d;
  color: #CDCDCD;
  position: fixed;
  width: 100%;
  position: fixed;
  top: 0;
}

#searchbox input[type='text'] {
  border-radius: .7em;
  margin: .5rem;
  padding: 0 1rem;
}

#searchbox {
  margin: 0 1rem;
}

.headerbutton {
  color: #cdcdcd;
  background-color: #233D4D;
}

</style>
</head>
<body>
  <div id="header" style="display: flex">
    <div>Home</div>
    <div class="search">
      <form>
        <div id="searchbox">
          <input type="text" name="keyword" placeholder="여기에서 검색하세요" style="width: 80%" required>
        </div>
        <div id="searchbutton">
          <input type="submit" class="headerbutton" formaction="search.jsp">
        </div>
      </form>
    </div>
    <div>Home</div>
  </div>
</body>
</html>
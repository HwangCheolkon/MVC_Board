<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String id = (String)session.getAttribute("id");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>

<style>
	table {
		text-align: center;
	}
	
	#left {
		text-align: left;
	}
</style>

</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<h4><a class="navbar-brand" href="../00user/main.do">
		서블릿 게시판</a></h4>
	    
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
		
	<div class="collapse navbar-collapse" id="navbarTogglerDemo03">
		<c:if test="${id == null }">
			<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
				<li class="nav-item active">
					<a class="nav-link" href="notice.do">공지사항 <span class="sr-only">(current)</span></a>
				</li>
				<li class="nav-item active">
					<a class="nav-link" href="list.do">자유게시판 <span class="sr-only">(current)</span></a>
				</li>
			</ul>
			<div class="col" style="text-align: right">
				<a href="../00user/login.jsp"><input type="button" class="btn btn-outline-primary" style="padding:2px 10px" value="로그인" ></a>
				<a href="../00user/join.jsp"><input type="button" class="btn btn-outline-primary" style="padding:2px 10px" value="회원가입" ></a>
			</div>
		</c:if>
		<c:if test="${id != null }">
			<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
				<li class="nav-item active">
					<a class="nav-link" href="notice.do">공지사항 <span class="sr-only">(current)</span></a>
				</li>
				<li class="nav-item active">
					<a class="nav-link" href="list.do">자유게시판 <span class="sr-only">(current)</span></a>
				</li>
			</ul>
			<div class="col" style="text-align: right">
		    	<%=id %> 님 &nbsp;&nbsp;
		    	<c:if test="${id == 'MANAGER' }">
				   	<a href="../00user/userList.doU"><input type="button" class="btn btn-outline-primary" style="padding:2px 10px" value="회원 관리" ></a>
			   	</c:if>
		    	<a href="../00user/logout.jsp"><input type="button" class="btn btn-outline-primary" style="padding:2px 10px" value="로그아웃" ></a>
		    	<a href="../00user/myInfoPage.jsp"><input type="button" class="btn btn-outline-primary" style="padding:2px 10px" value="마이페이지" ></a>
			</div>
		</c:if>
	</div>    
</nav>
<br><br>

<div class="container">
	<div class="row">
		<div class="col">
	    	<h2>&nbsp;&nbsp;공지사항</h2><br>
			<table class="table table-sm table-bordered" border="1">
				<thead>
					<tr>
						<th scope="col" colspan="2">제목</th>
						<th width=150>작성자</th>
						<th width=200>작성일</th>
						<th width=70>조회수</th>
						<th width=70>좋아요</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${nList }" var="dto">
					<tr>
						<td width=70>${dto.bId }</td>
						<td id="left">
							<a href="content_view.do?bId=${dto.bId }">&nbsp;${dto.bTitle }</a></td>					
						<td id="left">${dto.bName }</td>
						<td>${dto.bDate }</td>
						<td>${dto.bHit }</td>
						<td>${dto.like_cnt }</td>
					</tr>
					</c:forEach>
					<tr></tr>
					<c:if test="${id == 'MANAGER' }">
					<tr>
						<td id="left" colspan="6">&nbsp;
							<a href="write_view.do" class="btn btn-danger" style="padding:2px 10px">공지 올리기</a>
						</td>
					</tr>
					</c:if>				
				</tbody>	
			</table><br>
			<form action="bSearch.do" method="post" style="text-align:center;">
				<select name="b_opt">
					<option value="1">제목+내용</option>
					<option value="2">제목</option>
					<option value="3">내용</option>
					<option value="4">작성자</option>
				</select>&nbsp;
				<input type="text" name="keyword" size="20">&nbsp;
				<input type="submit" class="btn btn-outline-dark" value="검색" style="padding:3px 12px">
			</form>
	    </div>
    </div>
</div>




    
    
    
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
	
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
</body>
</html>
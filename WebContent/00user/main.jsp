<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String id = (String)session.getAttribute("id");
%>

<!DOCTYPE html>
<html>
<head>

	<!-- 브라우저 -->
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	
</head>
	
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<h4><a class="navbar-brand" href="main.do">
		서블릿 게시판</a></h4>
	    
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
		
	<div class="collapse navbar-collapse" id="navbarTogglerDemo03">
		<c:if test="${id == null }">
			<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
				<li class="nav-item active">
					<a class="nav-link" href="../00board/notice.do">공지사항 <span class="sr-only">(current)</span></a>
				</li>
				<li class="nav-item active">
					<a class="nav-link" href="../00board/list.do">자유게시판 <span class="sr-only">(current)</span></a>
				</li>				
			</ul>
			<div class="col" style="text-align: right">
				<a href="login.jsp"><input type="button" class="btn btn-outline-primary" style="padding:2px 10px" value="로그인" ></a>
				<a href="join.jsp"><input type="button" class="btn btn-outline-primary" style="padding:2px 10px" value="회원가입" ></a>
			</div>
		</c:if>
		<c:if test="${id != null }">
			<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
				<li class="nav-item active">
					<a class="nav-link" href="../00board/notice.do">공지사항 <span class="sr-only">(current)</span></a>
				</li>
				<li class="nav-item active">
					<a class="nav-link" href="../00board/list.do">자유게시판 <span class="sr-only">(current)</span></a>
				</li>
			</ul>
			<div class="col" style="text-align: right">
		    	<%=id %> 님 &nbsp;&nbsp;
		    	<c:if test="${id == 'MANAGER' }">
				   	<a href="../00user/userList.doU"><input type="button" class="btn btn-outline-primary" style="padding:2px 10px" value="회원 관리" ></a>
			   	</c:if>
		    	<a href="logout.jsp"><input type="button" class="btn btn-outline-primary" style="padding:2px 10px" value="로그아웃" ></a>
		    	<a href="myInfoPage.jsp"><input type="button" class="btn btn-outline-primary" style="padding:2px 10px" value="마이페이지" ></a>
			</div>
		</c:if>
	</div> 
</nav><br>

<div class="container">
	<div class="row">    
	    <div class="col" style="text-align: center"><br><br>
	   		<h1>Jsp/Servlet 게시판</h1><br><br>
			<img src="../img/Eclipse-luna.png" alt="이클립스" width="180">&nbsp;&nbsp;&nbsp;
			<img src="../img/1_zKnKunxf74dpaMMtcKrB9g.png" alt="jsp/servlet" width="300">&nbsp;&nbsp;&nbsp;
			<img src="../img/1508213062.563510_.png" alt="오라클" width="300"><br>
			<img src="../img/sql-developer.png" alt="오라클" width="400">
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
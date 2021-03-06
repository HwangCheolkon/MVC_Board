<%@ page import="jsp.board.dto.MemberDto" %>
<%@ page import="jsp.board.dao.MemberDao" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	String id = (String)session.getAttribute("id");
	MemberDao dao = MemberDao.getInstance();
	MemberDto dto = dao.getMember(id);
%>

<!DOCTYPE html>
<html>
<head>

	<!-- 브라우저 -->
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>

	<style>
		form { font-size: 15px }
	</style>
	
</head>
	
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<h4><a class="navbar-brand" href="main.do">
		서블릿 게시판</a></h4>
	    
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
		
	<div class="collapse navbar-collapse" id="navbarTogglerDemo03">
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
	</div>
</nav>
<br><br>

<div class="container">
    <div class="row">
	    <div class="col test1 align-self-start"></div>
	    <div class="col test1 align-self-center"><br>
	    	<h1>마이 페이지</h1><br>
	    	<form>
			아이디<br><input type="text" size="25" value="<%= dto.getId() %>"><br>
			<p style="font-size: 14px">(가입일 <%=dto.getrDate() %>)</p>
			이름<br><input type="text" size="25" value="<%= dto.getName() %>"><br><br>
			이메일<br><input type="text" name="eMail" size="25" value="<%= dto.geteMail() %>"><br><br>
			주소<br><input type="text" name="address" size="50" value="<%= dto.getAddress() %>"><br><br>
			<a href="modify.jsp"><input type="button" class="btn btn-outline-primary" value="회원정보 수정"></a>&nbsp;&nbsp;
			<a href="../00board/bMyBoard.do"><input type="button" class="btn btn-outline-primary" value="내가 쓴 글 보기"></a><br><br>
		   	<a href="leave.jsp"><input type="button" class="btn btn-outline-primary" value="회원 탈퇴"></a>
			</form><br>
			<img src="../img/Eclipse-luna.png" alt="이클립스" width="50">&nbsp;
			<img src="../img/1_zKnKunxf74dpaMMtcKrB9g.png" alt="jsp/servlet" width="85">&nbsp;
			<img src="../img/1508213062.563510_.png" alt="오라클" width="85">&nbsp;
			<img src="../img/sql-developer.png" alt="오라클" width="110">
		</div>
		<div class="col test1 align-self-end"></div>
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
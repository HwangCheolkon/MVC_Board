<%@ page import="jsp.board.dto.MemberDto" %>
<%@ page import="jsp.board.dao.MemberDao" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String id = (String)session.getAttribute("id");
	MemberDao dao = MemberDao.getInstance();
	MemberDto dto = dao.getMember(id);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://cdn.ckeditor.com/4.15.1/standard/ckeditor.js"></script>

<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>

<style>
	th {
		text-align: center;
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
	</div>  
</nav>
<br><br>

<div class="container">
	<div class="row">
		<div class="col">
	    	<h2>&nbsp;&nbsp;글쓰기</h2><br>
			<table width="1100" border="1">
				<form action="write.do" method="post">
					<tr>
						<th width="100" height="40">제목</th>
						<td> <input type="text" name="bTitle" size="140"> </td>
					</tr>
					<tr>
						<th width="100" height="40">작성자</th>
						<td> &nbsp;&nbsp;<%=id %> </td>
					</tr>
					<tr>
						<th width="100" height="320">내용</th>
						<td>
						<textarea name="bContent" id="bContent"></textarea>
						<script>CKEDITOR.replace('bContent');</script>
						</td>
					</tr>
					<tr>
						<td colspan="2" height="40">
							&nbsp;&nbsp;<input type="submit" class="btn btn-success" style="padding:2px 10px" value="등록">&nbsp;
							<a href="list.do?page=<%= session.getAttribute("cpage") %>" class="btn btn-success" style="padding:2px 10px">취소</a>&nbsp;						
						</td>
					</tr>
				</form>	
			</table>
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
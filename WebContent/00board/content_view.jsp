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
	    	<h2>&nbsp;&nbsp;자유게시판</h2><br>
			<table class="table table-sm table-bordered" border="1">
				<thead>
					<tr>
						<th id="left" style="font-size:23px; padding:10px;">${content_view.bTitle }</th>					
					</tr>
				</thead>
				<tbody>
					<tr>
						<td id="left" style="font-size:14px; padding:10px;">
							<form action="like.do" method="post">
							<span style="font-size:17px; font-weight:bold;">${content_view.bName }</span>&nbsp;&nbsp;
							${content_view.bDate }<br>
							조회&nbsp;${content_view.bHit }&nbsp;&nbsp;
								<input type="hidden" name="bId" value="${content_view.bId }">
								<input type="hidden" name="bName" value="${id }">
								<input type="submit" class="btn btn-outline-danger" value="♥ 좋아요 ${content_view.like_cnt }" style="padding:0px 4px; font-size:13px">
							</form>
						</td>
					</tr>
					<tr>
						<td id="left" style="padding:20px">${content_view.bContent }</td>
					</tr>
					<tr>
						<td id="left" style="padding:10px">
							<c:choose>
								<c:when test="${id != null }">
									<a href="write_view.do" class="btn btn-success" style="padding:2px 10px">글쓰기</a>
									<a href="reply_view.do?bId=${content_view.bId }" class="btn btn-secondary" style="padding:2px 10px">
										답글</a>
								</c:when>
								<c:otherwise>
									<a href="../00user/login.jsp" class="btn btn-success" style="padding:2px 10px">로그인하고 글쓰기</a>
								</c:otherwise>
							</c:choose>
							<c:if test="${id == content_view.bName }">
								<a href="modify_view.do?bId=${content_view.bId }" class="btn btn-secondary" style="padding:2px 10px">
									수정</a>
								<a href="delete.do?bId=${content_view.bId }" class="btn btn-secondary" style="padding:2px 10px">
									삭제</a>
							</c:if>
							<a href="list.do?page=<%= session.getAttribute("cpage") %>" class="btn btn-secondary" style="padding:2px 10px">
								목록</a>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<c:if test="${id == 'MANAGER' }">
								<a href="delete.do?bId=${content_view.bId }" class="btn btn-danger" style="padding:2px 10px">
									(관리자) 삭제</a>
							</c:if>
						</td>
					</tr>
				</tbody>
			</table>
			<hr>
			<!-- 댓글 -->
			<table class="table table-sm">
				<thead>
					<tr>
						<th colspan="5" style="font-size:20px; text-align:left;">댓글</th>
					</tr>
				</thead>
				<c:forEach items="${cList }" var="dto">
				<tbody>				
					<tr>
						<td width="150">${dto.coName }</td>
						<td style="text-align: left">&nbsp;${dto.coContent }</td>
						<td width="100">
							<c:if test="${id == dto.coName }">
								<form id="coModifySet" style="display: block;">
									<input type="button" value="수정" onclick="coModifyOn()" class="btn btn-light" style="padding:2px 10px">
								</form>								
								<form action="coModify.doC" method="post" id="coModifyDo" style="display: none;">
									<textarea name="coContent" rows="2" cols="50" >${dto.coContent }</textarea>
									<input type="hidden" name="bId" value="${content_view.bId }">
									<input type="hidden" name="coNo" value="${dto.coNo }">
									<input type="submit" value="수정" onclick="coModifyOut()" class="btn btn-light" style="padding:2px 10px">
									<input type="button" value="취소" onclick="coModifyOut()" class="btn btn-light" style="padding:2px 10px">
								</form>
								<form action="coDelete.doC" method="post" id="coModifyDo2" style="display: block;">
									<input type="hidden" name="bId" value="${content_view.bId }">
									<input type="hidden" name="coNo" value="${dto.coNo }">
									<input type="submit" value="삭제" class="btn btn-light" style="padding:2px 10px">				
								</form>
							</c:if>
						</td>
						<td width="100" style="font-size:12px;">${dto.coDate }</td>
					</tr>				
				</tbody>
				</c:forEach>	
			</table><br>
			<c:if test="${id != null }">
			<form action="coWrite.doC" method="post" style="text-align:center;">
				<input type="hidden" name="bId" value="${content_view.bId }">
				<input type="hidden" name="coName" value="${id }">
					<textarea name="coContent" rows="4" cols="120" placeholder="<%=id %>님 댓글을 작성해주세요"></textarea>
					&nbsp;<input type="submit" value="등록" class="btn btn-outline-dark" style="padding:2px 10px"><br><br>
			</form>
			</c:if>
	    </div>
    </div>
</div>

<script>
	function coModifyOn() {
		$('#coModifySet').css('display', 'none');
		$('#coModifyDo').css('display', 'block');
		$('#coModifyDo2').css('display', 'none');		
	}

	function coModifyOut() {
		$('#coModifySet').css('display', 'block');
		$('#coModifyDo').css('display', 'none');
		$('#coModifyDo2').css('display', 'block');
	}
</script>


    
    
    
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
	
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
</body>
</html>
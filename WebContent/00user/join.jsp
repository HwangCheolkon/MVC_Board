<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script language="JavaScript" src="members.js"></script>
	
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
	</div>  
</nav><br>

<div class="container">
    <div class="row">
	    <div class="col" style="text-align:center;"><br>
	    	<h1>회원가입</h1><br>
			<form action="joinOk.doU" method="post" name="reg_frm">
				아이디<br><input type="text" name="id" size="25"><br><br>
				비밀번호<br><input type="password" name="pw" size="25"><br><br>
				비밀번호 확인<br><input type="password" name="pw_check" size="25"><br><br>
				이름<br><input type="text" name="name" size="25"><br><br>
				본인 확인 이메일<br><input type="text" name="eMail" size="25"><br><br>
				주소<br><input type="text" name="address" size="50"><br><br>
				<input type="button" class="btn btn-outline-primary" value="가입하기" onclick="infoConfirm()">&nbsp;&nbsp;
				<input type="button" class="btn btn-outline-primary" value="로그인" onclick="javascript:window.location='login.jsp'">
			</form><br>
			<img src="../img/Eclipse-luna.png" alt="이클립스" width="100">&nbsp;
			<img src="../img/1_zKnKunxf74dpaMMtcKrB9g.png" alt="jsp/servlet" width="170">&nbsp;
			<img src="../img/1508213062.563510_.png" alt="오라클" width="170">&nbsp;
			<img src="../img/sql-developer.png" alt="오라클" width="220">
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
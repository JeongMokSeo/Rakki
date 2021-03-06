<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html>
<html lang="ko">


<head>
<jsp:include page="../includes/head.jsp" />
<sec:csrfMetaTags/>
</head>
<body>
	<div class="site-wrap">
		<header class="site-navbar" role="banner">
			<jsp:include page="../includes/header.jsp" />
		</header>
		<div class="container my lg">
			<div>
			<!-- Side Bar -->
			<jsp:include page="../includes/mypageSidebar.jsp"></jsp:include>
				<!-- Side Bar -->
			</div>
			<div class="content_area">
				<div class="content_main">
								<sec:authorize access="isAuthenticated()">
					<div class="login_info">
						<h4>회원 탈퇴</h4>
					</div>
					<form class="userModify" method="post">
				<label>
					<p class="label-txt">아이디</p> 
					<input type="text" class="input" id="id" name="id" >
					<div class="line-box">
						<div class="line"></div>
					</div>
				</label>
				</sec:authorize>
				 <sec:csrfInput/>
				<button href="${pageContext.request.contextPath}/rakki/main" class="loginBtn" type="submit" onclick="deleteForm(this)" >인증하기</button>
			</form>
					</div>
				</div>
			</div>
		<jsp:include page="../includes/footer.jsp" />
		<script>
		function deleteForm() {
			var id = document.getElementById("id");
			
			if(id.value == "") {
				event.preventDefault();
				alert("아이디를 입력해주세요");
				id.focus(); // 아이디 공백시 커서 깜빡임
				return false;
			};
		}
		</script>
	</div>
	<!-- End of Footer -->
	<jsp:include page="../includes/foot.jsp" />
</body>
</html>
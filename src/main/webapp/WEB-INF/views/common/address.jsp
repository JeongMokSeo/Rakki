<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="ko">


<head>
<jsp:include page="../includes/head.jsp" />
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
					<div class="content_title">
						<div class="title">
							<h3 class="title_h3">주소록</h3>
						</div>
						<div class="btn_box">
							<a href="${pageContext.request.contextPath}/common/addressReg" class="btn btn_add">
								<span>+ 새 배송지 추가</span>
							</a>
						</div>
					</div>
					<div class="my_list">
						<div class="basic">
							<div class="my_item is_active">
								<div class="info_bind">
									<div class="address_info">
										<div class="name_box">
											<span class="address_name">자바맨</span>
											<span class="address_mark">기본 배송지</span>
										</div>
										<p class="address_phone">
										010-1234-5678
										</p>
										<div class="address_box">
											<span class="zipcode">(123456)</span>
											<span class="address">서울 영등포구 영중로8길 6 6층</span>
										</div>
									</div>
								</div>
									<div class="btn_bind">
										<a href="#" type="button" class="btn outlinegrey small">수정</a>
										<a href="#" type="button" class="btn outlinegrey small">삭제</a>
									</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="../includes/footer.jsp" />
	</div>
	<!-- End of Footer -->
	<jsp:include page="../includes/foot.jsp" />
</body>
</html>
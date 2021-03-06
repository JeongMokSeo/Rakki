<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html lang="ko">

<head>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/admin.css">
	
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>administrator</title>

    <!-- Custom fonts for this template-->
    <link href="${pageContext.request.contextPath}/resources/admin/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="${pageContext.request.contextPath}/resources/admin/css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper" style="position: relative;">

		<jsp:include page="../includes/adminSidebar.jsp"/>
		
        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow" id="nav">
	                <div style="margin-left: 650px; margin-top: 25px">
						<ul>
					    	<li><a href="${pageContext.request.contextPath}/rakki/main">HOME</a></li>
					        <li><a href="${pageContext.request.contextPath}/admin/mNoticeList">NOTICE</a></li>
					        <li><a href="#">LOGOUT</a></li>
					    </ul>
					</div>
	            </nav>
	            <!-- End of Topbar -->

                            <div class="card shadow mb-4" style="margin: 15px; height: 90%">
                               <!-- Card Header - Dropdown -->
                                <div 
                                    class="card-header py-1" >
                            		<h4 style="font-weight: bold; margin-top: 5px; margin-left: 30px"> >> ????????????</h4>
                            	</div>
								
							<form id="frm_search">
								<table class="wp-table">
								    <tr>
								      <th>??????</th>
								      <th>??????</th>
								      <th style="float: right; margin-right: 38px">??????</th>
								    </tr>
								    <tr>
								      <td>
								      	<select name="type" id="type" class="custom-select custom-select-sm form-control form-control-sm mr-3">
										    <option value="" <c:out value="${page.cri.type eq '' ? 'selected' : '' }" />>????????????</option>
										    <option value="B" <c:out value="${page.cri.type eq 'B' ? 'selected' : '' }" />>?????????</option>
										    <option value="M" <c:out value="${page.cri.type eq 'M' ? 'selected' : '' }" />>????????????</option>
										    <option value="E" <c:out value="${page.cri.type eq 'E' ? 'selected' : '' }" />>???????????????</option>
										    <option value="K" <c:out value="${page.cri.type eq 'K' ? 'selected' : '' }" />>???????????????</option>
										</select>
								      </td>
								      <td>
								      	<div id="dataTable_filter" class="input-group">	
		                           			<input name="keyword" id="keyword" type="search" class="form-control form-control-sm" placeholder="search.."  value="${page.cri.keyword}">
					                        <button type="RESET" class="btn btn-secondary btn-sm" >x</button>	                            			
		                            	</div> 
								      </td>
								      <td rowspan="2"><button class="btn btn-primary btn float-right" id="btn_search" style="width: 100px; height: 70px">??????</button></td>
								    </tr>
								 </table>
							</form>
							
									<!-- Product Code Search -->
							
							<div style="position: absolute; top: 225px">
							<h6 style="margin-left: 70px; ">?????? ?????? : <b>[ <c:out value="${total}" /> ]</b> ???</h6>
							</div>
							
							<div class="row">
	                                <div class="col-sm-12">
		                                <table id="dataTable">
		                                    <thead>
			                                	<tr>
			                                    	<th>No</th>
			                                        <th>????????????</th>
			                                        <th>?????????</th>
			                                        <th>????????????</th>
			                                        <th>?????????(eng)</th>
			                                        <th>?????????(kor)</th>
			                                        <th>?????????</th>
			                                    </tr>
		                                	</thead>
		                                    <tbody>
											<c:forEach items="${list}" var="product">
												<tr>
													<td style="text-align: center;"><c:out value="${product.product_no}" /></td>
													<td style="text-align: center;"><c:out value="${product.category}" /></td>
													<td style="text-align: center;"><a href="get?product_no=${product.product_no}"><c:out value="${product.brand}" /></a></td>
													<td style="text-align: center;"><c:out value="${product.serialNo}" /></td>
													<td><c:out value="${product.name_en}" /></td>
													<td><c:out value="${product.name_ko}" /></td>
													<td style="text-align: center;"><fmt:formatDate value="${product.release}" pattern="yyyy-MM-dd" /></td>
												</tr>									
											</c:forEach>						
		                                	</tbody>
		                                </table>
	                                </div>
	                            </div>  
								
								<div class="row" style="margin-top: 40px; margin-right: 250px">
									<div class="col-sm-12 col-md-5"></div>
									<div class="col-sm-12 col-md-7">
										<div class="dataTables_paginate paging_simple_numbers" id="dataTable_paginate">
											<ul class="pagination">
												<li class="paginate_button page-item previous ${page.prev ? '' : 'disabled'}" id="dataTable_previous">
													<a href="mProductList?pageNum=${page.startPage-1}&amount=${page.cri.amount}" class="page-link">??????</a>
												</li>
												
												<c:forEach begin="${page.startPage }" end="${page.endPage}" var="p">
													<li class="paginate_button page-item ${p == page.cri.pageNum ? 'active' : ''}">
														<a href="mProductList?pageNum=${p}&amount=${page.cri.amount}&type=${page.cri.type}&keyword=${page.cri.keyword}"  class="page-link">${p}</a>
													</li>
												</c:forEach>
												
												<li class="paginate_button page-item next ${page.next ? '' : 'disabled'}" id="dataTable_next">
													<a href="mProductList?pageNum=${page.endPage +  1}&amount=${page.cri.amount}" class="page-link">??????</a>
												</li>												
											</ul>
										</div>
									</div>
								</div>
							
<!-- 	                            Color System -->
<!-- 	                            <div class="row"> -->
<!-- 	                                <div class="col-lg-6 mb-4"> -->
<!-- 	                                    <div class="card bg-success text-white shadow"> -->
<!-- 	                                        <div class="card-body"> -->
<!-- 	                                            Success -->
<!-- 	                                            <div class="text-white-50 small">#1cc88a</div> -->
<!-- 	                                        </div> -->
<!-- 	                                    </div> -->
<!-- 	                                </div> -->
<!-- 	                            </div> -->
			                  </div>
			            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>Copyright &copy; Your Website 2021</span>
                    </div>
                </div>
            </footer>
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">??</span>
                    </button>
                </div>
                <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <a class="btn btn-primary" href="login.html">Logout</a>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="${pageContext.request.contextPath}/resources/admin/vendor/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="${pageContext.request.contextPath}/resources/admin/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="${pageContext.request.contextPath}/resources/admin/js/sb-admin-2.min.js"></script>

    <!-- Page level plugins -->
    <script src="${pageContext.request.contextPath}/resources/admin/vendor/chart.js/Chart.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="${pageContext.request.contextPath}/resources/admin/js/demo/chart-area-demo.js"></script>
    <script src="${pageContext.request.contextPath}/resources/admin/js/demo/chart-pie-demo.js"></script>


    <!-- list Modal-->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Message</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">??</span>
                    </button>
                </div>
                <div class="modal-body"></div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">close</button>
                </div>
            </div>
        </div>
    </div>
    
<script>
$(function() {
	var result = '${result}';
	checkModal(result);
	history.replaceState({},null,null);
	function checkModal(result) {
		if (!result || history.state) {
			return;
		}
		var text = result == 'success' ? "????????? ?????????????????????" : "????????????" + result + "?????? ?????? ???????????????.";  
		
		$("#myModal .modal-body").text(text);
		$("#myModal").modal("show");
	}
	
 	//???????????? ?????? ??????????????? ?????? 
	$('#chg_amount').change(function() {
		$("#frm_search").find("input[name='amount']").val($(this).val());
		$("#frm_search").submit();
	}); 
	
 	//?????? ?????? ?????? 
	$('#btn_search').click(function() {
		event.preventDefault();			  
		if ($("#type option:selected").val() == "????????????") {
			location.href = 'mProductList'
		}
		if ($("#keyword").val() == "") {
			alert("?????????????????? ????????? ?????????");
			$("#keyword").focus();
			return false;
		}
		$("#frm_search").submit();
	});    	
});
</script>
</body>

</html>
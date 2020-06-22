<%@page import="model.Category"%>
<%@page import="java.util.List"%>
<%@page import="dao.CategoryDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp"%>
<%@ include file="/templates/admin/inc/leftbar.jsp"%>
<div id="page-wrapper">
	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h2>Cập nhật Logo</h2>
			</div>
		</div>
		<!-- /. ROW  -->
		<hr />
		<div class="row">
			<div class="col-md-12">
				<!-- Form Elements -->
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="row">
							<div class="col-md-12">
						<%
							if(request.getParameter("msg")!=null){
								int msg = Integer.parseInt(request.getParameter("msg"));
								if(msg==1){
									out.print("Update logo thành công");
								} else {
									out.print("Update logo thất bại");
								}
							}
						%>
								<form role="form"
									action="<%=request.getContextPath()%>/amdin/logo/update"
									method="post" enctype="multipart/form-data" id="form">
									
									<div class="form-group">
										<label for="picture">Hình ảnh</label> <input type="file"
											name="picture" />
									</div>
									
									<button type="submit" name="submit"
										class="btn btn-success btn-md">Thêm</button>
								</form>

							</div>
						</div>
					</div>
				</div>
				<!-- End Form Elements -->
			</div>
		</div>
		<!-- /. ROW  -->
	</div>
	<!-- /. PAGE INNER  -->
</div>

<script type="text/javascript">
	$(document).ready(function() {

		//Khi bàn phím được nhấn và thả ra thì sẽ chạy phương thức này
		$("#form").validate({
			rules : {
				name : {
					required : true,
				},
				category : {
					required : true,
					digits : true,
				},
				preview : {
					required : true,
				}
			},
			messages : {
				name : {
					required : "Vui lòng nhập tên bài hát</p>",

				},
				category : {
					required : "Vui chọn danh mục",
					minlength : 6,
				},
				preview : {
					required : "Vui lòng nhập mô tả bài hát",
				}

			},

		});

	});
</script>
<script>
	CKEDITOR.replace('detail');
</script>
<script>
    document.getElementById("logo").classList.add('active-menu');
</script> 

<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp"%>
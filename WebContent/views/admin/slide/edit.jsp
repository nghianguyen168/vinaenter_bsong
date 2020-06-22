<%@page import="model.Song"%>
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
				<h2>Sửa bài hát</h2>
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
									if (request.getParameter("msg") != null) {
										int msg = Integer.parseInt(request.getParameter("msg"));
										if (msg == 0) {
											out.print("Có lỗi trong quá trình xử lý, vui lòng thử lại!");
										}
									}
								%>
								<%
									int id = 0;
									String name = "";
									String picture = "";
									String preview_text = "";
									String detail_text = "";
									int cat_id = 0;

									if (request.getAttribute("song") != null) {
										Song song = (Song) request.getAttribute("song");
										id = song.getId();
										name = song.getName();
										picture = song.getPicture();
										preview_text = song.getPreview_text();
										detail_text = song.getDetail_text();
										cat_id = song.getCat_id();
									}
								%>
								<form role="form"
									action="<%=request.getContextPath()%>/amdin/slide/edit?id=<%=id%>"
									method="post" enctype="multipart/form-data" id="form">

									<div class="form-group">
										<label for="name">Tên bài hát</label> <input type="text"
											id="name" value="<%=name%>" name="name" class="form-control" />
									</div>
									<div class="form-group">
										<label for="category">Danh mục bài hát</label> <select
											id="category" name="category" class="form-control ">

											<%
												CategoryDAO categoryDAO = new CategoryDAO();
												List<Category> catList = categoryDAO.getItems();
												if (catList.size() > 0) {
													for (Category cat : catList) {
														if (cat.getId() == cat_id) {
											%>
											<option selected="selected" value="<%=cat.getId()%>"><%=cat.getName()%></option>
											<%
												} else {
											%>
											<option value="<%=cat.getId()%>"><%=cat.getName()%></option>

											<%
												}
													}
												}
											%>
										</select>
									</div>
									<div class="form-group">
										<label for="picture">Hình ảnh</label> <input type="file"
											name="picture" /> <img width="200px" height="100px" alt=""
											src="<%=request.getContextPath()%>/files/<%=picture%>">
									</div>
									<div class="form-group">
										<label for="preview">Mô tả</label>
										<textarea id="preview" class="form-control" rows="3"
											name="preview"><%=preview_text%></textarea>
									</div>
									<div class="form-group">
										<label for="detail">Chi tiết</label>
										<textarea id="detail" class="form-control" id="detail"
											rows="5" name="detail"><%=detail_text%></textarea>
									</div>

									<button type="submit" name="submit"
										class="btn btn-success btn-md">Sửa</button>
								</form>
								<%
									if (request.getParameter("msg") != null) {
										int msg = Integer.parseInt(request.getParameter("msg"));
										if (msg == 0) {
											out.print("Có lỗi trong quá trình thêm, vui lòng kiểm tra lại");
										} else {
											out.print("Thêm thành công!");
										}

									}
								%>
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
<script>
	document.getElementById("slide").classList.add('active-menu');
</script>
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
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp"%>
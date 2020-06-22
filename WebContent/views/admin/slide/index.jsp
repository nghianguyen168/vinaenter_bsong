<%@page import="dao.SlideDao"%>
<%@page import="model.Slide"%>
<%@page import="utils.SlideList"%>
<%@page import="controllers.AdminAddSlideController"%>
<%@page import="dao.SongDAO"%>
<%@page import="model.Song"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp"%>
<%@ include file="/templates/admin/inc/leftbar.jsp"%>
<div id="page-wrapper">
	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h2>Quản lý Slide</h2>
			</div>
		</div>
		<!-- /. ROW  -->
		<hr />
		<div class="row">
			<div class="col-md-12">
				<!-- Advanced Tables -->
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="table-responsive">
							<div class="row">
								<div class="col-sm-6">
									<%
										User user = null;
										if (session.getAttribute("isUser") != null) {
											user = (User) session.getAttribute("isUser");
										}
										if (user.getRole_id() == 3||user.getRole_id()==1) {
									%>
									<a href="<%=request.getContextPath()%>/amdin/slide/add"
										class="btn btn-success btn-md">Thêm</a>
									<%
										}
									%>
								</div>
								<div class="col-sm-6" style="text-align: right;">
									<form method="post"
										action="<%=request.getContextPath()%>/admin/song/search">
										<%
											String search = "";
											if (request.getAttribute("searchText") != null) {
												search = (String) request.getAttribute("searchText");
											}
										%>
										<input type="submit" name="search" value="Tìm kiếm"
											class="btn btn-warning btn-sm" style="float: right" /> <input
											name="searchText" type="search" class="form-control input-sm"
											placeholder="Nhập tên bài hát" value="<%=search%>"
											style="float: right; width: 300px;" />
										<div style="clear: both"></div>
									</form>

									<br />
									<%
										if (request.getParameter("msgS") != null) {
									%>
									<p>Không tìm thấy bài hát</p>
									<%
										}
									%>
								</div>
							</div>
							<%
								if (request.getParameter("msg") != null) {
									int msg = Integer.parseInt(request.getParameter("msg"));
									switch (msg) {

										case 1 :
											out.print("Thêm bài hát thành công");
											break;
										case 2 :
											out.print("Sửa bài hát thành công");
											break;
										case 3 :
											out.print("Xóa bài hát thành công");
											break;

									}
								}
							%>
							<table class="table table-striped table-bordered table-hover"
								id="dataTables-example">
								<thead>
									<tr>

										<th>ID</th>
										<th>Tên bài hát</th>
										<th>Danh mục</th>
										<th>Lượt đọc</th>
										<th>Hình ảnh</th>
										<th width="160px">Chức năng</th>
									</tr>
								</thead>
								<tbody>
									<%
										SlideDao slideDao = new SlideDao();
										
										if (slideDao.getItems()!=null) {
											 
											List<Slide> slideList = slideDao.getItems();
											
											if (slideList.size() > 0) {
												for (Slide song : slideList) {
													String urlDel = request.getContextPath() + "/amdin/slide/del?id=" + song.getId();
													String urlEdit = request.getContextPath() + "/amdin/slide/edit?id=" + song.getId();
									%>
									<tr>
										<td><%=song.getId()%></td>
										<td class="center"><%=song.getName()%></td>
										<td class="center"><%=song.getCategory()%></td>
										<td class="center"><%=song.getCounter()%></td>
										<td class="center">
											<%
												if (!"".equals(song.getPicture())) {
											%> <img width="100px" height="70px"
											src="<%=request.getContextPath()%>/files/<%=song.getPicture()%>"
											alt="Đổi thay" /> <%
									 	} else {
									 %> Không có hình <%
									 	}
									 %>
									
										</td>
										<td class="center">
										 <%
									 	if(user.getRole_id() == 3||user.getRole_id()==1){
											 %>
											
											<a href="<%=urlEdit%>" title=""
											class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>  
											<a href="<%=urlDel%>" onclick="return confirm('Bạn có chắc chắn muốn xóa không?')"
											title="" class="btn btn-danger"><i class="fa fa-pencil"></i>Xóa</a>
										<%
									 	}
										%>	
										</td>
										
									</tr>
									<%
												}
										
											}
										}
									%>

								</tbody>
							</table>
							
						</div>

					</div>
				</div>
				<!--End Advanced Tables -->
			</div>
		</div>
	</div>
</div>

<script>
	document.getElementById("slide").classList.add('active-menu');
</script>
<script>
	// Add active class to the current button (highlight it)
	var header = document.getElementById("dataTables-example_paginate");
	var btns = header.getElementsByClassName("paginate_button");
	for (var i = 0; i < btns.length; i++) {
		btns[i].addEventListener("click", function() {
			var current = document.getElementsByClassName("active");
			if (current.length > 0) {
				current[0].className = current[0].className.replace(" active",
						"");
			}
			this.className += " active";
		});
	}
</script>
<!-- <script>
	document.getElementById('searchText').addEventListener('keypress',
			function(event) {
				if (event.keyCode == 13) {
					event.preventDefault();
				}
			});
</script> -->
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp"%>
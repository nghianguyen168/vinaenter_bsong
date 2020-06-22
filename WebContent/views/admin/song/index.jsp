<%@page import="utils.DefineUtil"%>
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
				<h2>Quản lý bài hát</h2>
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
										if (user.getRole_id() == 2||user.getRole_id()==1) {
									%>
									<a href="<%=request.getContextPath()%>/admin/song/add"
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
										SongDAO songDAO = new SongDAO();
										int offset=0;
										if (request.getAttribute("offset") != null) {
											offset = (int) request.getAttribute("offset");
										}
										List<Song> songList=null;
										if (songDAO.getItemsByPageAdmin(offset) != null) {
											 songList = songDAO.getItemsByPageAdmin(offset);
											if (request.getAttribute("songListSearch") != null) {
												List<Song> songListSearch = (List<Song>) request.getAttribute("songListSearch");
												if (songListSearch.size() > 0) {
													songList = songListSearch;
												}
											}
											if (songList.size() > 0) {
												for (Song song : songList) {
													String urlDel = request.getContextPath() + "/admin/song/del?id=" + song.getId();
													String urlEdit = request.getContextPath() + "/admin/song/edit?id=" + song.getId();
									%>
									<tr>
										<td><%=song.getId()%></td>
										<td class="center"><%=song.getName()%></td>
										<td class="center"><%=song.getCategoy()%></td>
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
									 	if(user.getRole_id() == 2||user.getRole_id()==1){
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
							<%
							int numberOfPages=1;
							int curentPage=1;
							int numberOfItems=DefineUtil.NUMBER_PER_PAGE;
								if(request.getAttribute("numberOfPages")!=null){
								 numberOfPages =(int) request.getAttribute("numberOfPages");
								}
								if(request.getAttribute("curentPage")!=null){
								 curentPage =(int) request.getAttribute("curentPage");
							} if(request.getAttribute("numberOfItems")!=null){
								numberOfItems =(int) request.getAttribute("numberOfItems");
							}
								
								if(songList!=null||songList.size()>0){
							
							%>
							<div class="row">
								<div class="col-sm-6">
									<div class="dataTables_info" id="dataTables-example_info"
										style="margin-top: 27px">Hiển thị từ <%=offset %> đến <%=offset+DefineUtil.NUMBER_PER_PAGE %> của <%=numberOfItems%>
										truyện</div>
								</div>
								<div class="col-sm-6" style="text-align: right;">
									<div class="dataTables_paginate paging_simple_numbers"
										id="dataTables-example_paginate">
										<ul class="pagination">
										<%
											
											if(numberOfPages>2){
										
										%>
											<li class="" aria-controls="dataTables-example" tabindex="0"
												id="dataTables-example_previous"><a  href="<%=request.getContextPath()%>/admin/song/index?page=<%=curentPage-1%>">Trang
													trước</a></li>
											<%
											 
											}
											%>

										<%
											String active="";
											for(int i=1;i<=numberOfPages;i++){
												if(curentPage==i){
													active="active";
												} else {
													active="";
												}
										%>

											<li class="paginate_button <%=active %>"
												aria-controls="dataTables-example" tabindex="0"><a
												href="<%=request.getContextPath()%>/admin/song/index?page=<%=i%>"><%=i %></a></li>
											
											<%
											}
											%>
											<%
											if(numberOfPages>2){
											%>
											<li class="paginate_button next"
												aria-controls="dataTables-example" tabindex="0"
												id="dataTables-example_next"><a href="<%=request.getContextPath()%>/admin/song/index?page=<%=curentPage+1%>">Trang tiếp</a></li>
										<%
											}
										%>
										</ul>
									</div>
								</div>
							</div>
							<%
								}
							%>
						</div>

					</div>
				</div>
				<!--End Advanced Tables -->
			</div>
		</div>
	</div>
</div>

<script>
	document.getElementById("song").classList.add('active-menu');
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
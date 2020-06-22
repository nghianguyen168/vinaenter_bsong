<%@page import="model.Contact"%>
<%@page import="model.User"%>
<%@page import="model.Category"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp"%>
<%@ include file="/templates/admin/inc/leftbar.jsp"%>
<div id="page-wrapper">
	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h2>Quản lý liên hệ</h2>
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
									
								</div>
								<div class="col-sm-6" style="text-align: right;">
									<form method="post" action="">
										<input type="submit" name="search" value="Tìm kiếm"
											class="btn btn-warning btn-sm" style="float: right" /> <input
											type="search" class="form-control input-sm"
											placeholder="Nhập thông tin liên hệ"
											style="float: right; width: 300px;" />
										<div style="clear: both"></div>
									</form>
									<br />
								</div>
							</div>

							<%
								if (request.getParameter("msg") != null) {
									int msg = Integer.parseInt(request.getParameter("msg"));

									switch (msg) {
										case 0 :
											out.print("Có lỗi trong quá trình xử lý!");
											break;
										case 1 :
											out.print("Thêm thành công!");
											break;
										case 2 :
											out.print("Sửa thành công");
											break;
										case 3 :
											out.print("Xóa thành công!");
											break;
									}
								}
							%>
							<form action="<%=request.getContextPath()%>/admin/contact/del" method="POST">
							<table class="table table-striped table-bordered table-hover"
								id="dataTables-example">
								<thead>
									<tr>
										<th>ID</th>
										<th>Tên</th>
										<th>Email</th>
										<th>Website</th>
										<th>Message</th>
										<th width="160px">Chức năng</th>
									</tr>
								</thead>
								<tbody>
									<%
									List<Contact> contactList=null;
										if(request.getAttribute("contactListByPage")!=null){
											contactList = (List<Contact>)request.getAttribute("contactListByPage");
											if(contactList.size()>0){
												for(Contact contact:contactList){
											
									
									%>
									<tr>
										<td><%=contact.getId() %></td>
										<td class="center"><%=contact.getName() %></td>
										<td class="center"><%=contact.getEmail() %></td>
										<td class="center"><%=contact.getWebsite() %></td>
										<td class="center"><%=contact.getMessage() %></td>
										
										<td>
										  
										   		<input size="35" type="checkbox" id="deleteContact" name="deleteContact" value="<%=contact.getId()%>">
										   
										
											<a style="margin-left: 60px;" href="<%=request.getContextPath() %>/admin/contact/del?id=<%=contact.getId() %>"
												onclick="return confirm('Bạn có chắc chắn muốn xóa không?')"
												title="Xóa" class="btn btn-danger"><i
												class="fa fa-pencil"></i> Xóa
											</a>
										</td>
									</tr>
								<%
								
												}
											}
										}
								%>
								</tbody>
							</table>
							<div style="float: right">
							
									<input style="margin-right:64px;"  name="" type="checkbox" id="select_all"/>
							
							
									<button style="float: right; margin-right: 10px; width: 65px;" type="submit" name="submit" onclick="return confirm('Bạn có chắc chắn muốn xóa không?')"
										class="btn btn-danger">Xóa</button>
							
							</div>
							
							</form>
							<%
							int numberOfPages=1;
							int curentPage=1;
								if(request.getAttribute("numberOfPages")!=null){
								 numberOfPages =(int) request.getAttribute("numberOfPages");
								}
								if(request.getAttribute("curentPage")!=null){
								 curentPage =(int) request.getAttribute("curentPage");
							}
								if(contactList!=null||contactList.size()>0){
							
							%>
							<div class="row">
								<div class="col-sm-6">
									<div class="dataTables_info" id="dataTables-example_info"
										style="margin-top: 27px">Hiển thị từ 1 đến 5 của 24
										truyện</div>
								</div>
								<div class="col-sm-6" style="text-align: right;">
									<div class="dataTables_paginate paging_simple_numbers"
										id="dataTables-example_paginate">
										<ul class="pagination">
										<%
											
											if(numberOfPages>1){
										
										%>
											<li class="" aria-controls="dataTables-example" tabindex="0"
												id="dataTables-example_previous"><a  href="<%=request.getContextPath()%>/admin/contact/index?page=<%=curentPage-1%>">Trang
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
												href="<%=request.getContextPath()%>/admin/contact/index?page=<%=i%>"><%=i %></a></li>
											
											<%
											}
											%>
											<%
											if(numberOfPages>1){
											%>
											<li class="paginate_button next"
												aria-controls="dataTables-example" tabindex="0"
												id="dataTables-example_next"><a href="<%=request.getContextPath()%>/admin/contact/index?page=<%=curentPage+1%>">Trang tiếp</a></li>
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
	document.getElementById("contact").classList.add('active-menu');
</script>
<script>
$('#select_all').click(function(event) {
	  if(this.checked) {
	      // Iterate each checkbox
	      $(':checkbox').each(function() {
	          this.checked = true;
	      });
	  }
	  else {
	    $(':checkbox').each(function() {
	          this.checked = false;
	      });
	  }
	});
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp"%>
<%@page import="dao.UserDAO"%>
<%@page import="dao.SongDAO"%>
<%@page import="dao.CategoryDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp"%>
<%@ include file="/templates/admin/inc/leftbar.jsp"%>
<div id="page-wrapper">
	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h2>TRANG QUẢN TRỊ VIÊN</h2>
			</div>
		</div>
		<!-- /. ROW  -->
		<hr />
		<div class="row">
			<% if(AuthUtil.isRole(concatRole, "admin","editor")) {
                	
              %>
			<div class="col-md-4 col-sm-4 col-xs-4">
				<div class="panel panel-back noti-box">
					<span class="icon-box bg-color-green set-icon"> <i
						class="fa fa-bars"></i>
					</span>

					<div class="text-box">

						<p class="main-text">
							<a href="<%=request.getContextPath() %>/admin/cat/index" title="">Quản
								lý danh mục</a>
						</p>
						<%
                        	CategoryDAO categoryDAO = new CategoryDAO();
                        	int sumCat = categoryDAO.getSumCat();
                        	if(sumCat>0){
                        %>
						<p class="text-muted">
							Có
							<%=sumCat%>
							danh mục
							<sp> <%} %>
							
					</div>

				</div>
			</div>
			<%
                }
                    %>
			<% if(AuthUtil.isRole(concatRole, "admin","editor")) {
                	
              %>
			<div class="col-md-4 col-sm-4 col-xs-4">
				<div class="panel panel-back noti-box">
					<span class="icon-box bg-color-blue set-icon"> <i
						class="fa fa-bell-o"></i>
					</span>
					<div class="text-box">
						<%
                    	int sumSong=0;
                    	SongDAO songDAO = new SongDAO();
                    	if(songDAO.getItemsAdmin()!=null){
                    		sumSong=songDAO.getItemsAdmin().size();
                    	}
                    %>
						<p class="main-text">
							<a href="<%=request.getContextPath() %>/admin/song/index"
								title="">Quản lý bài hát</a>
						</p>
						<p class="text-muted">
							Có
							<%=sumSong %>
							bài hát
						</p>
					</div>
				</div>
			</div>
			<%
			}
			%>
			<%
			 if(AuthUtil.isRole(concatRole, "admin")) {
             	
	             %>
		
			<div class="col-md-4 col-sm-4 col-xs-4">
				<div class="panel panel-back noti-box">
					<span class="icon-box bg-color-brown set-icon"> <i
						class="fa fa-rocket"></i>
					</span>
					<div class="text-box">
						<%
                    	int sumUser=0;
                    	UserDAO userDAO = new UserDAO();
                    	if(userDAO.getItems()!=null){
                    		sumUser = userDAO.getItems().size();
                    	}
                    %>
						<p class="main-text">
							<a href="<%=request.getContextPath() %>/admin/user/index"
								title="">Quản lý người dùng</a>
						</p>
						<p class="text-muted">Có người dùng</p>
					</div>
				</div>
			</div>
			<%
			 }
			%>
		</div>

	</div>
</div>
<script>
	document.getElementById("index").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp"%>
<%@page import="model.Slide"%>
<%@page import="dao.SlideDao"%>
<%@page import="model.Song"%>
<%@page import="dao.SongDAO"%>
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
				<h2>Thêm bài hát nổi bật</h2>
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
										int msg= Integer.parseInt(request.getParameter("msg"));
										if(msg==0){
											out.print("Bài hát đã có trong slide");
										}
									}
								%>
								<form role="form"
									action="<%=request.getContextPath()%>/amdin/slide/add"
									method="post" enctype="multipart/form-data" id="form">
									
									<div class="form-group">
										<label for="">Danh sách bài hát</label> <select
											id="song" name="song" class="form-control ">
											
											<%
												SongDAO songDAO = new SongDAO();
												List<Song> songList = null;
												if(songDAO.getItems()!=null){
													songList = songDAO.getItems();
												}
												
													if(songList.size()>0){
														for(Song song:songList){
														
													
											%>
											

											<option value="<%=song.getId()%>"><%=song.getName() %></option>
											<%
														}		}
											%>
											
										</select>
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
 <script>
	document.getElementById("slide").classList.add('active-menu');
</script>


<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp"%>
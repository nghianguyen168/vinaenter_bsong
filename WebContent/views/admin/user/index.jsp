<%@page import="utils.DefineUtil"%>
<%@page import="model.User"%>
<%@page import="model.Category"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Quản lý người dùng</h2>
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
                                User user1=null;
                                if(session.getAttribute("isUser")!=null){
                                 user1 = (User )session.getAttribute("isUser") ;
                                	if(user1.getRole_id()==1){
                                %>
                                    <a href="<%=request.getContextPath() %>/admin/user/add" class="btn btn-success btn-md">Thêm</a>
                                    <%
                                	} }
                                    %>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <form method="post" action="">
                                        <input type="submit" name="search" value="Tìm kiếm" class="btn btn-warning btn-sm" style="float:right" />
                                        <input type="search" class="form-control input-sm" placeholder="Nhập tên người dùng" style="float:right; width: 300px;" />
                                        <div style="clear:both"></div>
                                    </form><br />
                                </div>
                            </div>

							<%
								if(request.getParameter("msg")!=null){
									int msg = Integer.parseInt(request.getParameter("msg"));
									 
									switch(msg){
									case 0: out.print("Có lỗi trong quá trình xử lý!");
									break;
									case 1: out.print("Thêm thành công!");
									break;
									case 2: out.print("Sửa thành công");
									break;
									case 3: out.print("Xóa thành công!");
									break;
									case 6: out.print("<p style=\" color: red; font-weight:bold;\">Bạn không có quyền thêm!</p>");
									break;
									case 7: out.print("<p style=\" color: red; font-weight:bold;\">Bạn không có quyền sửa!</p>");
									break;
									case 8: out.print("<p style=\" color: red; font-weight:bold;\">Bạn không có quyền xóa!</p>");
									break;
									
									}
								}
							
							%>
                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Tên người dùng</th>
                                        <th>Fullname</th>
                                        <th width="160px">Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <%
                                List<User> userList =null;
                                	if(request.getAttribute("userListByPage")!=null){
                                		userList = (List<User>)request.getAttribute("userListByPage");
                                		if(userList.size()>0){
                                			for(User userU:userList){
                                		
                                				String urlDel = request.getContextPath()+"/admin/user/del?id="+userU.getId();
                                				String urlEdit = request.getContextPath()+"/admin/user/edit?id="+userU.getId();
                                	
                                %>
                                    <tr>
                                        <td><%=userU.getId() %></td>
                                        <td class="center"><%=userU.getUsername()%></td>
                                        <td class="center"><%=userU.getFullname() %></td>
                                        
                                        	<%
                                        		if(userL.getRole_id()==1){
                                        	%>
                                        	<td class="center">
                                            	<a href="<%=urlEdit %>" title="Sửa" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
                                            	 <a href="<%=urlDel %>" onclick="return confirm('Bạn có chắc chắn muốn xóa không?')" title="Xóa" class="btn btn-danger"><i class="fa fa-pencil"></i> Xóa</a>
                                             </td>
                                            <%
                                        		} else {
                                        			
                                        		
                                            %>
                                            	<td class="center">
                                            	<%
                                            		if(userL.getRole_id()==1 || userU.getId()==userL.getId()){
                                            	%>
                                            	<a href="<%=urlEdit %>" title="Sửa" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
                                            	<%
                                            		}
                                            	%>
                                             </td>
                                       		<%
                                        		}
                                       		%>
                                        
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
							int offset=1;
							int numberOfItems=DefineUtil.NUMBER_PER_PAGE_USER;
								if(request.getAttribute("numberOfPages")!=null){
								 numberOfPages =(int) request.getAttribute("numberOfPages");
								}
								if(request.getAttribute("curentPage")!=null){
								 curentPage =(int) request.getAttribute("curentPage");
							}	if(request.getAttribute("numberOfItems")!=null){
								numberOfItems =(int) request.getAttribute("numberOfItems");
							}
							if(request.getAttribute("offset")!=null){
								offset =(int) request.getAttribute("offset");
							}
								if(userList!=null||userList.size()>0){
							
							%>
							<div class="row">
							<div class="col-sm-6">
									<div class="dataTables_info" id="dataTables-example_info"
										style="margin-top: 27px">Hiển thị từ <%=offset %> đến <%=offset+DefineUtil.NUMBER_PER_PAGE_USER %> của <%=numberOfItems%>
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
												id="dataTables-example_previous"><a  href="<%=request.getContextPath()%>/admin/user/index?page=<%=curentPage-1%>">Trang
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
												href="<%=request.getContextPath()%>/admin/user/index?page=<%=i%>"><%=i %></a></li>
											
											<%
											}
											%>
											<%
											if(numberOfPages>1){
											%>
											<li class="paginate_button next"
												aria-controls="dataTables-example" tabindex="0"
												id="dataTables-example_next"><a href="<%=request.getContextPath()%>/admin/user/index?page=<%=curentPage+1%>">Trang tiếp</a></li>
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
    document.getElementById("user").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>
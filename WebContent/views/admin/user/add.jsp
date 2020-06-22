<%@page import="model.Roles"%>
<%@page import="java.util.List"%>
<%@page import="dao.RolesDao"%>
<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Thêm người dùng</h2>
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
									 if(msg==0){
										out.print("<p style=\"color:red;\" >Có lỗi trong quá trình thêm, vui lòng kiểm tra lại</p>");
									}if(msg==4){
										out.print("<p style=\"color:red;\" >Quyền Admin đã tồn tại!</p>");
									}if(msg==5){
										out.print("<p style=\"color:red;\" >Người dùng đã tồn tại</p>");
									} 
									 /* else {
										
									}  */
									
								}
							
							%>
							
                                <form action="<%=request.getContextPath() %>/admin/user/add" role="form" method="post"  id="form">
                                <%
                                	if(request.getAttribute("userErr")!=null){
                                		User userErr = (User)request.getAttribute("userErr");
                                	
                                %>
                                    <div class="form-group">
                                        <label for="name">Username</label>
                                        <input type="text" name="username" id="username" value="<%=userErr.getUsername() %>" name="username" class="form-control"  placeholder="Tên đăng nhập"/>
                                        
                                    </div>
                                    
                                     <div class="form-group">
                                        <label for="name">Password</label>
                                        <input type="password" name="password" id="password" value="<%=userErr.getPassword() %>" name="password" class="form-control"  placeholder="Mật khẩu"/>
                                        
                                    </div>
                                     <div class="form-group">
                                         <label for="name">Fullname</label>
                                        <input type="text" name="fullname" id="fullname" value="<%=userErr.getFullname() %>" name="fullname" class="form-control"  placeholder="Họ và tên người dùng"/>
                                    </div>
                                    <div class="form-group">
										<label for="roles">Vai trò</label> <select
											id="roles" name="roles" class="form-control ">
												
												<%
													RolesDao rolesDao = new RolesDao();
													if(rolesDao.getItems()!=null){
														List<Roles> roleList = rolesDao.getItems();
														if(roleList.size()>0){
															for(Roles roles :roleList){
																if(roles.getId()==userErr.getId()){
																	
																
												%>
												<option selected="selected" value="<%=roles.getId()%>"><%=roles.getName() %></option>
												<%
																} else {
												%>
												<option value="<%=roles.getId()%>"><%=roles.getName() %></option>
												<%
													} } } }
												%>						
										
										</select>
									</div>
                                  
                                    <%
                                		} else {
                                    %>
                                 
                                    <div class="form-group">
                                        <label for="name">Username</label>
                                        <input type="text" id="username" value="" name="username" class="form-control"  placeholder="Tên đăng nhập"/>
                                        
                                    </div>
                                    
                                     <div class="form-group">
                                        <label for="name">Password</label>
                                        <input type="password" id="password" value="" name="password" class="form-control"  placeholder="Mật khẩu"/>
                                        
                                    </div>
                                     <div class="form-group">
                                         <label for="name">Fullname</label>
                                        <input type="text" id="fullname" value="" name="fullname" class="form-control"  placeholder="Họ và tên người dùng"/>
                                    </div>
                                      <div class="form-group">
										<label for="roles">Vai trò</label> 
												
												<%
													RolesDao rolesDao = new RolesDao();
													if(rolesDao.getItems()!=null){
														List<Roles> roleList = rolesDao.getItems();
														if(roleList.size()>0){
															for(Roles roles :roleList){
													
												%>
												
										   		<br><input size="35" type="checkbox" id="roleID" name="roleID" value="<%=roles.getId()%>"><%=roles.getName() %>
												<%
													} } }
												%>						
										
										</select>
									</div>
                                    <%
                                		}  
                                    %>
                                       
                                    <button type="submit" name="submit" class="btn btn-success btn-md">Thêm</button>
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
    document.getElementById("user").classList.add('active-menu');
</script>
<script type="text/javascript">
		$(document).ready(function() {

			//Khi bàn phím được nhấn và thả ra thì sẽ chạy phương thức này
			$("#form").validate({
				rules : {
					username : {
						required : true,

					},
					password : {
						required : true,
						digits : true,

					},
					fullname : {
						required : true,

					}
					
				},
				messages : {
					username : {
						required : "Vui lòng nhập tên đăng nhập</p>",
						
					},
					password : {
						required : "Vui lòng nhập mật khẩu",
						minlength: 6,
					},
					fullname : {
						required : "Vui lòng nhập họ tên",
					}
					

				},
				
			});
			
		});
	</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>
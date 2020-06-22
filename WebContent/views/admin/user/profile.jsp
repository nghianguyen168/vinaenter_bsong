
<%@page import="utils.StringUtil"%>
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
                <h2>Thông tin cá nhân</h2>
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
                            		int id=0;
                            		String username ="";
                            		String password="";
                            		String fullname = "";
                            		String role_name = "";
                            		int  role_id =0;
                            		UserDAO userDAO1 = new UserDAO();
                            		if(session.getAttribute("isUser")!=null){
                            			
                            			User user3 = (User)session.getAttribute("isUser");
                            				if(request.getAttribute("userErr")!=null){
                            					User userError =(User) request.getAttribute("userErr");
                            					if(userError!=null){
                            						user3=userError;
                            					}
                            				}
                            			id = user3.getId();
                            			username = user3.getUsername();
                            			password = user3.getPassword();
                            			fullname = user3.getFullname();
                            			role_name=user3.getRole_name();
                            			role_id=user3.getRole_id();
                      
                            		}
                            	%>
                            	<%
                            		if(request.getParameter("msg")!=null){
                            			int msg=Integer.parseInt(request.getParameter("msg"));
                            			if(msg==1){
                            				out.print("Đổi mật khẩu thành công!");
                            			}
                            		}
                            	%>
                                <form action="<%=request.getContextPath() %>/admin/user/profile?id=<%=id %>" role="form" method="post"  id="form">
                                    <div class="form-group">
                                        <label for="name">Username</label>
                                        <input type="text" id="username" value="<%=username%>" name="username" class="form-control"  placeholder=""/>
                                        
                                    </div>
                                    
                                     <div class="form-group">
                                        <label for="name">Password</label>
                                        <input type="password" id="password" value="<%=password%>" name="password" class="form-control"  placeholder=""/>
                                        
                                    </div>
                                     <div class="form-group">
                                         <label for="name">Fullname</label>
                                        <input type="text" id="fullname" value="<%=fullname%>" name="fullname" class="form-control"  placeholder=""/>
                                    </div>
                                     <%
                                     	
                                     	if(session.getAttribute("isUser")!=null){
                                     		User user2= (User) session.getAttribute("isUser");
                                     		if(user2.getRole_id()==1){
                                     	
                                     %>
                                     <div class="form-group">
										<label for="roles">Vai trò</label> <select
											id="roles" name="roles" class="form-control ">
												
												<%
													String checked="";
													RolesDao rolesDao = new RolesDao();
													List<User> userRoles =null;
													List<User> roleList=null;
													if (uDao.getRoleList(id) != null) {
								         				roleList = uDao.getRoleList(id);
								         				List<String> lr = new ArrayList<>();
								         				for (User user : roleList) {
								         					lr.add(user.getRole_name());
								         				}
								         				concatRole = AuthUtil.concatRole(lr);
								         			}

								         			
														/* if(roleList.size()>0){
															for(Roles roles :roleList){
																for(User u:userRoles){
																	
																if(roles.getId()==u.getRole_id()){
																	checked="checked";
				
																} */
															
																
												%>
												<br><input <% if(AuthUtil.isRole(concatRole, "admin")) {out.print("checked");} %> size="35"  type="checkbox" id="roleID" name="roleID" value="<%=1%>">admin
												<br><input <% if(AuthUtil.isRole(concatRole, "editor")) {out.print("checked");} %> size="35"  type="checkbox" id="roleID" name="roleID" value="<%=2%>">editor
												<br><input <% if(AuthUtil.isRole(concatRole, "mod")) {out.print("checked");} %> size="35"  type="checkbox" id="roleID" name="roleID" value="<%=3%>">mod
												<%
														/* }} } */
												%>						
										
										</select>
									</div>
									<%
									
														} }
									%>
                                  <%
								if(request.getParameter("msg")!=null){
									int msg = Integer.parseInt(request.getParameter("msg"));
									 if(msg==0){
										out.print("Có lỗi trong quá trình xử lý, vui lòng kiểm tra lại");
									}if(msg==4){
										out.print("<p style=\"color:red;\" >Quyền Admin đã tồn tại!</p>");
									}if(msg==5){
										out.print("<p style=\"color:red;\" >Người dùng đã tồn tại</p>");
									} 
								}
							
							%>
                                       
                                  <button type="submit" name="submit" class="btn btn-success btn-md">Cập nhật</button>
                             		
                             		<a id="delUser"  href="<%=request.getContextPath() %>/admin/user/password?id=<%=id%>">Đổi mật khẩu</a>
                             		
                                </form>
                                
                                 <%-- form action="<%=request.getContextPath() %>/amdin/user/editpass?id=<%=id %>" method="post">
                                	<button style="background: red;" type="submit" name="submit" class="btn btn-success btn-md">Đổi mật khẩu</button>
                                </form> --%>
                                
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
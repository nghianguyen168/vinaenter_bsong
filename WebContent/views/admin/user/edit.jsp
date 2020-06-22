
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
                <h2>Thông tin người dùng</h2>
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
                            		if(request.getAttribute("user")!=null){
                            			
                            			User user1 = (User)request.getAttribute("user");
                            				if(request.getAttribute("userErr")!=null){
                            					User userError =(User) request.getAttribute("userErr");
                            					if(userError!=null){
                            						user1=userError;
                            					}
                            				}
                            			id = user1.getId();
                            			username = user1.getUsername();
                            			password = user1.getPassword();
                            			fullname = user1.getFullname();
                            			role_name=user1.getRole_name();
                            			role_id=user1.getRole_id();
                      
                            		}
                            	%>
                                <form action="<%=request.getContextPath() %>/admin/user/edit?id=<%=id %>" role="form" method="post"  id="form">
                                    <div class="form-group">
                                        <label for="name">Username</label>
                                        <input type="text" id="username" value="<%=username%>" name="username" class="form-control"  placeholder=""/>
                                        
                                    </div>
                                    
                                     <div class="form-group">
                                        <label for="name">Password</label>
                                        <input type="password" id="password" value="" name="password" class="form-control"  placeholder="Nhập mật khẩu mới"/>
                                        
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
										<label for="roles">Vai trò</label> 
												
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
                             		<%	
                             			User user=null;
                             			if(session.getAttribute("isUser")!=null){
                             				user=(User)session.getAttribute("isUser");
                             			}
                             			if(user.getRole_id()!=1){
                             		%>
                             		<a id="delUser"  href="<%=request.getContextPath() %>/amdin/user/editpass?id=<%=id%>">Đổi mật khẩu</a>
                             		<%
                             			}
                             		%>
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
				
					fullname : {
						required : true,

					}
					
				},
				messages : {
					username : {
						required : "Vui lòng nhập tên đăng nhập</p>",
						
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
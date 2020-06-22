
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
                            	 
                                 User user2=null;
                                 if(session.getAttribute("isUser")!=null){
                                  user2 = (User )session.getAttribute("isUser") ;
                                 }
                                 %>
                            	<%
                            		if(request.getParameter("msg")!=null){
                            			int msg=Integer.parseInt(request.getParameter("msg"));
                            			if(msg==0){
                            				out.print("Mật khẩu củ không chính xác!");
                            			}
                            		}
                            	%>
                                <form action="<%=request.getContextPath() %>/admin/user/password?id=<%=user2.getId() %>" role="form" method="post"  id="form">
                                   
                                     <div class="form-group">
                                        <label for="name">Mật khẩu cũ</label>
                                        <input type="password" id="password" value="" name="password" class="form-control"  placeholder=""/>
                                        
                                    </div>
                                    <div class="form-group">
                                        <label for="name">Mật khẩu mới</label>
                                        <input type=password id="newpassword" value="" name="newpassword" class="form-control"  placeholder=""/>
                                        
                                    </div>
                                    
                                    
                                       
                                    <button type="submit" name="submit" class="btn btn-success btn-md">Cập nhật</button>
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
					
					password : {
						required : true,
						

					},
					
					repassword : {
						required : true,
					

					}
					
					
				},
				messages : {
					password : {
						required : "Vui lòng nhập mật khẩu cũ",
						
					},
					repassword : {
						required : "Vui lòng nhập mật khẩu mới",
					}
					

				},
				
			});
			
		});
	</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>
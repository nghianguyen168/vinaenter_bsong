<%@page import="model.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Sửa danh mục</h2>
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
                          	String name="";
                          	int id=0;
                          	if(request.getAttribute("cat")!=null){
                          		Category cat = (Category)request.getAttribute("cat");
                          		name = cat.getName();
                          		id=cat.getId();
                          	}
                          %>
							
                                <form action="<%=request.getContextPath() %>/admin/cat/edit?id=<%=id %>" role="form" method="post"  id="form">
                                    <div class="form-group">
                                        <label for="name">Tên danh mục</label>
                                        <input type="text" id="name" value="<%=name %>" name="name" class="form-control" />
                                    </div>
                                    
                                    <button type="submit" name="submit" class="btn btn-success btn-md">Sửa</button>
                                </form>
                                  <%
								if(request.getParameter("msg")!=null){
									int msg = Integer.parseInt(request.getParameter("msg"));
									 if(msg==0){
										out.print("Có lỗi trong quá trình thêm, vui lòng kiểm tra lại");
									} else {
										out.print("Thêm thành công!");
									} 
									
								}
							
							%>
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
    document.getElementById("category").classList.add('active-menu');
</script>
<script type="text/javascript">
		$(document).ready(function() {

			//Khi bàn phím được nhấn và thả ra thì sẽ chạy phương thức này
			$("#form").validate({
				rules : {
					name : {
						required : true,
					}
					
				},
				messages : {
					name : {
						required : "Vui lòng nhập danh mục",
						
					},
					
					

				},
				
			});
			
		});
	</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>
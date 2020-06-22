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
                <h2>Quản lý danh mục</h2>
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
                                		User user=null;
                                		if(session.getAttribute("isUser")!=null){
                                			user = (User)session.getAttribute("isUser");
                                		}
                                		if(user.getRole_id()==1||user.getRole_id()==2){
                   
                                	%>
                                
                                    <a href="<%=request.getContextPath() %>/admin/cat/add" class="btn btn-success btn-md">Thêm</a>
                                    
                                    <%
                                		}
                                    %>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <form method="post" action="">
                                        <input type="submit" name="search" value="Tìm kiếm" class="btn btn-warning btn-sm" style="float:right" />
                                        <input type="search" class="form-control input-sm" placeholder="Nhập tên danh mục" style="float:right; width: 300px;" />
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
									}
								}
							
							%>
                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Danh mục</th>
                                        <th width="160px">Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <%
                                	if(request.getAttribute("catList")!=null){
                                		List<Category> catList = (List<Category>)request.getAttribute("catList");
                                		if(!catList.isEmpty()){
                                			for(Category cat : catList){
                                				String urlDel = request.getContextPath()+"/admin/cat/del?id="+cat.getId();
                                				String urlEdit = request.getContextPath()+"/admin/cat/edit?id="+cat.getId();
                                	
                                %>
                                    <tr>
                                        <td><%=cat.getId() %></td>
                                        <td class="center"><%=cat.getName() %></td>
                                        
                                        <td class="center">
                                        
                                        <%
                                    	if(user.getRole_id()==1||user.getRole_id()==2){
                                        %>
                                            <a href="<%=urlEdit %>" title="Sửa" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
                                            <a href="<%=urlDel %>" onclick="return confirm('Bạn có chắc chắn muốn xóa không?')" title="Xóa" class="btn btn-danger"><i class="fa fa-pencil"></i> Xóa</a>
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
                            
                        </div>

                    </div>
                </div>
                <!--End Advanced Tables -->
            </div>
        </div>
    </div>
</div>

<script>
document.getElementById("category").classList.add('active-menu');
</script> 
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>
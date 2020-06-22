<%@page import="dao.UserDAO"%>
<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>AdminCP | VinaEnter Edu</title>
    <!-- BOOTSTRAP STYLES-->
    <link href="<%=request.getContextPath() %>/templates/admin/assets/css/bootstrap.css" rel="stylesheet" />
    <!-- FONTAWESOME STYLES-->
    <link href="<%=request.getContextPath() %>/templates/admin/assets/css/font-awesome.css" rel="stylesheet" />
    <!-- CUSTOM STYLES-->
    <link href="<%=request.getContextPath() %>/templates/admin/assets/css/custom.css" rel="stylesheet" />
    <link href="<%=request.getContextPath() %>/templates/admin/assets/css/custom1.css" rel="stylesheet" />
    <!-- GOOGLE FONTS-->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
    <script src="<%=request.getContextPath() %>/templates/admin/assets/js/jquery-3.2.1.js"></script>
       <script src="<%=request.getContextPath() %>/templates/admin/assets/ckeditor/ckeditor.js"></script>
   
</head>

<body>
    <div id="wrapper">
        <nav class="navbar navbar-default navbar-cls-top " role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="<%=request.getContextPath()%>/admin">VinaEnter Edu</a>
            </div>
            <div style="color: white;padding: 15px 50px 5px 50px;float: right;font-size: 16px;">
            <%
            	UserDAO ud= new UserDAO();
            	if(session.getAttribute("isUser") !=null) {
            		User user = (User )session.getAttribute("isUser") ;
            		User userH = null;
            		if(user!=null){
            			userH = ud.getItem(user.getId());
            	
            %> 
            Xin chào, <b><a style="color: white;"  href="<%=request.getContextPath()%>/admin/user/profile?id=<%=user.getId()%>"><%=userH.getFullname() %></a></b> &nbsp; <a href="<%=request.getContextPath() %>/auth/logout" class="btn btn-danger square-btn-adjust">Đăng xuất</a> 
            <%
    		
            		}
            	} else {
            		
            	
            %>
             <a href="<%=request.getContextPath() %>/admin/login" class="btn btn-danger square-btn-adjust">Đăng Nhập</a> 
             
             <%
            	}
             %>
            </div>
        </nav>
        <!-- /. NAV TOP  -->
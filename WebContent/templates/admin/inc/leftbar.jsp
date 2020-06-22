<%@page import="utils.AuthUtil"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="dao.UserDAO"%>
<%@page import="dao.UserRoleDAO"%>
<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<nav class="navbar-default navbar-side" role="navigation">
    <div class="sidebar-collapse">
        <ul class="nav" id="main-menu">
            <li class="text-center" >
                <img src="<%=request.getContextPath() %>/templates/admin/assets/img/find_user.png" class="user-image img-responsive" />
            </li>
            
            <li>
                <a id="index" href="<%=request.getContextPath() %>/admin"><i class="fa fa-dashboard fa-3x"></i> Trang chủ</a>
            </li>
         	<%
         		User userL = null;

         		UserDAO uDao = new UserDAO();
         		String concatRole = "";
         		if (session.getAttribute("isUser") != null) {
         			userL = (User) session.getAttribute("isUser");
         			List<User> roleList = null;
         			if (uDao.getRoleList(userL.getId()) != null) {
         				roleList = uDao.getRoleList(userL.getId());
         				List<String> lr = new ArrayList<>();
         				for (User user : roleList) {
         					lr.add(user.getRole_name());
         				}
         				concatRole = AuthUtil.concatRole(lr);
         			}

         		}

         		/* if(userL.getRole_id()==1 || userL.getRole_id()==2){ */
         	%>
            <li <% if(!AuthUtil.isRole(concatRole, "admin", "editor")) {out.print("style='display:none'");} %>>
                <a id="category" href="<%=request.getContextPath() %>/admin/cat/index"><i class="fa fa-list fa-3x"></i> Quản lý danh mục</a>
            </li>
            <li <% if(!AuthUtil.isRole(concatRole, "admin", "editor")) {out.print("style='display:none'");} %>>
                <a id="song" href="<%=request.getContextPath() %>/admin/song/index"><i class="fa fa-music fa-3x"></i> Quản lý bài hát</a>
            </li>
            <li <% if(!AuthUtil.isRole(concatRole, "admin")) {out.print("style='display:none'");} %>>
                <a id="user" href="<%=request.getContextPath() %>/admin/user/index"><i class="fa fa-user fa-3x"></i> Quản lý người dùng</a>
            </li>
            <li <% if(!AuthUtil.isRole(concatRole, "admin", "mod")) {out.print("style='display:none'");} %>>
                <a id="slide" href="<%=request.getContextPath() %>/amdin/slide/index"><i class=""></i> <img style="margin-left:-10px;" alt=""  src="https://img.icons8.com/material-sharp/40/FFFFFF/tv.png"> &ensp;  Quản lý slide</a>
            </li>
             <li <% if(!AuthUtil.isRole(concatRole, "admin", "mod")) {out.print("style='display:none'");} %>>
                <a id="logo" href="<%=request.getContextPath() %>/amdin/logo/update"><i class=""></i><img style="margin-left:-10px;" alt=""  src="https://img.icons8.com/android/40/FFFFFF/picture.png"> &ensp; Quản lý Logo</a>
            </li>
            <li <% if(!AuthUtil.isRole(concatRole, "admin", "mod")) {out.print("style='display:none'");} %>>
                <a id="contact" href="<%=request.getContextPath() %>/admin/contact/index"><i class="fa fa-envelope fa-3x"></i> Quản lý liên hệ</a>
            </li>
        </ul>
    </div>
</nav>
<!-- /. NAV SIDE  -->
<%@page import="model.Logo"%>
<%@page import="dao.LogoDAO"%>
<%@page import="model.Slide"%>
<%@page import="dao.SlideDao"%>
<%@page import="utils.LogoUtil"%>
<%@page import="utils.SlideList"%>
<%@page import="model.Song"%>
<%@page import="java.util.List"%>
<%@page import="dao.SongDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<title>BSong</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<%=request.getContextPath() %>/templates/public/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/templates/public/css/style1.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/templates/public/css/coin-slider.css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/templates/public/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/templates/public/js/scriptt.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/templates/public/js/coin-slider.min.js"></script>
 <script src="<%=request.getContextPath() %>/templates/admin/assets/ckeditor/ckeditor.js"></script>

  <script type="text/javascript"
	src="<%=request.getContextPath()%>/templates/admin/assets/jquery.validate.min.js"></script>
</head>
<body>
<div class="mainn	">
  <div class="header">
    <div class="header_resize">
      <div class="logoo">
      <%
      	LogoDAO logoDAO = new LogoDAO();
      	Logo logo=null;
      	if(logoDAO.getItem()!=null){
      	 logo = logoDAO.getItem();
    	}
      %>
        <h1><a href="<%=request.getContextPath()%>/index"><img style="	display: block;
	width: 110px;
	height: 80px; " alt="" src="<%=request.getContextPath()%>/files/<%=logo.getLogoName()%>"> </a></h1>
	
      </div>
      <div id="myDIV" class="menu_na">
        <ul>
          <li id=cs_home ><a href="<%=request.getContextPath()%>/index"><span>Trang chủ</span></a></li>
        <li id="cs_contact"><a href="<%=request.getContextPath()%>/contact"><span>Liên hệ</span></a></li>  
        </ul>
      </div>
      <div class="clr"></div>
     
      <div class="sliderr" style="position: relative;">
   		 <div class="coin-slider" id="coin-slider-coin-slider">	
        <div id="coin-slider" style="background-image: url(&quot;/files/&quot;); width: 1200px; height: 400px; position: relative; background-position: left top;">
           <%
      	SongDAO sDao = new SongDAO();
           SlideDao slideDao = new SlideDao();
   		
   		if (slideDao.getItems()!=null) {
   			 
   			List<Slide> slideList = slideDao.getItems();
         		for(Slide slide : slideList){
      	
      %>
        <a href="<%=request.getContextPath() %>/detail?did=<%=slide.getId()%>&cid=<%=slide.getCat_id() %>">
        	<img src="<%=request.getContextPath() %>/files/<%=slide.getPicture() %>" width="935" height="307" alt="" />
        	<div class="title"><span style="display: none;"><%=slide.getName() %></span></div>
        	
        </a>
        
        <%
      			}
      		}
      
        %>
        <div class="clr"></div>
        
      </div>
      </div>
      <div class="clr"></div>
    </div>
  </div>

  <div class="content">
<%@page import="java.util.List"%>
<%@page import="model.Song"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/public/inc/header.jsp" %>
<div class="content_resize">
  <div class="mainbar">
    <div class="article">
    
    <%
    	if(request.getAttribute("song")!=null){
    		Song song = (Song)request.getAttribute("song");
    		if(song!=null){
    	
    		
    %>
      <h1 style="font-weight: bold;font-size: 20;"><%=song.getName() %></h1>
      <div class="clr"></div>
      <p>Ngày đăng: <%=song.getDate_create() %>. Lượt xem: <%=song.getCounter() %></p>
      <!-- <p><iframe scrolling="no" width=640 height=180 src=https://zingmp3.vn/embed/song/ZWAEIUUB?start=false frameborder="0" allowfullscreen="true" /></iframe></p>
    -->
     <audio controls  width=640 height=180>
 	 <source src="horse.ogg" type="audio/ogg">
  	<source src="<%=request.getContextPath()%>/templates/admin/assets/audio/vnt.mp3" type="audio/mpeg">
</audio>
     <div class="detailcontent">
     	 <div class="vnecontent">
      	<%=song.getDetail_text() %>
      </div>
      
      <%
		
    		}
    	}
      %>
    </div>
    <div class="article1">
      <h2>Bài hát liên quan</h2>
      <div class="clr"></div>
      <%
      	if(request.getAttribute("relatedList")!=null){
      		List<Song> relatedList = (List<Song> )request.getAttribute("relatedList");
      		if(relatedList.size()>0){
      			for(Song songR:relatedList){
      		
      %>
      <%
			String urlSlug =request.getContextPath()+"/detail/"+StringUtil.makeSlug(songR.getName())+"-"+songR.getId()+"-"+songR.getCat_id()+".html";
			%>
      <div class="comment"> <a href=""><img src="<%=request.getContextPath()%>/files/<%=songR.getPicture()%>" width="40" height="40" alt="" class="userpic" /></a>
        <h2><a class="name" href="<%=urlSlug%>"><%=songR.getName() %></a></h2>
      </div>
      <%
		
      			}
      		}
      	}
      %>
    </div>
     </div>
  </div>
  <div class="sidebar">
  <%@ include file="/templates/public/inc/leftbar.jsp" %>
  </div>
  <div class="clr"></div>
</div>
<%@ include file="/templates/public/inc/footer.jsp" %>

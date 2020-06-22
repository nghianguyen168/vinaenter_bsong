<%@page import="utils.DefineUtil"%>
<%@page import="model.Song"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/public/inc/header.jsp" %>
<div class="content_resize">
  <div class="mainbar">
  	<%
  		int cid=0;
	  	if(request.getAttribute("cid")!=null){
			cid=(int)request.getAttribute("cid");
		}
  		CategoryDAO categoryDAO1 = new CategoryDAO();
  		Category cate =null;
  		if(categoryDAO1.getItemById(cid)!=null){
  			 cate = categoryDAO1.getItemById(cid);
  		}
  	%>
  	 <h2 style="margin-left: 20px; color: #086A87;">PlAYLIST <%=cate.getName().toUpperCase() %></h2>
     <%

		int offset=0;
     	
		if(request.getAttribute("offset")!=null){
			offset=(int)request.getAttribute("offset");
		}
		if(request.getAttribute("cid")!=null){
			cid=(int)request.getAttribute("cid");
		}
		List<Song> songListByPage =null;
			if (sDao.getItemsByPageCat(offset, cid)!= null) {
		 		songListByPage = (List<Song>) sDao.getItemsByPageCat(offset, cid);
		 	if (request.getAttribute("songListSearch") != null) {
			List<Song> songListSearch = (List<Song>) request.getAttribute("songListSearch");
			if(songListSearch.size()>0){
				songListByPage = songListSearch;
			}
		} 
		if (songListByPage.size() > 0) {
			for (Song song : songListByPage) {
			%>
			 <div class="article">
			
			<h2>
			<%
			String urlSlug =request.getContextPath()+"/detail/"+StringUtil.makeSlug(song.getName())+"-"+song.getId()+"-"+song.getCat_id()+".html";
			%>
				<a class="name" href="<%=urlSlug%>" title="<%=song.getName()%>"><%=song.getName()%></a>
			</h2>
			<p class="infopost">
				Ngày đăng: <%=song.getDate_create() %>. <a href="<%=request.getContextPath() %>/detail?did=<%=song.getId() %>&cid=<%=song.getCat_id()%>"
					class="com"><span>  <%=song.getCounter() %></span></a>
			</p>
			<div class="clr"></div>
			<div class="img">
				<img
				<%
						String pic="";
						if("".equals(song.getPicture())){
							pic="no-image.jpg";
						} else {
							pic=song.getPicture();
						}
						
					%>
					src="<%=request.getContextPath()%>/files/<%=pic %>"
					width="180" height="130" alt="Đổi thay" class="fl" />
			</div>
			<div class="post_content">
				<p>“<%=song.getPreview_text() %></p>
				<p class="spec">
					<a href="<%=urlSlug%>" class="rn">Chi tiết &raquo;</a>
				</p>
			</div>
			
			<div class="clr"></div>
			<hr style="opacity: 0.4">
			</div>
			
       
  <%
						}}}
   %>
    <%
		int numberOfPages=1;
		int curentPage=1;
		int numberOfItems=DefineUtil.NUMBER_PER_PAGE;
			if(request.getAttribute("numberOfPages")!=null){
			 numberOfPages =(int) request.getAttribute("numberOfPages");
			}
			if(request.getAttribute("curentPage")!=null){
			 curentPage =(int) request.getAttribute("curentPage");
		} if(request.getAttribute("numberOfItems")!=null){
			numberOfItems =(int) request.getAttribute("numberOfItems");
		}
		if(songListByPage!=null && songListByPage.size()>0){
	
	%>
  
    <p class="pages"><small>Trang <%=curentPage %> của <%=numberOfPages %></small>
    <%
	for(int i=1;i<=numberOfPages;i++){
    %>
    <%
				if(curentPage==i){
			%>
    <span><%=i %></span>
    <%
				} else {
    %>
    <a href="<%=request.getContextPath()%>/cat?page=<%=i%>&cid=<%=cid%>"><%=i %></a>
    <%
				}} }
    %>
    <%
    	if(numberOfPages>1){
    %>
     <a href="<%=request.getContextPath()%>/cat?page=<%=curentPage+1%>&cid=<%=cid%>">&raquo;</a></p>
     <%
    	}
     %>
  </div>
  
  <div class="sidebar">
      <%@ include file="/templates/public/inc/leftbar.jsp" %>
  </div>
  <div class="clr"></div>
</div>
<%@ include file="/templates/public/inc/footer.jsp" %>
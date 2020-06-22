<%@page import="utils.DefineUtil"%>
<%@page import="java.util.List"%>
<%@page import="model.Song"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/public/inc/header.jsp"%>
<div class="content_resize">
	<div class="mainbar">
		<%-- 	
		 <%

		if (request.getParameter("msgS") != null) {
			int msgS = Integer.parseInt(request.getParameter("msgS"));
			if(msgS==0){
				out.print("Không tìm thấy bài hát");
			} else{
	
		%>  --%>
		<%
				
				int offset=0;
				if(request.getAttribute("offset")!=null){
					offset=(int)request.getAttribute("offset");
				}
				List<Song> songListByPage =null;
			if (sDao.getItemsByPage(offset) != null) {
				 songListByPage = (List<Song>) sDao.getItemsByPage(offset);
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
				<a class="name"
					href="<%=urlSlug %>"
					title="<%=song.getName()%>"><%=song.getName()%></a>
			</h2>
			<p class="infopost" style="border-radius: 6px; background: #58A808">
				Ngày đăng:
				<%=song.getDate_create()%>
				<a
					href="<%=request.getContextPath()%>/detail?did=<%=song.getId()%>&cid=<%=song.getCat_id()%>"
					class="com"><span><%=song.getCounter()%></span></a>
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
					src="<%=request.getContextPath()%>/files/<%=pic%>"
					width="180" height="130" alt="" class="fl" />
			</div>
			<div class="post_content">
				<p>
					“<%=song.getPreview_text()%></p>
				<p class="spec">
					<a style=""
						href="<%=urlSlug%>"
						class="rn">Chi tiết &raquo;</a>
						
				</p>
			</div>
			<div class="clr"></div>
			<hr style="opacity: 0.4">
		</div>
		<%
			}

				}
			}
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
	    <%} else { %>
			<a class=""  href="<%=request.getContextPath()%>/index?page=<%=i%>"><%=i %></a>	
		<%
			}}
	    %>
	    <a href="<%=request.getContextPath()%>/index?page=<%=curentPage+1%>">">&raquo;</a></p>
		
		<%} %>

		</p>
		
 	
	</div>
	<div class="sidebar">
		<%@ include file="/templates/public/inc/leftbar.jsp"%>
	</div>
	<div class="clr"></div>
	<script>
		document.getElementById("cs_home").classList.add('hd_active');
</script>
	<!-- <script type="text/javascript">
		document.getElementById('search').addEventListener('keypress',
			function(event) {
				if (event.keyCode == 13) {
					event.preventDefault();
				}
		});
		
	</script> -->

</div>
<%@ include file="/templates/public/inc/footer.jsp"%>


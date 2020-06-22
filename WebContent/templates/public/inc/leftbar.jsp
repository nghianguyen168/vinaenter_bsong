<%@page import="utils.StringUtil"%>
<%@page import="model.Song"%>
<%@page import="dao.SongDAO"%>
<%@page import="model.Category"%>
<%@page import="java.util.List"%>
<%@page import="dao.CategoryDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<div class="searchform">
  <form id="formsearch" name="formsearch" action="<%=request.getContextPath()%>/index" method="post" >
    <span>
    <input name="search" class="editbox_search"  maxlength="80" value="" placeholder="Nhập tên bài hát" type="text" />
    </span>
    <input name="button_search" src="<%=request.getContextPath() %>/templates/public/images/search.jpg" class="button_search" type="image" />
  </form>
</div>
<div class="clr"></div>
<div class="gadget">
  <h2 class="star">Danh mục bài hát</h2>
  <div class="clr"></div>
  <ul class="cat_menu">
    <%
            	CategoryDAO categoryDAO = new CategoryDAO();
    			String cat_active="";
    			int catid=0;
    			if(request.getAttribute("cid")!=null){
    				catid=(int)request.getAttribute("cid");
    			}
            	List<Category> catList = categoryDAO.getItems();
            	if(catList!=null){
       			for(Category cat:catList){
            		if(cat.getId()==catid){
            		cat_active="cat_active";
            		} else {
            			cat_active="";
            		}
            		String urlSlug = request.getContextPath()+"/cat/"+StringUtil.makeSlug(cat.getName())+"-"+cat.getId()+".html";
            %>
    <li class="<%=cat_active %>"> <a  href="<%=urlSlug%>"><%=cat.getName() %></a></li>
   	<%
   		
   	%>
     <%
   		
            		}
            	}
           %>
  </ul>
</div>

<div class="gadget">
  <h2 class="star"><span>Bài hát mới</span></h2>
  <div class="clr"></div>
  <ul class="ex_menu">
   <%
            	SongDAO songDAO = new SongDAO();
            	List<Song> songListNews = songDAO.getItemsNews();
            	
            	
            	if(songListNews!=null){
            		for(Song song:songListNews){
            	
            
            %>
            <%
			String urlSlug =request.getContextPath()+"/detail/"+StringUtil.makeSlug(song.getName())+"-"+song.getId()+"-"+song.getCat_id()+".html";
			%>
    <li><a class="" href="<%=urlSlug%>"><%=song.getName() %></a><br />
     </li>
   <%
            		}
            	}
   %>
  </ul>
</div>
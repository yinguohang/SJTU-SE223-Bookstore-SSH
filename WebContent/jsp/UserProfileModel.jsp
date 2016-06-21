<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% 
	pageContext.setAttribute("map", (Map<String, String>)request.getAttribute("map")); 	
%>

<c:forEach var="entry" items="${map}">
	<div class="form-group removable">
		<label class="col-lg-2 key control-label">${entry.key}</label>
		<div class="col-lg-8 value" style="padding-top:7px; padding-left:25px;">${entry.value}</div>
		<div class="col-lg-2" style="visibility:hidden"><button type="button" class="btn btn-success btn-remove">删除</button></div>
	</div>
</c:forEach>
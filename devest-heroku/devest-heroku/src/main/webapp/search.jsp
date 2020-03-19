<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kgym.KgymHelper"
         import="javax.servlet.http.HttpServletRequest"
         import="java.util.ArrayList"
         import="java.util.HashMap"%>
<%
	request.setCharacterEncoding("UTF-8");
	String keyword=request.getParameter("keyword");
	ArrayList<String> items=new ArrayList<String>();
	items=(ArrayList<String>)request.getAttribute("items");
	HashMap<String,String> image=new HashMap<String,String>();
	image=(HashMap<String,String>)request.getAttribute("image");
	HashMap<String,Integer> price=new HashMap<String,Integer>();
	price=(HashMap<String,Integer>)request.getAttribute("price");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>検索キーワード：<%=keyword %></h1>
	<br>
	<p>--------------------------------</p>
	<br>
	<p>検索結果</p>
	<table>
	<tr>
		<th>サムネイル</th>
		<th>商品名</th>
		<th>金額</th>
	</tr>
	<% for(int i=0;i<items.size();i++){ %>
		<%if(i>=20){
			break;
		}%>
		<tr>
			<td>
				<img src=<%=image.get(items.get(i))%>>
			</td>
			<td>
				<%= items.get(i)%>
			</td>
			<td>
				<%= price.get(items.get(i))%>
			</td>
		</tr>
	<%} %>
	</table>
</body>
</html>
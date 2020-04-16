<%@page import="java.sql.Connection"%>
<%@page import="DB.DBconnection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
  <%
        DBconnection db = new DBconnection();
  	    Connection con = db.getconnection();
  	    
  	    if(con != null){
  	    	out.print("working successfully");
  	    }
  %>
</body>
</html>
<%-- 
    Document   : UsersPics
    Created on : Sep 24, 2014, 2:52:48 PM
    Author     : Administrator
--%>

<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Your Gallery : Instagrim</title>
        <link rel="stylesheet" type="text/css" href="/Instagrim/Styles.css" />
    </head>
    <body>
        <header>
        
        <h1>InstaGrim</h1>
        <h2>Your Pictures</h2>
        </header>
        
        <%@include file="nav.jsp"%>
 
        <article>
        <%
            java.util.LinkedList<Pic> lsPics = (java.util.LinkedList<Pic>) request.getAttribute("Pics");
            if (lsPics == null) {
        %>
        <p>No Pictures found</p>
        <%
        } else {
            Iterator<Pic> iterator;
            iterator = lsPics.iterator();
            %><ul> <%
            while (iterator.hasNext()) 
                {
                    Pic p = (Pic) iterator.next();
        %>
        <li><figure><a href="/Instagrim/Image/<%=p.getSUUID()%>"><img src="/Instagrim/Thumb/<%=p.getSUUID()%>"></a>
        <figcaption><a href="/Instagrim/Comments/<%=p.getSUUID()%>">Picture Comments</a></figcaption></figure></li>
        <%
                }
                %></ul><%
            }
        %>
        </article>
        <footer>
            <%@include file="footer.jsp"%> 
        </footer>
    </body>
</html>
